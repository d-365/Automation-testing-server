/*
 * author     : dujun
 * date       : 2022/7/6 11:43
 * description: App测试计划执行
 */

package com.dujun.springboot.common.appium;

import com.dujun.springboot.VO.AssertConsole;
import com.dujun.springboot.VO.UIConsole;
import com.dujun.springboot.common.selenium.runWebPlan;
import com.dujun.springboot.entity.*;
import com.dujun.springboot.mapper.MobilePhoneMapper;
import com.dujun.springboot.mapper.PlanParamMapper;
import com.dujun.springboot.mapper.PlanResultDetailMapper;
import com.dujun.springboot.service.impl.RunPlanServiceImpl;
import com.dujun.springboot.utils.BeanContext;
import io.appium.java_client.AppiumDriver;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

@Log4j2
public class ExecAppPlan implements Callable<String> {

    private final RunPlan plan;
    private final MobilePhone execPhone;
    private final runWebPlan web;

    AppUtils appUtils = BeanContext.getApplicationContext().getBean(AppUtils.class);
    PlanParamMapper planParamMapper = BeanContext.getApplicationContext().getBean(PlanParamMapper.class);
    MobilePhoneMapper mobilePhoneMapper = BeanContext.getApplicationContext().getBean(MobilePhoneMapper.class);
    RunPlanServiceImpl planService = BeanContext.getApplicationContext().getBean(RunPlanServiceImpl.class);
    PlanResultDetailMapper planResultDetailMapper = BeanContext.getApplicationContext().getBean(PlanResultDetailMapper.class);

    public ExecAppPlan(RunPlan plan, MobilePhone execPhone){
        this.plan = plan;
        this.execPhone = execPhone;
        this.web = new runWebPlan(plan.getId());
    }

    @Override
    public String call() {
        int caseSuccess = 0;
        int caseFailed = 0;
        log.debug(String.format("-----------------------开始执行APP测试计划%s--------------------", plan.getId()));
        // 获取DesiredCapabilities配置信息
        DesiredCapabilities desiredCapabilities = appUtils.getDesiredCapabilities(plan.getAppId(), execPhone.getId());
        // 获取AppiumDriver
        AppiumDriver driver = appUtils.getDriver("", desiredCapabilities);
        // 获取-执行-测试用例
        try {
            if (driver != null) {
                // 1 计划执行前-初始化动作
                // 1.1  手机状态变更（使用中）
                execPhone.setStatus(2);
                mobilePhoneMapper.updateById(execPhone);
                // 1.2 计划执行结果 初始化
                PlanResult planResult = planService.planResultInit(plan);
                // 2 执行计划前置动作
                List<UIConsole> execSetUp = web.stepUpTearDown(plan.getId(),0);
                if (execSetUp!=null){
                    PlanResultDetail planResultDetail = new PlanResultDetail();
                    Boolean result = web.getWebCaseResult(execSetUp,null);
                    planResultDetail.setCaseName("计划前置");
                    planResultDetail.setPlanResultId(planResult.getId());
                    planResultDetail.setResultConsole(execSetUp);
                    planResultDetail.setResult(result);
                    planResultDetailMapper.insert(planResultDetail);
                }
                // 3 遍历执行测试用例
                // 3.1 获取测试用例列表
                PlanParam planParam = planParamMapper.byPlanId(plan.getId());
                if (planParam==null){
                    execPhone.setStatus(0);
                    mobilePhoneMapper.updateById(execPhone);
                    return "计划中没有可执行测试用例";
                }
                List<Integer> caseIds =  planParam.getCaseIds();
                if (caseIds.size()!=0){
                    for (Integer caseId : caseIds) {
                        PlanResultDetail planResultDetail = new PlanResultDetail();
                        // 执行测试用例
                        HashMap<String,Object> execCaseResult = new ExecAppCase(caseId,driver).call();
                        List<UIConsole> resultConsoles  = (List<UIConsole>) execCaseResult.get("result");
                        List<AssertConsole> assertConsoles = (List<AssertConsole>) execCaseResult.get("assert");
                        if (web.getWebCaseResult(resultConsoles,assertConsoles)){
                            caseSuccess++;
                            planResultDetail.setResult(true);
                        }else {
                            caseFailed++;
                            planResultDetail.setResult(false);
                        }
                        // 执行结果写入数据库
                        planResultDetail.setCaseId(caseId);
                        planResultDetail.setPlanResultId(planResult.getId());
                        planResultDetail.setResultConsole(resultConsoles);
                        planResultDetail.setAssertResult(assertConsoles);
                        planResultDetailMapper.insert(planResultDetail);
                    }
                }
                // 4 执行计划后置动作
                List<UIConsole> tearDown = web.stepUpTearDown(plan.getId(),1);
                if (tearDown!=null){
                    PlanResultDetail planResultDetail = new PlanResultDetail();
                    Boolean result = web.getWebCaseResult(tearDown,null);
                    planResultDetail.setCaseName("计划后置");
                    planResultDetail.setPlanResultId(planResult.getId());
                    planResultDetail.setResultConsole(tearDown);
                    planResultDetail.setResult(result);
                    planResultDetailMapper.insert(planResultDetail);
                }
                // 5 计划执行结束-保存执行结果
                // 5.2 更新用例执行结果
                Boolean result = planService.planEndResult(null,0,caseSuccess,caseFailed,planResult);
                // 5.4 判断是否发送邮件
                Integer isSendEmail = plan.getIsSendEmail();
                if(isSendEmail == 1){
                    RunPlanServiceImpl.planSendMail(plan,planResult.getId(),caseSuccess, caseFailed);
                }else if (isSendEmail == 2){
                    if (result){
                        RunPlanServiceImpl.planSendMail(plan,planResult.getId(),caseSuccess,caseFailed);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 5.1 释放占用手机资源
            execPhone.setStatus(0);
            mobilePhoneMapper.updateById(execPhone);
            // 5.3 关闭AppiumDriver
            if (driver!=null){
                driver.quit();
            }
            log.debug("----------------------测试计划执行完毕，关闭AppiumDriver---------------------------");
        }
        return "执行完毕";

    }

}
