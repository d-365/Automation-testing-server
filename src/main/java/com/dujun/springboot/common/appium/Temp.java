/*
 * author     : dujun
 * date       : 2022/6/21 11:15
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.common.appium;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Temp {

    public static void main(String[] args) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","192.168.2.175:5555");
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.1.0");
        capabilities.setCapability("appPackage", "com.wanqiandaikuan.xddd");
        capabilities.setCapability("appActivity", "ui.activity.SplashActivity");
        capabilities.setCapability("noReset", true);
        AndroidDriver driver = AppiumApi.getAndroidDriver("http://127.0.0.1:4723/wd/hub",capabilities);
        WebElement element1 = AppiumApi.ByXpath(driver,"//*[@resource-id=\"com.wanqiandaikuan.xddd:id/tvConfirm\"]");
        element1.click();
        String context = driver.getContext();
        System.out.println("打印context"+ context);
    }

}
