/*
 * author     : dujun
 * date       : 2022/7/1 11:27
 * description: App用例步骤执行工具类
 */

package com.dujun.springboot.common.appium;

import com.dujun.springboot.VO.UIConsole;
import com.dujun.springboot.entity.WebCaseStep;
import com.dujun.springboot.utils.BeanContext;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Objects;
@Log4j2
public class AppActionFactory {


    AppUtils appUtils = BeanContext.getApplicationContext().getBean(AppUtils.class);

    /**
     * 强制等待
     * @param driver AppiumDriver
     * @param caseStep  caseStep
     * @return  UIConsole
     */
    private UIConsole appSleep(AppiumDriver driver, WebCaseStep caseStep) {
        UIConsole uiConsole = new UIConsole();
        String execMsg;
        try {
            Thread.sleep(Long.parseLong(caseStep.getActionValue()));
            execMsg = String.format("步骤ID%s,执行成功----driver沉睡%s ms", caseStep.getId(), caseStep.getActionValue());
            uiConsole.setCode(0);
            uiConsole.setMsg(execMsg);
        } catch (InterruptedException e) {
            e.printStackTrace();
            execMsg = e.toString();
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    /**
     * 键盘输入
     * @param driver AppiumDriver
     * @param caseStep WebCaseStep
     * @return UIConsole
     */
    private UIConsole press_key(AppiumDriver driver, WebCaseStep caseStep){
        UIConsole uiConsole = new UIConsole();
        String execMsg = null;
        try {
            AppiumApi.pressKey((AndroidDriver) driver,caseStep.getActionValue());
            uiConsole.setCode(0);
            execMsg = String.format("步骤ID%1$s执行成功,输入值为:%2$s",caseStep.getId(),caseStep.getActionValue());
        }catch (Exception e){
            e.printStackTrace();
            uiConsole.setCode(1);
            execMsg = String.format("步骤ID%1$s执行失败",caseStep.getId());
        }finally {
            uiConsole.setMsg(execMsg);
        }

        return uiConsole;
    }

    /**
     * 模拟键盘删除操作
     */
    private UIConsole key_del(AppiumDriver driver, WebCaseStep caseStep){
        UIConsole uiConsole = new UIConsole();
        String execMsg = null;
        try {
            if (Objects.equals(caseStep.getActionValue(), "") ||caseStep.getActionValue()==null){
                AppiumApi.key_del((AndroidDriver) driver);
            }else {
                AppiumApi.key_del((AndroidDriver) driver, Integer.valueOf(caseStep.getActionValue()));
            }
            uiConsole.setCode(0);
            execMsg = String.format("步骤ID%1$s执行成功,进行键盘删除操作",caseStep.getId());
        }catch (Exception e){
            uiConsole.setCode(1);
            execMsg = String.format("步骤ID%1$s 键盘操作执行失败",caseStep.getId());
            e.printStackTrace();
        }finally {
            uiConsole.setMsg(execMsg);
        }
        return uiConsole;
    }

    /**
     *  捕捉toast 提示
     */
    private UIConsole catch_toast(AppiumDriver driver, WebCaseStep caseStep){
        UIConsole uiConsole = new UIConsole();
        String execMsg = null;
        try {
            String toast;
            if (caseStep.getActionValue()!=null){
                toast = AppiumApi.catch_toast(driver,caseStep.getActionValue().trim());
            }else {
                toast = AppiumApi.catch_toast(driver);
            }
            uiConsole.setCode(0);
            execMsg = String.format("步骤ID%1$S 捕捉toast成功 toast: %2$s",caseStep.getId(),toast);
        }catch (Exception e){
            uiConsole.setCode(1);
            execMsg = String.format("步骤ID%1$s 捕捉toast失败",caseStep.getId());
            e.printStackTrace();
        }finally {
            uiConsole.setMsg(execMsg);
        }
        return uiConsole;
    }

    /**
     * 切换到H5视图
     */
    private UIConsole switch_to_H5(AppiumDriver driver, WebCaseStep caseStep){
        UIConsole uiConsole = new UIConsole();
        String execMsg = null;
        try {
            AppiumApi.switch_to_H5((AndroidDriver) driver);
            uiConsole.setCode(0);
            execMsg = String.format("步骤ID%1$s执行成功：成功切换到H5视图",caseStep.getId());
        }catch (Exception e){
            uiConsole.setCode(1);
            execMsg = String.format("步骤ID%1$s 切换H5视图执行失败",caseStep.getId());
        }finally {
            uiConsole.setMsg(execMsg);
        }
        return uiConsole;
    }

    /**
     * 切换到原生Nativity视图
     */
    private UIConsole switch_to_native(AppiumDriver driver, WebCaseStep caseStep){
        UIConsole uiConsole = new UIConsole();
        String execMsg = null;
        try {
            AppiumApi.switch_to_native((AndroidDriver) driver);
            uiConsole.setCode(0);
            execMsg = String.format("步骤ID%1$s执行成功：成功切换到原生native视图",caseStep.getId());
        }catch (Exception e){
            uiConsole.setCode(1);
            execMsg = String.format("步骤ID%1$s 切换原生视图失败",caseStep.getId());
        }finally {
            uiConsole.setMsg(execMsg);
        }
        return uiConsole;
    }

    /**
     * 切换到原生Nativity视图
     */
    private UIConsole input(AppiumDriver driver, WebCaseStep caseStep){
        UIConsole uiConsole = new UIConsole();
        String execMsg = null;
        WebElement element = null;
        try {
            element = appUtils.getElement(driver,caseStep.getElementId());
        }catch (Exception e){
            e.printStackTrace();
        }
        if (element!=null){
            execMsg = String.format("步骤ID%1$s执行成功--输入%2$s",caseStep.getId(),caseStep.getActionValue());
            uiConsole.setCode(0);
            AppiumApi.send_keys(element,caseStep.getActionValue());
        }else{
            uiConsole.setCode(1);
            execMsg = String.format("步骤ID%1$s执行失败 未定位到要操作的元素",caseStep.getId());
            log.debug("-----------------------------"+execMsg+"------------------------------------");
        }
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    /**
     * 元素点击操作
     */
    private UIConsole click(AppiumDriver driver, WebCaseStep caseStep){
        UIConsole uiConsole = new UIConsole();
        String execMsg = null;
        WebElement element = null;
        try {
            element = appUtils.getElement(driver,caseStep.getElementId());
        }catch (Exception e){
            e.printStackTrace();
        }
        if (element!=null){
            execMsg = String.format("步骤ID%1$s执行成功--点击元素%2$s",caseStep.getId(),caseStep.getElementId());
            uiConsole.setCode(0);
            AppiumApi.click(element);
        }else{
            uiConsole.setCode(1);
            execMsg = String.format("步骤ID%1$s执行失败 未定位到要操作的元素",caseStep.getId());
            log.debug("-----------------------------"+execMsg+"------------------------------------");
        }
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    /**
     * 执行APPAction操作
     * @param actionKey 操作方式
     * @param driver drive
     * @param caseStep 用例步骤
     * @return 操作结果
     */
    public UIConsole execAction(AppActionEnum actionKey, AppiumDriver driver, WebCaseStep caseStep) {
        HashMap<AppActionEnum, AppFunInter> appFunInterHashMap = new HashMap<>();
        appFunInterHashMap.put(AppActionEnum.DEFAULT, (driver1, caseStep1) -> new UIConsole(1,"未找到对应的Action"));
        appFunInterHashMap.put(AppActionEnum.SLEEP, this::appSleep);
        appFunInterHashMap.put(AppActionEnum.PRESSKEY, this::press_key);
        appFunInterHashMap.put(AppActionEnum.KEY_DEL, this::key_del);
        appFunInterHashMap.put(AppActionEnum.CATCH_TOAST, this::catch_toast);
        appFunInterHashMap.put(AppActionEnum.SWITCH_TO_H5, this::switch_to_H5);
        appFunInterHashMap.put(AppActionEnum.SWITCH_TO_NATIVE, this::switch_to_native);
        appFunInterHashMap.put(AppActionEnum.CLICK, this::click);
        appFunInterHashMap.put(AppActionEnum.INPUT, this::input);
        AppFunInter appFunInter = appFunInterHashMap.get(actionKey);
        return appFunInter.apply(driver,caseStep);
    }

}


@FunctionalInterface
interface AppFunInter{

    UIConsole apply(AppiumDriver driver, WebCaseStep caseStep);
}