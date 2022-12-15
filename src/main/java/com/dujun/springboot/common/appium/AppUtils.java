/*
 * author     : dujun
 * date       : 2022/7/1 10:31
 * description: App自动化用例执行
 */

package com.dujun.springboot.common.appium;

import com.alibaba.fastjson.JSONObject;
import com.dujun.springboot.VO.AssertConsole;
import com.dujun.springboot.VO.UIConsole;
import com.dujun.springboot.common.selenium.WebAssertType;
import com.dujun.springboot.entity.AppConfig;
import com.dujun.springboot.entity.MobilePhone;
import com.dujun.springboot.entity.PageElement;
import com.dujun.springboot.entity.WebCaseStep;
import com.dujun.springboot.mapper.AppConfigMapper;
import com.dujun.springboot.mapper.MobilePhoneMapper;
import com.dujun.springboot.mapper.PageElementMapper;
import com.dujun.springboot.mapper.WebCaseStepMapper;
import com.dujun.springboot.utils.cmdTaskUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static com.dujun.springboot.common.selenium.runWebPlan.portStart;

@Component
@Log4j2
public class AppUtils {

    @Resource
    private CaseStepAssert caseAssert;

    @Resource
    private WebCaseStepMapper webCaseStepMapper;

    @Resource
    private PageElementMapper pageElementMapper;

    @Resource
    private AppConfigMapper appConfigMapper;

    @Resource
    private MobilePhoneMapper mobilePhoneMapper;

    boolean AppiumServerStatus;
    Integer appiumStartCount = 0;

    /**
     * 执行用例操作步骤
     * @param driver Appium
     * @param caseStep 用例步骤
     * @return 用例步骤执行结果
     */
    public UIConsole execAppCaseStep(AppiumDriver driver, WebCaseStep caseStep){
        UIConsole uiConsole;
        AppActionEnum actionKey;
        try {
            actionKey = AppActionEnum.valueOf(webCaseStepMapper.getActionKey(caseStep.getActionId()).trim().toUpperCase());
        }catch (Exception e){
            e.printStackTrace();
            actionKey = AppActionEnum.DEFAULT;
        }
        uiConsole = new AppActionFactory().execAction(actionKey,driver,caseStep);
        return uiConsole;
    }

    // 获取元素对象
    public  WebElement getElement(AppiumDriver driver, Integer elementId){
        WebElement element;
        PageElement pageElement = pageElementMapper.selectById(elementId);
        String locationWay = pageElement.getLocationWay();
        String conditions = pageElement.getConditions();
        String locationValue = pageElement.getLocationValue();
        switch (locationWay){
            case "id":
                if (conditions == null || Objects.equals(conditions, "")){
                    element = AppiumApi.ById(driver,locationValue);
                }else {
                    element = AppiumApi.findElement(driver,conditions, By.id(locationValue));
                }
                break;
            case "xpath":
                if (conditions == null || Objects.equals(conditions, "")){
                    element = AppiumApi.ByXpath(driver,locationValue);
                } else {
                    element = AppiumApi.findElement(driver,conditions, By.xpath(locationValue));
                }
                break;
            default:
                return null;
        }
        return element;
    }

    /**
     * 开启Appium服务
     * @return boolean
     */
    public Boolean AppiumStart(){
        // 开启Appium服务
        AppiumServerStatus = portStart("0.0.0.0",4723);
        log.debug(AppiumServerStatus);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (appiumStartCount<=2){
            if (!AppiumServerStatus){
                cmdTaskUtils.execCommand("cmd /k start appium");
                appiumStartCount++;
                AppiumStart();
            }
        }else {
            appiumStartCount = 0;
        }
        return AppiumServerStatus;
    }

    /**
     *  Appium启动配置
     * @return DesiredCapabilities 配置列表
     */
    public DesiredCapabilities getDesiredCapabilities(Integer appConId,Integer phoneId){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // 处理基本配置信息
        AppConfig appConfigs = appConfigMapper.selectById(appConId);
        MobilePhone mobilePhone = mobilePhoneMapper.selectById(phoneId);
        capabilities.setCapability("deviceName",appConfigs.getName());
        capabilities.setCapability("automationName", appConfigs.getAutomationName());
        capabilities.setCapability("appPackage", appConfigs.getAppPackage());
        capabilities.setCapability("appActivity", appConfigs.getAppActivity());
        capabilities.setCapability("noReset", !appConfigs.getNoReset());
        capabilities.setCapability("platformName", mobilePhone.getPlatForm());
        capabilities.setCapability("platformVersion", mobilePhone.getPlatVersion());
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        // 处理Android特有信息
        if (mobilePhone.getPlatForm().contains("ANDROID")){
            capabilities.setCapability("autoGrantPermissions",true);
        }
        //处理其他配置信息
        List<JSONObject> appConOthers = appConfigs.getOthers();
        if (appConOthers.size()!=0){
            for (JSONObject appConOther : appConOthers) {
                if (!appConOther.getString("key").equals("")||!appConOther.getString("value").equals("")){
                    capabilities.setCapability(appConOther.getString("key"),appConOther.getString("value"));
                }
            }
        }
        return capabilities;
    }

    /**
     * 获取对应AppiumDriver
     *
     * @param capabilities appium配置
     * @return AppiumDriver
     */
    public AppiumDriver getDriver(String address, DesiredCapabilities capabilities) {
        if (address.equals("")) {
            address = "127.0.0.1";
        }
        String platform = capabilities.getPlatformName().toString();
        AppiumDriver driver = null;
        String remoteIp = String.format("http://%1$s:4723/wd/hub", address);
        try {
            if (Objects.equals(platform.toUpperCase(), "ANDROID")) {
                driver = AppiumApi.getAndroidDriver(remoteIp, capabilities);
            } else if (Objects.equals(platform.toUpperCase(), "IOS")) {
                driver = AppiumApi.getIosDriver(remoteIp, capabilities);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return driver;
    }

    /**
     * 执行断言操作
     * @param driver AppiumDriver
     * @param caseStep 用例步骤
     * @return 断言结果
     */
    public AssertConsole execStepAssert(AppiumDriver driver, WebCaseStep caseStep){
        AssertConsole assertConsole = new AssertConsole();
        WebAssertType assertType;
        try {
            assertType = WebAssertType.valueOf(caseStep.getAssertType().trim().toUpperCase());
            if (caseStep.getAssertValue()==null){
                assertConsole.setMsg("断言的预期值不能为空");
                assertConsole.setResult(false);
            }else {
                caseAssert.execAction(assertType,driver,caseStep,assertConsole);
            }
        }catch (Exception e){
            assertConsole.setMsg("断言方式不存在");
            assertConsole.setResult(false);
        }
        assertConsole.setExpectValue(caseStep.getAssertValue());
        assertConsole.setAssertType(caseStep.getAssertType());
        assertConsole.setStepId(caseStep.getId());
        return assertConsole;
    }



}
