/*
 * author     : dujun
 * date       : 2022/4/21 17:27
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.common.selenium;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dujun.springboot.VO.UIConsole;
import com.dujun.springboot.entity.*;
import com.dujun.springboot.mapper.*;
import com.dujun.springboot.service.impl.RunPlanServiceImpl;
import com.dujun.springboot.tools.dateTools;
import com.dujun.springboot.utils.BeanContext;
import org.openqa.selenium.WebDriver;
import org.testng.collections.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

public class  runWebPlan implements Callable<String> {

    SeleniumUtils seleniumUtils = BeanContext.getApplicationContext().getBean(SeleniumUtils.class);

    RunPlanMapper RunPlanMapper = BeanContext.getApplicationContext().getBean(RunPlanMapper.class);
    PlanResultDetailMapper planResultDetailMapper = BeanContext.getApplicationContext().getBean(PlanResultDetailMapper.class);
    PlanParamMapper PlanParamMapper = BeanContext.getApplicationContext().getBean(PlanParamMapper.class);
    WebCaseStepMapper webCaseStepMapper = BeanContext.getApplicationContext().getBean(WebCaseStepMapper.class);
    RunPlanServiceImpl runPlanService = BeanContext.getApplicationContext().getBean(RunPlanServiceImpl.class);
    PlanResultMapper planResultMapper = BeanContext.getApplicationContext().getBean(PlanResultMapper.class);

    private final Integer planId;

    public runWebPlan(Integer planId) {
        this.planId = planId;
    }

    @Override
    public String call() {
        WebDriver driver = null;
        try {
            // 初始化用例执行数据
            int case_successCount = 0;
            int case_failedCount = 0;
            boolean result = false;
            // 1: planID获取用例列表
            PlanParam planParam = PlanParamMapper.byPlanId(planId);
            String browser="chrome";
            RunPlan plan = RunPlanMapper.selectById(planId);
            PlanResult planResult = runPlanService.planResultInit(plan);

            // 判断计划中浏览器参数是否为空，为空默认谷歌浏览器执行
            if (plan.getBrowserType()!=null&& !Objects.equals(plan.getBrowserType(), "")){
                browser = plan.getBrowserType();
            }

            // 2: 启动WebDriver
            String remoteAddress = "http://127.0.0.1:4444";
            driver = MySelenium.getRemoteDriver(remoteAddress,browser);

            //3: 遍历用例列表--获取对应用例步骤列表
            ArrayList<Integer> webCaseIds = planParam.getCaseIds();
            for (Integer webCaseId : webCaseIds) {
                List<UIConsole> consoleMsg = Lists.newArrayList();
                LambdaQueryWrapper<WebCaseStep> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(WebCaseStep::getCaseId,webCaseId);
                lambdaQueryWrapper.eq(WebCaseStep::getStatus,0);
                List<WebCaseStep> webCaseSteps = webCaseStepMapper.selectList(lambdaQueryWrapper);
                // 4: 遍历执行用例步骤信息
                for (WebCaseStep CaseStep : webCaseSteps) {
                    UIConsole console = seleniumUtils.execWebCase(driver,CaseStep);
                    consoleMsg.add(console);
                }
                // 5: 用例执行结果保存到数据库中
                Boolean caseResult = getWebCaseResult(consoleMsg);
                if (caseResult){
                    case_successCount++;
                }else {
                    case_failedCount++;
                }

                // 4:用例执行基本信息写入数据库
                PlanResultDetail planResultDetail = new PlanResultDetail();

                planResultDetail.setCaseId(webCaseId);
                planResultDetail.setResult(caseResult);
                planResultDetail.setPlanResultId(planResult.getId());
                planResultDetail.setResultConsole(consoleMsg);
                planResultDetailMapper.insert(planResultDetail);
            }


            // 5:获取用例执行结果,将用例最终执行结果更新到数据库
            Boolean finalPlanResult = getPlanResult(case_failedCount);
            if (finalPlanResult){
                planResult.setResultStatus(1);
            }else {
                planResult.setResultStatus(2);
            }
            planResult.setEndTime(dateTools.currentTime());
            planResult.setCaseFailedCount(case_failedCount);
            planResult.setCaseSuccessCount(case_successCount);
            planResultMapper.updateById(planResult);

            // 6:判断是否发送邮件
            Integer isSendEmail = plan.getIsSendEmail();
            int success = case_successCount;
            int failed = case_failedCount;
            if(isSendEmail == 1){
                RunPlanServiceImpl.planSendMail(plan,planResult.getId(),success, failed);
            }else if (isSendEmail == 2){
                if (!finalPlanResult){
                    RunPlanServiceImpl.planSendMail(plan,planResult.getId(),success,failed);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (driver!=null){
                driver.quit();
            }
        }
        return "执行完成";
    }

    /**
     *  获取单条We用例执行结果
     */
    public Boolean getWebCaseResult(List<UIConsole> uiConsoles){
        boolean result = true;
        for (UIConsole uiConsole : uiConsoles) {
            if (uiConsole.getCode() == 1) {
                result = false;
                break;
            }
        }
        return true;
    }

    /**
     * 获取测试计划执行结果
     */
    public Boolean getPlanResult(Integer failed){
        boolean result = true;
        if (failed != 0){
            result = false;
        }
        return  result;
    }



}
