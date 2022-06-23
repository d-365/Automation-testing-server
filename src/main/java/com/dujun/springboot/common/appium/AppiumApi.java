/*
 * author     : dujun
 * date       : 2022/6/22 14:02
 * description: AppiumAPI
 */

package com.dujun.springboot.common.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.Set;

public class AppiumApi {


    /**
     * 获取AndroidDriver
     * @return androidDriver
     */
    public static AndroidDriver getAndroidDriver(String url,DesiredCapabilities capabilities){
        AndroidDriver androidDriver = null;
        try {
            androidDriver =  new AndroidDriver(new URL(url),capabilities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return androidDriver;
    }

    /**
     * 获取IOSDriver
     * @return androidDriver
     */
    public static IOSDriver getIosDriver(String url,DesiredCapabilities capabilities){
        IOSDriver IosDriver = null;
        try {
            IosDriver =  new IOSDriver(new URL(url),capabilities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return IosDriver;
    }

    /**
     * 根据ID进行定位可指定显示等待方式
     * @param driver AppiumDriver
     * @param id ID
     * @param expected 显示等待方式
     * @return WebElement元素
     */
    public static WebElement ById(AppiumDriver driver, String id,MyExpected expected){
        WebElement element = null;
        try {
            element = new WebDriverWait(driver,3000).until(MyExpectedConditions.run(By.id(id),expected));
        }catch (Exception e){
            e.printStackTrace();
        }
        return element;
    }

    /**
     * 根据ID进行定位 默认 显示等待： 元素可见
     * @param driver AppiumDriver
     * @param id ID
     * @return WebElement元素
     */
    public static WebElement ById (AppiumDriver driver,String id){
        WebElement element = null;
        try {
            element = new WebDriverWait(driver,3000).until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return element;
    }

    /**
     * 根据ID进行定位可指定显示等待方式
     * @param driver AppiumDriver
     * @param xpath xpath
     * @param expected 显示等待方式
     * @return WebElement元素
     */
    public static WebElement ByXpath(AppiumDriver driver, String xpath,MyExpected expected){
        WebElement element = null;
        try {
            element = new WebDriverWait(driver,3000).until(MyExpectedConditions.run(By.xpath(xpath),expected));
        }catch (Exception e){
            e.printStackTrace();
        }
        return element;
    }

    /**
     * 根据ID进行定位 默认 显示等待： 元素可见
     * @param driver AppiumDriver
     * @param xpath xpath
     * @return WebElement元素
     */
    public static WebElement ByXpath (AppiumDriver driver,String xpath){
        WebElement element = null;
        try {
            element = new WebDriverWait(driver,3000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return element;
    }

    /**
     * 万能元素定位 可指定显示等待方式
     * @param driver  driver
     * @param by 元素定位方式
     * @param expected 显示等待类型
     * @return WebElement
     */
    public static WebElement ElementLocation(AppiumDriver driver,By by ,MyExpected expected){
        WebElement element = null;
        try {
            element = new WebDriverWait(driver,3000).until(MyExpectedConditions.run(by,expected));
        }catch (Exception e){
            e.printStackTrace();
        }
        return element;
    }

    /**
     * AndroidDriver模拟键盘输入
     * @param driver driver
     * @param codes AndroidKey
     */
    public static void pressKey(AndroidDriver driver,String ...codes){
        for (String code : codes) {
            try {
                driver.pressKey(new KeyEvent(AndroidKey.valueOf(code)));
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    /**
     * 向左滑动
     * @param driver AppiumDriver
     */
    public void swipe_left(AppiumDriver driver){
        Integer width = driver.manage().window().getSize().width;
        Integer height = driver.manage().window().getSize().height;
        TouchActions action = new TouchActions(driver);
    }

    /**
     * 捕捉toast提示
     * @param driver  AppiumDriver
     * @return String
     */
    public static String catch_toast(AppiumDriver driver){
        String message = "//*[@class='android.widget.Toast']";
        //String message = "//*[contains(@text,"xxxx")]";
        return ByXpath(driver,message).getText();
    }

    /**
     * 坐标定位
     * @param driver
     * @param pointX
     * @param pointY
     */
    public static  void byRelative(AppiumDriver driver,Integer pointX, Integer pointY){
        Integer width = driver.manage().window().getSize().width;
        Integer height = driver.manage().window().getSize().height;
        // 比例系数
        Integer x = pointX / width;
        Integer y = pointY / height;

    }

    /**
     * 切换到H5视图
     * @param driver AndroidDriver
     */
    public static void switch_to_H5(AndroidDriver driver){
//        String context = driver.getContext();
        Set<String> contextNames = driver.getContextHandles();
        driver.context(String.valueOf(contextNames.toArray()[1]));
    }

    /**
     * 切换到 原生NATIVE_APP视图
     * @param driver AndroidDriver
     */
    public static void switch_to_native(AndroidDriver driver){
        driver.context("NATIVE_APP");
    }

    /**
     * 键盘删除操作 多次
     * @param driver AndroidDriver
     * @param num 删除次数
     */
    public static void key_del(AndroidDriver driver,Integer num){
        for (int i = 0; i < num; i++) {
            driver.pressKey(new KeyEvent(AndroidKey.valueOf("DEL")));
        }
    }

    /**
     * 键盘删除操作 单次
     * @param driver AndroidDriver
     */
    public static void key_del(AndroidDriver driver){
        driver.pressKey(new KeyEvent(AndroidKey.valueOf("DEL")));
    }

    /**
     * 判断元素是否可见
     * @param element WebElement
     * @return true or false
     */
    public static boolean isDisplayed(WebElement element){
        return element.isDisplayed();
    }


}
