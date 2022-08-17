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
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Objects;

@Log4j2
public class AppActionFactory {

    public String exec_Failed(WebCaseStep caseStep) {
        return String.format("");
    }


    AppUtils appUtils = BeanContext.getApplicationContext().getBean(AppUtils.class);

    /**
     * 强制等待
     *
     * @param driver   AppiumDriver
     * @param caseStep caseStep
     * @return UIConsole
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
        String execMsg;
        String toast;
        if (caseStep.getActionValue()!=null){
            toast = AppiumApi.catch_toast(driver,caseStep.getActionValue().trim());
        }else {
            toast = AppiumApi.catch_toast(driver);
        }
        if (!Objects.equals(toast, "")){
            uiConsole.setCode(0);
            execMsg = String.format("步骤ID%1$S 捕捉toast成功 toast: %2$s",caseStep.getId(),toast);
        }else {
            uiConsole.setCode(1);
            execMsg = String.format("步骤ID%1$s 捕捉toast失败",caseStep.getId());
        }
        uiConsole.setMsg(execMsg);
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
        String execMsg;
        WebElement element = null;
        try {
            element = appUtils.getElement(driver, caseStep.getElementId());
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        if (element != null) {
            execMsg = String.format("步骤ID%1$s执行成功--点击元素%2$s", caseStep.getId(), caseStep.getElementId());
            uiConsole.setCode(0);
            AppiumApi.click(element);
        } else {
            uiConsole.setCode(1);
            execMsg = String.format("步骤ID%1$s执行失败,未定位到要操作的元素", caseStep.getId());
            log.debug("-----------------------------" + execMsg + "------------------------------------");
        }
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    /**
     * 元素文本清除操作
     */
    private UIConsole clear(AppiumDriver driver, WebCaseStep caseStep) {
        UIConsole uiConsole = new UIConsole();
        String execMsg;
        WebElement element = appUtils.getElement(driver, caseStep.getElementId());
        if (element != null) {
            try {
                AppiumApi.clear(element);
                execMsg = String.format("步骤ID%1$s执行成功--input文本清除%2$s", caseStep.getId(), caseStep.getElementId());
                uiConsole.setCode(0);
            } catch (Exception e) {
                e.printStackTrace();
                execMsg = String.format("步骤ID%1$s执行失败--input文本清除失败%2$s", caseStep.getId(), caseStep.getElementId());
                uiConsole.setCode(1);
            }
        } else {
            uiConsole.setCode(1);
            execMsg = String.format("步骤ID%1$s执行失败 未定位到要操作的元素", caseStep.getId());
            log.debug("-----------------------------" + execMsg + "------------------------------------");
        }
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    /**
     * 向上滑动手机屏幕
     *
     * @param driver   AppiumDriver
     * @param caseStep 用例步骤
     * @return 滑动结果
     */
    private UIConsole swipeTop(AppiumDriver driver, WebCaseStep caseStep) {
        UIConsole uiConsole = new UIConsole();
        String execMsg;
        try {
            AppiumApi.swipe_top(driver);
            execMsg = String.format("步骤ID%1$s执行成功--屏幕向上滑动", caseStep.getId());
            uiConsole.setCode(0);
        } catch (Exception e) {
            e.printStackTrace();
            execMsg = String.format("步骤ID%1$s执行失败- - 屏幕未向上滑动", caseStep.getId());
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    /**
     * 向下滑动手机屏幕
     *
     * @param driver   AppiumDriver
     * @param caseStep 用例步骤
     * @return 滑动结果
     */
    private UIConsole swipeBottom(AppiumDriver driver, WebCaseStep caseStep) {
        UIConsole uiConsole = new UIConsole();
        String execMsg;
        try {
            AppiumApi.swipe_bottom(driver);
            execMsg = String.format("步骤ID%1$s执行成功--屏幕向下滑动", caseStep.getId());
            uiConsole.setCode(0);
        } catch (Exception e) {
            e.printStackTrace();
            execMsg = String.format("步骤ID%1$s执行失败- - 屏幕未向下滑动", caseStep.getId());
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    /**
     * 向左滑动手机屏幕
     *
     * @param driver   AppiumDriver
     * @param caseStep 用例步骤
     * @return 滑动结果
     */
    private UIConsole swipeLeft(AppiumDriver driver, WebCaseStep caseStep) {
        UIConsole uiConsole = new UIConsole();
        String execMsg;
        try {
            AppiumApi.swipe_left(driver);
            execMsg = String.format("步骤ID%1$s执行成功--屏幕向左滑动", caseStep.getId());
            uiConsole.setCode(0);
        } catch (Exception e) {
            e.printStackTrace();
            execMsg = String.format("步骤ID%1$s执行失败- - 屏幕未向左滑动", caseStep.getId());
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    /**
     * 向右滑动手机屏幕
     *
     * @param driver   AppiumDriver
     * @param caseStep 用例步骤
     * @return 滑动结果
     */
    private UIConsole swipeRight(AppiumDriver driver, WebCaseStep caseStep) {
        UIConsole uiConsole = new UIConsole();
        String execMsg;
        try {
            AppiumApi.swipe_right(driver);
            execMsg = String.format("步骤ID%1$s执行成功--屏幕向右滑动", caseStep.getId());
            uiConsole.setCode(0);
        } catch (Exception e) {
            e.printStackTrace();
            execMsg = String.format("步骤ID%1$s执行失败- - 屏幕未向右滑动", caseStep.getId());
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    /**
     * 坐标进行定位点击
     *
     * @param driver   AppiumDriver
     * @param caseStep 用例步骤
     */
    private UIConsole tapClick(AppiumDriver driver, WebCaseStep caseStep) {
        UIConsole uiConsole = new UIConsole();
        String execMsg;
        if (Objects.equals(caseStep.getActionValue(), "") || caseStep.getActionValue() == null) {
            execMsg = String.format("步骤ID%1$s执行失败,Action值不能为空", caseStep.getId());
        } else {
            try {
                String[] xy;
                xy = caseStep.getActionValue().split(",");
                if (xy[0] == null || xy[1] == null) {
                    execMsg = String.format("步骤ID%1$s执行失败坐标解析失败", caseStep.getId());
                    uiConsole.setCode(1);
                } else {
                    AppiumApi.TapClick(driver, Double.parseDouble(xy[0]), Double.parseDouble(xy[1]));
                    execMsg = String.format("步骤ID%1$s执行成功--坐标点击", caseStep.getId());
                    uiConsole.setCode(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
                execMsg = String.format("步骤ID%1$s执行失败-- 坐标点击", caseStep.getId());
                uiConsole.setCode(1);
            }
        }
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    /**
     * 执行JS命令
     *
     * @param driver   AppiumDriver
     * @param caseStep 用例步骤
     */
    private UIConsole execJs(AppiumDriver driver, WebCaseStep caseStep) {
        UIConsole uiConsole = new UIConsole();
        String execMsg;
        if (Objects.equals(caseStep.getActionValue(), "") || caseStep.getActionValue() == null) {
            execMsg = String.format("步骤ID%1$s执行失败,JS命令不能为空", caseStep.getId());
        } else {
            try {
                AppiumApi.execJs(driver, caseStep.getActionValue());
                execMsg = String.format("步骤ID%1$s执行JS命令成功", caseStep.getId());
                uiConsole.setCode(0);
            } catch (Exception e) {
                e.printStackTrace();
                execMsg = String.format("步骤ID%1$s执行JS命令失败", caseStep.getId());
                uiConsole.setCode(1);
            }
        }
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    /**
     * 执行APPAction操作
     *
     * @param actionKey 操作方式
     * @param driver    drive
     * @param caseStep  用例步骤
     * @return 操作结果
     */
    public UIConsole execAction(AppActionEnum actionKey, AppiumDriver driver, WebCaseStep caseStep) {
        HashMap<AppActionEnum, AppFunInter> appFunInterHashMap = new HashMap<>();
        appFunInterHashMap.put(AppActionEnum.DEFAULT, (driver1, caseStep1) -> new UIConsole(1, "未找到对应的Action"));
        appFunInterHashMap.put(AppActionEnum.SLEEP, this::appSleep);
        appFunInterHashMap.put(AppActionEnum.PRESSKEY, this::press_key);
        appFunInterHashMap.put(AppActionEnum.KEY_DEL, this::key_del);
        appFunInterHashMap.put(AppActionEnum.CATCH_TOAST, this::catch_toast);
        appFunInterHashMap.put(AppActionEnum.SWITCH_TO_H5, this::switch_to_H5);
        appFunInterHashMap.put(AppActionEnum.SWITCH_TO_NATIVE, this::switch_to_native);
        appFunInterHashMap.put(AppActionEnum.CLICK, this::click);
        appFunInterHashMap.put(AppActionEnum.INPUT, this::input);
        appFunInterHashMap.put(AppActionEnum.CLEAR, this::clear);
        appFunInterHashMap.put(AppActionEnum.SWIPE_TOP, this::swipeTop);
        appFunInterHashMap.put(AppActionEnum.SWIPE_BOTTOM, this::swipeBottom);
        appFunInterHashMap.put(AppActionEnum.SWIPE_LEFT, this::swipeLeft);
        appFunInterHashMap.put(AppActionEnum.SWIPE_RIGHT, this::swipeRight);
        appFunInterHashMap.put(AppActionEnum.TAP_CLICK, this::tapClick);
        appFunInterHashMap.put(AppActionEnum.EXECJS, this::execJs);
        AppFunInter appFunInter = appFunInterHashMap.get(actionKey);
        return appFunInter.apply(driver, caseStep);
    }

}


@FunctionalInterface
interface AppFunInter{

    UIConsole apply(AppiumDriver driver, WebCaseStep caseStep);
}