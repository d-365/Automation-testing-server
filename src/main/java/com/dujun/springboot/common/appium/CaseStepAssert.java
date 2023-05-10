/*
 * author     : dujun
 * date       : 2022/7/12 18:14
 * description: APP自动化用例执行断言操作
 */

package com.dujun.springboot.common.appium;

import com.dujun.springboot.VO.AssertConsole;
import com.dujun.springboot.common.selenium.WebAssertType;
import com.dujun.springboot.entity.WebCaseStep;
import io.appium.java_client.AppiumDriver;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Log4j2
public class CaseStepAssert {

    private final String ASSERT_SUCCESS = "断言成功";
    private final String ASSERT_ERROR = "断言失败";


    /**
     * 执行APP用例断言操作
     * @param actionKey 操作方式
     * @param driver drive
     * @param caseStep 用例步骤
     */
    public void execAction(WebAssertType actionKey, AppiumDriver driver, WebCaseStep caseStep,AssertConsole assertConsole) {
        HashMap<WebAssertType, AppAssertStep> appAssertHashMap = new HashMap<>();
        appAssertHashMap.put(WebAssertType.TITLEIS,this::title);
        appAssertHashMap.put(WebAssertType.TOASTASSERT,this::toastAssert);
        appAssertHashMap.put(WebAssertType.URLIS,this::urlAssert);
        AppAssertStep appFunInter = appAssertHashMap.get(actionKey);
        appFunInter.apply(driver,caseStep,assertConsole);
    }

    /**
     * 断言当前页面 URL
     * @param driver AppiumDriver
     * @param caseStep 用例步骤
     * @param assertConsole 断言结果
     */
    private void urlAssert(AppiumDriver driver, WebCaseStep caseStep, AssertConsole assertConsole) {
        String url = AppiumApi.getUrl(driver);
        if (url.equals(caseStep.getAssertValue())){
            assertConsole.setResult(true);
            assertConsole.setRealityValue(url);
            assertConsole.setMsg(ASSERT_SUCCESS);
        }else {
            assertConsole.setResult(false);
            assertConsole.setMsg(ASSERT_ERROR);
        }
    }

    /**
     * 断言是否有对应toast
     * @param driver AppiumDriver
     * @param caseStep 用例步骤
     */
    private void toastAssert(AppiumDriver driver, WebCaseStep caseStep,AssertConsole assertConsole) {
        String toast = AppiumApi.catch_toast(driver,caseStep.getAssertValue());
        log.debug(String.format("----------------------执行toast断言操作%1$s--------------------",caseStep.getId()));
        assertConsole.setRealityValue(toast);
       if (!toast.equals("")){
           assertConsole.setResult(true);
           assertConsole.setMsg(ASSERT_SUCCESS);
       }else {
           assertConsole.setResult(false);
           assertConsole.setMsg(ASSERT_ERROR);
       }
    }

    /**
     * 断言页面标题
     * @param driver AppiumDriver
     * @param caseStep 用例步骤
     */
    private void title(AppiumDriver driver, WebCaseStep caseStep,AssertConsole assertConsole) {
        String title = AppiumApi.getTitle(driver);
        if (title.equals(caseStep.getAssertValue())){
            assertConsole.setResult(true);
            assertConsole.setRealityValue(title);
            assertConsole.setMsg(ASSERT_SUCCESS);
        }else {
            assertConsole.setResult(false);
            assertConsole.setMsg(ASSERT_ERROR);
        }
    }

}

interface AppAssertStep{
    void apply(AppiumDriver driver, WebCaseStep caseStep,AssertConsole assertConsole);
}