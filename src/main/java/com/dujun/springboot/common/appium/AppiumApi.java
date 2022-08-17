/*
 * author     : dujun
 * date       : 2022/6/22 14:02
 * description: AppiumAPI
 */

package com.dujun.springboot.common.appium;

import com.dujun.springboot.common.selenium.MyConditions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.Collections;
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
            element = new WebDriverWait(driver, 5).until(MyExpectedConditions.run(By.id(id), expected));
        } catch (TimeoutException e) {
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
            element = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        } catch (TimeoutException e) {
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
            element = new WebDriverWait(driver, 3).until(MyExpectedConditions.run(By.xpath(xpath), expected));
        } catch (TimeoutException e) {
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
            element = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        } catch (TimeoutException e) {
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
            element = new WebDriverWait(driver, 3).until(MyExpectedConditions.run(by, expected));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return element;
    }

    /**
     * 获取WebElement元素 -- 万能 ExpectedConditions
     */
    public static WebElement findElement(AppiumDriver driver, String conditions, By by) {
        WebElement element = null;
        MyConditions myConditions = MyConditions.valueOf(conditions.trim());
        try {
            switch (myConditions) {
                case elementToBeClickable:
                    element = new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(by));
                    break;
                case visibilityOfElementLocated:
                default:
                    element = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(by));
                    break;
            }
        } catch (TimeoutException e) {
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
     * 元素点击操作
     * @param element WebElement
     */
    public static void click(WebElement element) {
        element.click();
    }

    /**
     * 元素输入
     *
     * @param element WebElement
     */
    public static void send_keys(WebElement element, String... code) {
        element.sendKeys(code);
    }

    /**
     * 向上滑动
     *
     * @param driver AppiumDriver
     */
    public static void swipe_top(AppiumDriver driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), width / 2, height / 2));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), width / 2, 0));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(dragNDrop));
    }

    /**
     * 向下滑动
     *
     * @param driver AppiumDriver
     */
    public static void swipe_bottom(AppiumDriver driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), width / 2, height / 2));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), width / 2, height));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(dragNDrop));
    }

    /**
     * 向左滑动
     *
     * @param driver AppiumDriver
     */
    public static void swipe_left(AppiumDriver driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), width / 2, height / 2));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), 0, height / 2));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(dragNDrop));
    }

    /**
     * 向右滑动
     *
     * @param driver AppiumDriver
     */
    public static void swipe_right(AppiumDriver driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 0, height / 2));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), width / 2, height));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(dragNDrop));
    }

    /**
     * 捕捉toast提示
     *
     * @param driver AppiumDriver
     * @return String
     */
    public static String catch_toast(AppiumDriver driver) {
        String message = "//*[@class='android.widget.Toast']";
        try {
            WebElement element = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath(message)));
            return element.getText();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 捕捉toast提示
     * @param driver  AppiumDriver
     * @return String
     */
    public static String catch_toast(AppiumDriver driver,String text){
        String messages = String.format("//*[contains(@text,'%1$s')]", text);
        try {
            WebElement element = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath(messages)));
            return element.getText();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据界面坐标点击元素
     *
     * @param driver 1
     * @param pointX 横坐标比例系数（X实际坐标 / 手机x轴长度）
     * @param pointY 纵坐标比例系数 （Y实际坐标 / 手机Y轴长度）
     */
    public static void TapClick(AppiumDriver driver, Double pointX, Double pointY) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        // 实际坐标
        int x = (int) (pointX * width);
        int y = (int) (pointY * height);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(dragNDrop));
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

    /**
     * 获取当前页面标题
     * @param driver AppiumDriver
     * @return 页面标题
     */
    public static String getTitle(AppiumDriver driver) {
        return driver.getTitle();
    }

    /**
     * 获取当前页面URL
     *
     * @param driver AppiumDriver
     * @return 当前页面URL
     */
    public static String getUrl(AppiumDriver driver) {
        return driver.getCurrentUrl();
    }

    /**
     * 清除文本元素
     *
     * @param element WebElement
     */
    public static void clear(WebElement element) {
        element.clear();
    }

    /**
     * 执行JS脚本
     *
     * @param driver  AppiumDriver
     * @param command JS命令
     */
    public static void execJs(AppiumDriver driver, String command){
        driver.execute(command);
    }

}
