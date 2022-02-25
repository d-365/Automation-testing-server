package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.common.ApiCommon;
import com.dujun.springboot.entity.*;
import com.dujun.springboot.mapper.*;
import com.dujun.springboot.service.RunPlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dujun.springboot.tools.StringTools;
import com.dujun.springboot.tools.dateTools;
import com.dujun.springboot.utils.MailTool;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  计划执行服务实现类
 * </p>
 *
 * @author dujun
 * @since 2022-01-12
 */
@Service
public class RunPlanServiceImpl extends ServiceImpl<RunPlanMapper, RunPlan> implements RunPlanService {

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
    // 接口详情mapper
    private ApiInfoMapper apiInfoMapper;
    @Resource
    // 用例关联表
    private ApiCaseMapper apiCaseMapper;

    //计划列表
    @Override
    public Result<List<RunPlan>> planList(RunPlan planFilter) {
        String name = planFilter.getName();
        String status = planFilter.getStatus();
        QueryWrapper<RunPlan> queryWrapper = new QueryWrapper<RunPlan>(null);
        if(name != null){
            queryWrapper.eq("name",name);
        }
        if(status != null){
            queryWrapper.eq("status",status);
        }
        List<RunPlan> planList = planInfoMapper.selectList(queryWrapper);
        for (RunPlan plan : planList) {
            int planId = plan.getId();
            PlanParam planParam = paramMapper.byPlanId(planId);
//            PlanParam planParam = paramMapper.selectOne(new QueryWrapper<PlanParam>().eq("plan_id",planId));
            plan.setPlanParam(planParam);
        }
        return Result.success(planList);
    }

    // 删除计划
    @Override
    public Result deletePlan(int planId) {
        paramMapper.delByPlanId(planId);
        planInfoMapper.deleteById(planId);
        return Result.success();
    }

    // 新增修改计划
    @Override
    public Result update(RunPlan plan){
        PlanParam planParam = plan.getPlanParam();
        System.out.println("planParam"+planParam);

        if(plan.getId() == null){
            planInfoMapper.insert(plan);
            planParam.setPlanId(plan.getId());
            paramMapper.insert(planParam);
        }else {
            planInfoMapper.updateById(plan);
            paramMapper.updateById(planParam);
        }
        System.out.println("打印ID"+plan.getId());
        return Result.success() ;
    }

    // 运行计划(数据库获取数据）
    @Override
    public Result runPlan(RunPlan planInfo){

        PlanResult planResult = planResultInit(planInfo);
        // 解析apiId
        ArrayList<Integer> apiIds =  planInfo.getPlanParam().getApiIds();

        int api_successCount = 0;
        int api_failedCount = 0;
        int case_successCount = 0;
        int case_failedCount = 0;
        boolean caseResult = false;
        // 执行接口请求
        try {
            // 执行单个接口请求
            for (Integer apiId : apiIds) {
                ApiInfo apiInfo = apiInfoMapper.selectOne(new QueryWrapper<ApiInfo>().eq("api_suite_id",apiId));
                ApiInfo apiResult = ApiCommon.apiDebugDb(apiInfo);
                PlanResultDetail planResultDetail = new PlanResultDetail();
                planResultDetail.setApiId(apiInfo.getId());
                planResultDetail.setApiInfo(apiResult);
                planResultDetail.setResult(apiResult.getResult());
                planResultDetail.setPlanResultId(planResult.getId());
                planResultDetailMapper.insert(planResultDetail);
                if(apiResult.getResult()){
                    api_successCount++;
                }else {api_failedCount++;}
            }

            // 解析caseId
            ArrayList<Integer> caseCategoryIds = planInfo.getPlanParam().getCaseIds();
            if (caseCategoryIds.size()!=0){
                for (Integer categoryId : caseCategoryIds) {
                    // 根据用例分类ID 查询 对应apiId
                    List<Integer> caseApiIds = apiCaseMapper.selectApiIdsByCategory(categoryId);
                    if(caseApiIds.size()!=0){
                        int i = 0;
                        while ( i < caseApiIds.size()) {
                            ApiInfo apiInfo = apiInfoMapper.selectById(caseApiIds.get(i));
                            ApiInfo apiResult = ApiCommon.apiDebugDb(apiInfo);
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
                        if (caseResult){
                            case_successCount++;
                        }else {
                            case_failedCount++;
                        }
                    }
                }
            }

        }catch (Exception e){
            throw e;
        }

        // 判断计划执行结果
        Boolean runResult = planEndResult(api_successCount,api_failedCount,case_successCount,case_failedCount,planResult);

        // 判断是否发送邮件
        Integer isSendEmail = planInfo.getIsSendEmail();
        int success = api_successCount+ case_successCount;
        int failed = api_failedCount +case_failedCount;
        if(isSendEmail == 1){
            planSendMail(planInfo,planResult.getId(),success, failed);
        }else if (isSendEmail == 2){
            if (!runResult){
                planSendMail(planInfo,planResult.getId(),success,failed);
            }
        }

        return Result.success();

    };

    // 初始化plan_result计划执行结果
    public  PlanResult planResultInit(RunPlan planInfo){
        // 获取计划中的apiIds 和 caseIds
        PlanParam planParam = paramMapper.selectOne(new QueryWrapper<PlanParam>().eq("plan_id",planInfo.getId()));
        planInfo.setPlanParam(planParam);
        PlanResult planResult = new PlanResult();
        planResult.setPlanName(planInfo.getName());
        planResult.setPlanId(planInfo.getId());
        planResult.setResultStatus(0);
        planResult.setStartTime(dateTools.currentTime());
        planResult.setRemark(planInfo.getRemark());
        planResultMapper.insert(planResult);
        return planResult;
    }

    // 判断计划执行结果
    public Boolean planEndResult(int apiSuccessCount ,int apiFailedCount, int caseSuccessCount, int caseFailedCount,PlanResult planResult){
        Boolean runResult = false;
        planResult.setApiSuccessCount(apiSuccessCount);
        planResult.setApiFailedCount(apiFailedCount);
        planResult.setCaseSuccessCount(caseSuccessCount);
        planResult.setCaseFailedCount(caseFailedCount);
        planResult.setEndTime(dateTools.currentTime());
        if (apiFailedCount ==0 && caseFailedCount ==0){
            planResult.setResultStatus(1);
            runResult = true;
        }else {
            planResult.setResultStatus(2);
        }
        planResultMapper.updateById(planResult);
        return runResult;
    }

    // 计划运行完毕发送邮件
    public static void planSendMail(RunPlan planInfo,Integer planResultId,int success,int failed){
        String SendEmail = planInfo.getSendEmail();
        String regex = "；|;";
        String[] to = StringTools.split(SendEmail,regex);
        String object = planInfo.getName()+"--自动化执行报告";
        String text = "执行成功数量: "+success+"<br/>执行失败数量: "+failed+
                "<br/>详情请点击: "+"<a href = 'http://localhost:8080/plan/report?id="+planResultId+"' >查看详情<a/>";
        MailTool.sendEmail(to,object,text);
    }

}
