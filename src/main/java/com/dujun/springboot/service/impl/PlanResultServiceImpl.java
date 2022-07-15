package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.*;
import com.dujun.springboot.mapper.*;
import com.dujun.springboot.service.PlanResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.testng.collections.Lists;

import javax.annotation.Resource;
import javax.swing.plaf.metal.MetalBorders;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dujun
 * @since 2022-01-19
 */
@Service
public class PlanResultServiceImpl extends ServiceImpl<PlanResultMapper, PlanResult> implements PlanResultService {

    @Resource
    private CategoryApiMapper  categoryApiMapper;

    @Resource
    private PlanResultMapper planResultMapper;

    @Resource
    private PlanResultDetailMapper planResultDetailMapper;

    @Resource
    private ApiCaseMapper apiCaseMapper;

    @Resource
    private WebCaseStepMapper webCaseStepMapper;

    //计划运行结果列表
    @Transactional
    public RespPageEntity result_list(PlanResult planResult,Integer page ,Integer size){


        String planName = planResult.getPlanName();
        Integer  resultStatus = planResult.getResultStatus();
        String startTime = planResult.getStartTime();

        Integer planType = planResult.getPlanType();

        RespPageEntity pageEntity = new RespPageEntity();

        // 默认从0开始
        if (page != null && size != null) {
            page = (page-1)*size;
        }

        List<PlanResult> planResultList = planResultMapper.planResultList(page,size,planName,planType,resultStatus,startTime);
        pageEntity.setData(planResultList);
        pageEntity.setTotal(planResultMapper.planResultTotal(resultStatus,planName,planType,startTime));

        return  pageEntity;
    }

    // 删除选中的计划列表
    @Transactional
    public Result<?> delPlanResult(ArrayList<Integer> checkResultId){
        for (Integer planResultId : checkResultId) {
            // 删除计划结果详情
            planResultDetailMapper.delete(new QueryWrapper<PlanResultDetail>().eq("plan_result_id",planResultId));
            // 删除计划运行结果
            planResultMapper.deleteById(planResultId);
        }

        return Result.success();
    }


    // 获取接口自动化计划执行结果
    public Result<?> report(Integer planResultId){
        planResultReport planResultReport = new planResultReport();
        // 根据ID查询计划执行结果
        PlanResult planResult = planResultMapper.selectById(planResultId);
        planResultReport.setPlanResult(planResult);
        // 解析计划执行结果详情
        List<PlanResultDetail> planResultDetails = planResultDetailMapper.selectList(new QueryWrapper<PlanResultDetail>().eq("plan_result_id",planResultId));
        ArrayList<ApiInfo> apiReport = new ArrayList<>();
        ArrayList<ApiCase> caseReport = new ArrayList<>();
        // 用例ID
        TreeSet<Integer> caseIds =  new TreeSet<>();
        // 单接口ID
        ArrayList<Long> apiIds = new ArrayList<>();
        // 封装apiReport
        for (PlanResultDetail planResultDetail : planResultDetails) {
            if(planResultDetail.getCaseId() == null){
                apiReport.add(planResultDetail.getApiInfo());
                apiIds.add(planResultDetail.getApiId());
            }else {
                caseIds.add(planResultDetail.getCaseId());
            }
        }
        // 封装caseReport
        for (Integer caseId : caseIds) {
            boolean result = true;
            ApiCase apiCase = apiCaseMapper.selectCase(caseId);
            List<PlanResultDetail> caseResult = planResultDetailMapper.selectList(new QueryWrapper<PlanResultDetail>().eq("plan_result_id",planResultId).eq("case_id",caseId));
            ArrayList<ApiInfo> caseStep = new ArrayList<>();
            for (PlanResultDetail planResultDetail : caseResult) {
                caseStep.add(planResultDetail.getApiInfo());
                if(!planResultDetail.getResult()){
                    result = false;
                }
            }
            if (apiCase!=null){
                apiCase.setResult(result);
                apiCase.setStep(caseStep);
            }
            caseReport.add(apiCase);
        }
        planResultReport.setApiRunResult(apiReport);
        planResultReport.setCaseRunResult(caseReport);
        // 封装用例树
        // 封装接口树
        return Result.success(planResultReport);
    }


    // 获取计划执行结果Web自动化
    @Override
    public Result<?> webReport(Integer planResultId) {

        planResultReport planResultReport = new planResultReport();
        // 将计划结果信息进行封装
        PlanResult planResult = planResultMapper.selectById(planResultId);
        planResultReport.setPlanResult(planResult);

        // 封装用例步骤信息
        LambdaQueryWrapper<PlanResultDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(PlanResultDetail::getPlanResultId,planResultId);
        List<PlanResultDetail> caseList = planResultDetailMapper.selectList(lambdaQueryWrapper);

        // 遍历用例名信息 用例步骤信息
        for (PlanResultDetail planResultDetail : caseList) {
            Integer caseId = planResultDetail.getCaseId();
            // 用例名
            String caseName = planResultDetailMapper.findCaseName(caseId);

            // 用例步骤信息
            LambdaQueryWrapper<WebCaseStep> caseStepLambda = new LambdaQueryWrapper<>();
            caseStepLambda.eq(WebCaseStep::getCaseId,caseId);
            caseStepLambda.orderByAsc(WebCaseStep::getSort);
            List<WebCaseStep> caseSteps = webCaseStepMapper.selectList(caseStepLambda);
            planResultDetail.setCaseSteps(caseSteps);
            if (planResultDetail.getCaseName() ==null){
                planResultDetail.setCaseName(caseName);
            }

        }
        planResultReport.setCaseRunResult((ArrayList<PlanResultDetail>) caseList);

        // 封装计划执行详情信息
        return Result.success(planResultReport);
    }

}
