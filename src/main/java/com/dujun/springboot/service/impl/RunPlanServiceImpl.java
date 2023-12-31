package com.dujun.springboot.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.common.ApiCommon;
import com.dujun.springboot.common.appium.AppUtils;
import com.dujun.springboot.common.appium.ExecAppPlan;
import com.dujun.springboot.common.myAnnotation.DingRobotAn;
import com.dujun.springboot.common.selenium.runWebPlan;
import com.dujun.springboot.entity.*;
import com.dujun.springboot.mapper.*;
import com.dujun.springboot.service.RunPlanService;
import com.dujun.springboot.tools.RandomValue;
import com.dujun.springboot.tools.StringTools;
import com.dujun.springboot.tools.YmlTools;
import com.dujun.springboot.tools.dateTools;
import com.dujun.springboot.utils.MailTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p>
 * 计划执行服务实现类
 * </p>
 *
 * @author dujun
 * @since 2022-01-12
 */
@Service
public class RunPlanServiceImpl extends ServiceImpl<RunPlanMapper, RunPlan> implements RunPlanService {

    @Resource
    private PlanRoundMapper planRoundMapper;

    @Resource
    private AppUtils appUtils;

    @Resource
    private MobilePhoneMapper phoneMapper;

    @Resource
    // 计划参数表
    private PlanParamMapper paramMapper;
    @Resource
    // 计划详情表
    private RunPlanMapper planInfoMapper;
    @Resource
    // 计划运行结果表
    private PlanResultMapper planResultMapper;

    // 计划运行结果详情表
    @Resource
    private PlanResultDetailMapper planResultDetailMapper;

    @Resource
    private UiWebCaseMapper webCaseMapper;

    @Resource
    // 接口详情mapper
    private ApiInfoMapper apiInfoMapper;

    @Resource
    // 用例关联表
    private ApiCaseMapper apiCaseMapper;

    // 计划运行完毕发送邮件
    public static void planSendMail(RunPlan planInfo, Integer planResultId, int success, int failed) {
        YmlTools ymlTools = new YmlTools("globalConfig.yml");
        String reportAddress = ymlTools.getValueByKey("online.report.address", "");
        String SendEmail = planInfo.getSendEmail();
        String regex = "；|;";
        String[] to = StringTools.split(SendEmail, regex);
        String object = planInfo.getName() + "--自动化执行报告";
        String context = String.format("执行成功数量: %1$s ,  <br/>执行失败数量: %2$s , <br/>详情请点击: <a href = '%3$s/#/web/report?id=%4$s'>查看详情<a/>", success, failed, reportAddress, planResultId);
        MailTool.sendEmail(to, object, context);
    }

    // 删除计划
    @Override
    public Result<?> deletePlan(int planId) {
        paramMapper.delByPlanId(planId);
        planInfoMapper.deleteById(planId);
        return Result.success();
    }

    //计划列表
    @Override
    public Result<List<RunPlan>> planList(RunPlan planFilter) {
        String name = planFilter.getName();
        String status = planFilter.getStatus();
        Integer planType = planFilter.getPlanType();
        QueryWrapper<RunPlan> queryWrapper = new QueryWrapper<>(null);
        queryWrapper.orderByDesc("create_time");
        if (planType != null) {
            queryWrapper.eq("plan_type", planType);
        }
        if (name != null) {
            queryWrapper.like("name", name);
        }
        if (status != null) {
            queryWrapper.eq("status", status);
        }
        List<RunPlan> planList = planInfoMapper.selectList(queryWrapper);
        for (RunPlan plan : planList) {
            int planId = plan.getId();
            PlanParam planParam = paramMapper.byPlanId(planId);
            plan.setPlanParam(planParam);
        }
        return Result.success(planList);
    }

    // 新增修改计划
    @Override
    public Result<?> update(RunPlan plan) {
        PlanParam planParam = plan.getPlanParam();
        if (plan.getId() == null) {
            planInfoMapper.insert(plan);

            if (planParam != null) {
                planParam.setPlanId(plan.getId());
                paramMapper.insert(planParam);
            }

        } else {
            planInfoMapper.updateById(plan);
            if (planParam != null) {
                paramMapper.updateById(planParam);

            }
        }
        return Result.success();
    }

    // 运行接口自动化计划(数据库获取数据）
    @Override
    public Result<?> runApiPlan(RunPlan planInfo) {
        // 获取计划中的apiIds 和 caseIds
        PlanParam planParam = paramMapper.selectOne(new QueryWrapper<PlanParam>().eq("plan_id", planInfo.getId()));
        planInfo.setPlanParam(planParam);
        PlanResult planResult = planResultInit(planInfo);
        // 解析apiId
        ArrayList<Integer> apiIds = planInfo.getPlanParam().getApiIds();
        int api_successCount = 0;
        int api_failedCount = 0;
        int case_successCount = 0;
        int case_failedCount = 0;
        boolean caseResult = false;
        // 执行接口请求
        Integer envId = planInfo.getEnvId();
        try {
            // 执行单个接口请求
            for (Integer apiId : apiIds) {
                ApiInfo apiInfo = apiInfoMapper.selectOne(new QueryWrapper<ApiInfo>().eq("api_suite_id", apiId));
                // 查询封装对应的前置后置动作
                ApiCommon.setUpTearDownDispose(apiInfo);
                ApiInfo apiResult = ApiCommon.apiDebugDb(envId, apiInfo);
                PlanResultDetail planResultDetail = new PlanResultDetail();
                planResultDetail.setApiId(apiInfo.getId());
                planResultDetail.setApiInfo(apiResult);
                planResultDetail.setResult(apiResult.getResult());
                planResultDetail.setPlanResultId(planResult.getId());
                planResultDetailMapper.insert(planResultDetail);
                if (apiResult.getResult()) {
                    api_successCount++;
                } else {
                    api_failedCount++;
                }
            }

            // 解析caseId
            ArrayList<Integer> caseCategoryIds = planInfo.getPlanParam().getCaseIds();

            if (caseCategoryIds.size() != 0) {
                for (Integer categoryId : caseCategoryIds) {
                    // 根据用例分类ID 查询 对应apiId
                    List<Integer> caseApiIds = apiCaseMapper.selectApiIdsByCategory(categoryId);
                    if (caseApiIds.size() != 0) {
                        int i = 0;
                        while (i < caseApiIds.size()) {
                            ApiInfo apiInfo = apiInfoMapper.selectById(caseApiIds.get(i));
                            ApiInfo apiResult = ApiCommon.apiDebugDb(envId, apiInfo);
                            PlanResultDetail planResultDetail = new PlanResultDetail();
                            planResultDetail.setApiId(apiInfo.getId());
                            planResultDetail.setApiInfo(apiResult);
                            planResultDetail.setCaseId(apiCaseMapper.caseIdByCategory(categoryId));
                            planResultDetail.setResult(apiResult.getResult());
                            planResultDetail.setPlanResultId(planResult.getId());
                            planResultDetailMapper.insert(planResultDetail);
                            caseResult = apiResult.getResult();
                            i++;
                        }
                        if (caseResult) {
                            case_successCount++;
                        } else {
                            case_failedCount++;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断计划执行结果
        Boolean runResult = planEndResult(api_successCount, api_failedCount, case_successCount, case_failedCount, planResult);

        // 判断是否发送邮件
        Integer isSendEmail = planInfo.getIsSendEmail();
        int success = api_successCount + case_successCount;
        int failed = api_failedCount + case_failedCount;
        if (isSendEmail == 1) {
            planSendMail(planInfo, planResult.getId(), success, failed);
        } else if (isSendEmail == 2) {
            if (!runResult) {
                planSendMail(planInfo, planResult.getId(), success, failed);
            }
        }
        return Result.success();

    }

    // 初始化接口自动化plan_result计划执行结果
    public PlanResult planResultInit(RunPlan planInfo) {
        PlanResult planResult = new PlanResult();
        planResult.setPlanType(planInfo.getPlanType());
        planResult.setPlanName(planInfo.getName());
        planResult.setPlanId(planInfo.getId());
        planResult.setResultStatus(0);
        planResult.setStartTime(dateTools.currentTime());
        planResult.setRemark(planInfo.getRemark());
        planResultMapper.insert(planResult);
        return planResult;
    }

    // 判断接口计划执行结果
    public Boolean planEndResult(Integer apiSuccessCount, Integer apiFailedCount, Integer caseSuccessCount, Integer caseFailedCount, PlanResult planResult) {
        boolean runResult = false;
        planResult.setApiSuccessCount(apiSuccessCount);
        planResult.setApiFailedCount(apiFailedCount);
        planResult.setCaseSuccessCount(caseSuccessCount);
        planResult.setCaseFailedCount(caseFailedCount);
        planResult.setEndTime(dateTools.currentTime());
        if (apiFailedCount == 0 && caseFailedCount == 0) {
            planResult.setResultStatus(1);
            runResult = true;
        } else {
            planResult.setResultStatus(2);
        }
        YmlTools ymlTools = new YmlTools("globalConfig.yml");
        String reportAddress = ymlTools.getValueByKey("online.report.address", "");
        String planAddress = String.format("%1$s/#/web/report?id=%2$s",reportAddress,planResult.getId());
        planResult.setResultAddress(planAddress);
        planResultMapper.updateById(planResult);
        return runResult;
    }

    @Override
    // 获取测试计划详情中的setup 和 tearDown
    public Result<?> setupList(Integer planId) {
        LambdaQueryWrapper<PlanRound> setupQuery = new LambdaQueryWrapper<>();
        setupQuery.eq(PlanRound::getPlanId, planId).eq(PlanRound::getType, 0);
        List<PlanRound> setup = planRoundMapper.selectList(setupQuery);

        LambdaQueryWrapper<PlanRound> tearDownQuery = new LambdaQueryWrapper<>();
        tearDownQuery.eq(PlanRound::getPlanId, planId).eq(PlanRound::getType, 1);
        List<PlanRound> tearDown = planRoundMapper.selectList(tearDownQuery);

        HashMap<String, List<PlanRound>> result = new HashMap<String, List<PlanRound>>() {
        };
        result.put("setup", setup);
        result.put("tearDown", tearDown);

        return Result.success(result);
    }

    @Override
    public Result<?> delRound(Integer id) {
        planRoundMapper.deleteById(id);
        return Result.success();
    }

    /**
     * 执行APP测试计划
     **/
    @Override
    @DingRobotAn
    public Result<?> appPlanExec(Integer planId) {
        RunPlan plan = planInfoMapper.selectById(planId);
        // 判断APP是否为空
        if (plan.getAppId() == null) {
            return Result.error("计划未执行,App为空");
        }
        // 判断appiumServer是否可正常开启
        Boolean appServerStatus = appUtils.AppiumStart();
        if (!appServerStatus) {
            return Result.error("计划未执行,AppiumServer无法启动");
        }
        // 判断是否有空闲的手机
        List<MobilePhone> phone = phoneMapper.onlinePhone();
        if (phone.size() > 0) {
            Integer randomInteger = RandomValue.getInteger(0, phone.size());
            MobilePhone execPhone = phone.get(randomInteger);
            // 执行APP测试计划
            ExecutorService executor = Executors.newCachedThreadPool();
            Future<String> appFutures = executor.submit(new ExecAppPlan(plan, execPhone));
            String runResult = "";
            try {
                runResult = appFutures.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Result.success(runResult);
        } else {
            return Result.error("计划未执行,没有空闲的测试机");
        }
    }

    /**
     * web自动化计划执行
     * 1: planID获取用例列表
     * 2: 遍历用例列表
     * 3： 获取对应用例步骤列表
     * 4： 遍历用例列表执行
     * 5： 用例执行结果保存到数据库
     *
     * @param planId 计划ID
     * @return Result
     */
    @Override
    @DingRobotAn
    public Result<?> webRun(Integer planId) {
        boolean gridStart = runWebPlan.portStart("127.0.0.1", 4444);
        if (!gridStart) {
            runWebPlan.gridStart();
            boolean gridStart2 = runWebPlan.portStart("127.0.0.1", 4444);
            if (!gridStart2) {
                return Result.error("本机seleniumGrid未成功启动");
            }
        }
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> futures = executor.submit(new runWebPlan(planId));
        String runResult = "";
        try {
            runResult = futures.get();
            if (!Objects.equals(runResult, "执行完成")) {
                return Result.error(runResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success(runResult);
    }

    @Override
    public Result<?> checkedCase(List<Integer> caseIds) {
        List<UiWebCase> webCase = new ArrayList<>();
        for (Integer caseId : caseIds) {
            webCase.add(webCaseMapper.selectById(caseId));
        }
        return Result.success(webCase);
    }

    // 更新保存前置后置操作
    public Result<?> updateRound(String payload) {
        JSONObject jsonObject = JSONObject.parseObject(payload);
        // 前置数据
        JSONArray setUpRounds = jsonObject.getJSONArray("setUp");
        for (Object planRound : setUpRounds) {
            PlanRound setup = JSONObject.parseObject(planRound.toString(), PlanRound.class);
            Result<?> result = updateRoundHelp(setup);
            if (Objects.equals(result.getCode(), "1")) {
                return result;
            }
        }
        // 后置数据
        JSONArray tearDownRounds = jsonObject.getJSONArray("tearDown");
        for (Object planRound : tearDownRounds) {
            PlanRound tearDown = JSONObject.parseObject(planRound.toString(), PlanRound.class);
            tearDown.setType(1);
            Result<?> result = updateRoundHelp(tearDown);
            if (Objects.equals(result.getCode(), "1")) {
                return result;
            }
        }
        return Result.success();
    }

    public Result<?> updateRoundHelp(PlanRound round) {
        Result<?> result = new Result<>();
        if (round.getActionId() == null) {
            result.setMsg("操作不能为空");
            result.setCode(String.valueOf(1));
            return result;
        }
        if (round.getActionKey() == null && round.getActionValue() == null) {
            result.setMsg("参数不能为空");
            result.setCode(String.valueOf(1));
            return result;
        }
        if (round.getId() != null) {
            planRoundMapper.updateById(round);
        } else {
            planRoundMapper.insert(round);
        }
        result.setMsg("操作成功");
        result.setCode(String.valueOf(0));
        return result;

    }

}
