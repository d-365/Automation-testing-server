/*
 * author     : dujun
 * date       : 2022/4/21 17:27
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.common.selenium;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dujun.springboot.VO.AssertConsole;
import com.dujun.springboot.VO.UIConsole;
import com.dujun.springboot.entity.*;
import com.dujun.springboot.mapper.*;
import com.dujun.springboot.service.impl.RunPlanServiceImpl;
import com.dujun.springboot.tools.dateTools;
import com.dujun.springboot.utils.BeanContext;
import com.dujun.springboot.utils.cmdTaskUtils;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.testng.collections.Lists;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

@Log4j2
public class  runWebPlan implements Callable<String> {

    SeleniumUtils seleniumUtils = BeanContext.getApplicationContext().getBean(SeleniumUtils.class);
    RunPlanMapper RunPlanMapper = BeanContext.getApplicationContext().getBean(RunPlanMapper.class);
    PlanResultDetailMapper planResultDetailMapper = BeanContext.getApplicationContext().getBean(PlanResultDetailMapper.class);
    PlanParamMapper PlanParamMapper = BeanContext.getApplicationContext().getBean(PlanParamMapper.class);
    WebCaseStepMapper webCaseStepMapper = BeanContext.getApplicationContext().getBean(WebCaseStepMapper.class);
    RunPlanServiceImpl runPlanService = BeanContext.getApplicationContext().getBean(RunPlanServiceImpl.class);
    PlanResultMapper planResultMapper = BeanContext.getApplicationContext().getBean(PlanResultMapper.class);
    PlanRoundMapper planRoundMapper = BeanContext.getApplicationContext().getBean(PlanRoundMapper.class);
    ActionMapper actionMapper = BeanContext.getApplicationContext().getBean(ActionMapper.class);


    private final Integer planId;

    public runWebPlan(Integer planId) {
        this.planId = planId;
    }

    @Override
    public String call() {
        // 执行结果信息
        String runMessage = "执行完成";
        WebDriver driver = null;
        int case_successCount = 0;
        int case_failedCount = 0;
        PlanResult planResult;
        RunPlan plan;
        // 计划执行结果
        boolean finalPlanResult;
        plan = RunPlanMapper.selectById(planId);
        planResult = runPlanService.planResultInit(plan);

        // 执行前置操作
        try {
            // 1:执行前置操作
            List<UIConsole> step = stepUpTearDown(planId,0);
            if (step!=null){
                PlanResultDetail planResultDetail = new PlanResultDetail();
                Boolean result = getWebCaseResult(step,null);
                planResultDetail.setCaseName("前置");
                planResultDetail.setPlanResultId(planResult.getId());
                planResultDetail.setResultConsole(step);
                planResultDetail.setResult(result);
                planResultDetailMapper.insert(planResultDetail);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        // 执行计划用例信息
        try {
            // 2: planID获取用例列表
            PlanParam planParam = PlanParamMapper.byPlanId(planId);
            if (planParam.getCaseIds().size() == 0){
                return "测试计划为空";
            }

            // 判断计划中浏览器参数是否为空，为空默认谷歌浏览器执行
            String browser="chrome";
            if (plan.getBrowserType()!=null&& !Objects.equals(plan.getBrowserType(), "")){
                browser = plan.getBrowserType();
            }

            // 2.1: 启动WebDriver
            try {
                String remoteAddress = "http://127.0.0.1:4444";
                driver = MySelenium.getRemoteDriver(remoteAddress,browser);
            }catch (Exception e){
                e.printStackTrace();
                return "执行失败：无法创建driver实例";
            }

            //3: 遍历用例列表--获取对应用例步骤列表
            List<UIConsole> consoleMsg;
            List<AssertConsole> assertMsg;
            ArrayList<Integer> webCaseIds = planParam.getCaseIds();
            for (Integer webCaseId : webCaseIds) {
                // 用例执行结果信息
                consoleMsg = Lists.newArrayList();
                // 用例断言信息
                 assertMsg = Lists.newArrayList();
                // 获取用例步骤信息
                LambdaQueryWrapper<WebCaseStep> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(WebCaseStep::getCaseId,webCaseId);
                lambdaQueryWrapper.eq(WebCaseStep::getStatus,0);
                List<WebCaseStep> webCaseSteps = webCaseStepMapper.selectList(lambdaQueryWrapper);
                try {
                    // 4: 遍历执行用例步骤信息
                    for (WebCaseStep CaseStep : webCaseSteps) {
                        // 执行用例
                        UIConsole console = seleniumUtils.execWebCase(driver,CaseStep);
                        consoleMsg.add(console);
                        // 断言操作
                        if (!Objects.equals(CaseStep.getAssertType(), "") && CaseStep.getAssertType()!=null){
                            AssertConsole assertConsole = seleniumUtils.execAssert(driver,CaseStep);
                            assertMsg.add(assertConsole);
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    // 5: 用例执行结果保存到数据库中
                    Boolean caseResult = getWebCaseResult(consoleMsg,assertMsg);
                    if (caseResult){
                        case_successCount++;
                    }else {
                        case_failedCount++;
                    }
                    PlanResultDetail planResultDetail = new PlanResultDetail();
                    planResultDetail.setCaseId(webCaseId);
                    planResultDetail.setResult(caseResult);
                    planResultDetail.setPlanResultId(planResult.getId());
                    planResultDetail.setResultConsole(consoleMsg);
                    planResultDetail.setAssertResult(assertMsg);
                    planResultDetailMapper.insert(planResultDetail);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
            case_failedCount++;
            runMessage = "执行失败"+e.toString();
        }finally {
            // 关闭driver
            if (driver!=null){
                driver.quit();
            }
            // 5:获取用例执行结果,将用例最终执行结果更新到数据库
            finalPlanResult = getPlanResult(case_failedCount);
            assert planResult != null;
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
            if(isSendEmail == 1){
                RunPlanServiceImpl.planSendMail(plan,planResult.getId(),case_successCount, case_failedCount);
            }else if (isSendEmail == 2){
                if (!finalPlanResult){
                    RunPlanServiceImpl.planSendMail(plan,planResult.getId(),case_successCount,case_failedCount);
                }
            }
        }
        //执行后置操作
        try {
            // 1:执行前置操作
            List<UIConsole> step = stepUpTearDown(planId,1);
            if (step!=null){
                PlanResultDetail planResultDetail = new PlanResultDetail();
                Boolean result = getWebCaseResult(step,null);
                planResultDetail.setCaseName("后置");
                planResultDetail.setPlanResultId(planResult.getId());
                planResultDetail.setResultConsole(step);
                planResultDetail.setResult(result);
                planResultDetailMapper.insert(planResultDetail);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return runMessage;
    }

    /**
     *  获取单条We用例执行结果
     */
    public Boolean getWebCaseResult(List<UIConsole> uiConsoles,List<AssertConsole> assertMsg){
        boolean result = true;
        for (UIConsole uiConsole : uiConsoles) {
            if (uiConsole.getCode() == 1 || uiConsole.getCode() == null) {
                result = false;
                break;
            }
        }
        boolean assertResult = true;
        if (assertMsg !=null ){
            if (assertMsg.size()!=0){
            for (AssertConsole assertConsole : assertMsg) {
                if (!assertConsole.getResult()){
                    assertResult = false;
                    break;
                }
            }
            }
        }
        return result && assertResult;
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

    /**
     * 端口是否占用
     */
    public static Boolean portStart(String address,Integer port){
        boolean result = false;
        try {
            new Socket(address,port);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
            log.debug(String.format("端口%1$s未开启成功",port));
        }
        return result;
    }

    /**
     * 开启seleniumGrid服务
     */
    public  static void gridStart(){
        boolean result = portStart("127.0.0.1",4444);
        if (!result){
            try {
                String path = ResourceUtils.getURL("classpath:").getPath();
                String gridPath = path +"static"+File.separator+"data"+File.separator+"selenium-server-4.1.3.jar";
                File file = new File(gridPath);
                System.out.println(file.getAbsolutePath());
                cmdTaskUtils.execCommand(String.format("java -jar %s standalone",file.getAbsolutePath()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 执行前置后置动作
     * @param planId 计划ID
     * @param type 0 前置 1 后置
     */
    public  List<UIConsole> stepUpTearDown(Integer planId,Integer type){
        // 数据库查询 计划ID对应的 前置后置动作列表
        LambdaQueryWrapper<PlanRound> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PlanRound::getPlanId,planId).eq(PlanRound::getStatus,0).eq(PlanRound::getType,type);
        List<PlanRound> planRounds =  planRoundMapper.selectList(queryWrapper);
        if (planRounds.size() ==0){
            return null;
        }else {
            List<UIConsole> uiConsoles = Lists.newArrayList();
            for (PlanRound planRound : planRounds) {
                Integer actionId = planRound.getActionId();
                Action action = actionMapper.selectById(actionId);
                String actionType = action.getActionKey();
                String actionKey = planRound.getActionKey();
                String actionValue = planRound.getActionValue();
                UIConsole uiConsole = seleniumUtils.runAction(actionType,actionKey,actionValue);
                uiConsoles.add(uiConsole);
            }
            return uiConsoles;
        }
    }

}
