/*
 * author     : dujun
 * date       : 2022/4/1 17:33
 * description: selenium方法封装
 */

package com.dujun.springboot.common.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class MySelenium {

    // 获取操作Api
    static Actions actions;

    // 获取本机指定浏览器WebDriver
    public static WebDriver getDriver(String browser) {
        if (browser == null) {
            browser = "chrome";
        }
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriver driver = new FirefoxDriver(firefoxOptions);
                return driver;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new ChromeDriver(chromeOptions);
                return driver;

        }
    }

    // 获取远程Webdriver
    public static WebDriver getRemoteDriver(String remoteUrl,String browser) {
        if (browser == null) {
            browser = "chrome";
        }
        WebDriver driver = null;

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                try {
                    driver = new RemoteWebDriver(new URL(remoteUrl),firefoxOptions);
                    driver.manage().window().maximize();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return driver;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                try {
                    System.out.println(remoteUrl);
                    driver = new RemoteWebDriver(new URL(remoteUrl),chromeOptions);
                    driver.manage().window().maximize();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return driver;

        }
    }

    /**
     * 浏览器中读取当前页面标题：
     */
    public static String  getTitle(WebDriver driver){
        return driver.getTitle();
    }

    /**
     * 从浏览器的地址栏中读取当前 URL
     */
    public static String getCurrentUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public static String getPageSource(WebDriver driver){
        return  driver.getPageSource();
    }

    /**
     * 打开网址
     */
    public static void get(WebDriver driver,String url) throws Exception{
        driver.get(url);
    }

    /**
     * 后退网址
     */
    public static void back(WebDriver driver){
        driver.navigate().back();
    }

    /**
     * 前进网址
     */
    public static void forward(WebDriver driver){
        driver.navigate().forward();
    }

    /**
     * 刷新网址
     */
    public static void refresh(WebDriver driver){
        driver.navigate().refresh();
    }

    /**
     * 关闭选项卡或窗口
     */
    public static void close(WebDriver driver){
        if (driver!=null){
            driver.close();
        }
    }

    /**
     * 退出浏览器
     * 关闭与该 WebDriver 会话关联的所有窗口和选项卡
     * 关闭浏览器进程
     * 关闭后台驱动进程
     * 通知 Selenium Grid 该浏览器不再使用，以便其他会话可以使用它（如果您使用的是 Selenium Grid）
     */
    public static void quite(WebDriver driver){
        if (driver !=null){
            driver.quit();
        }
    }

    // 获取当前DOM的Alert弹窗
     public static  Alert getAlert(WebDriver driver){
        return driver.switchTo().alert();
    }

    /**
     * Alert获取警告弹窗文本--- Confirm 确认弹窗
     */
    public static String getAlertText(Alert alert){
        return alert.getText();
    }

    /**
     * Alert输入框发送文本
     */
    public static void sendAlertKeys(Alert alert,String Keys){
        alert.sendKeys(Keys);
    }

    /**
     * Alert确认
     */
    public static void acceptAlert(Alert alert){
         alert.accept();
    }

    /**
     * 取消(Confirm,Warning)弹窗
     */
    public static void dismissAlert(Alert alert){
        alert.dismiss();
    }

    /**
     * 将 cookie 添加到当前浏览上下文中
     */
    public static void addCookie(WebDriver driver,Cookie cookie){
        driver.manage().addCookie(cookie);
    }

    /**
     * 获取命名 Cookie
     */
    public static Cookie getCookieNamed(WebDriver driver,String cookieName){
        return driver.manage().getCookieNamed(cookieName);
    }

    /**
     * 获取所有Cookie集合
     */
    public static Set<Cookie> getCookies(WebDriver driver){
        return driver.manage().getCookies();
    }

    /**
     * 删除具名Cookie
     * **/
    public static void deleteCookieNamed(WebDriver driver,String cookieName){
        driver.manage().deleteCookieNamed(cookieName);
    }

    /**
     * 通过Cookie对象删除对应Cookie
     */
    public static void deleteCookie(WebDriver driver,Cookie cookie){
        driver.manage().deleteCookie(cookie);
    }

    /**
     * 删除当前浏览器对象上下文中所有Cookies
     */
    public static void deleteAllCookies(WebDriver driver){
        driver.manage().deleteAllCookies();
    }

    /**
     * 框架切换Frame --WebElement
     */
    public static void switchToFrame(WebDriver driver,WebElement element){
        driver.switchTo().frame(element);
    }

    /**
     * 框架切换Frame --FrameName,FrameId
     */
    public static void switchToFrame(WebDriver driver,String name){
        driver.switchTo().frame(name);
    }

    /**
     * 框架切换Frame --FrameIndex
     */
    public static void switchToFrame(WebDriver driver,int index){
        driver.switchTo().frame(index);
    }

    /**
     * 离开 iframe 或框架集，切换回默认内容
     */
    public static void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    /**
     * 浏览器设置窗口位置
     */
    public static void setPosition(WebDriver driver,int x ,int y){
        driver.manage().window().setPosition(new Point(x, y));
    }

    /**
     * 最大化窗口
     */
    public static void maximize(WebDriver driver){
        driver.manage().window().maximize();
    }

    /**
     * 最小化窗口
     */
    public static void minimize(WebDriver driver){
        driver.manage().window().minimize();
    }

    /**
     * 浏览器窗口充满屏幕
     */
    public static void fullscreen(WebDriver driver){
        driver.manage().window().fullscreen();
    }

    /**
     * 截图保存至电脑 截取整个屏幕
     * @param descPath 保存的路径
     */
    public static void getScreenshotAs(WebDriver driver,String descPath){
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(descPath));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * 截图保存至电脑 截取元素周围上下文对象
     * @param descPath 保存的路径
     */
    public static File getScreenshotAs(WebElement element,String descPath){
        File scrFile = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(descPath));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return scrFile;
    }

    /**
     * 执行JavaScript脚本
     */
    public static void executeScript(WebDriver driver,String script ){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript(script);
    }

    /**
     *在浏览器中打印当前页面,需要 Chromium 浏览器处于无头模式
     */
    public static  String getContent(WebDriver driver){
        PrintsPage printer = (PrintsPage) driver;
        PrintOptions printOptions = new PrintOptions();
        printOptions.setPageRanges("1-2");
        Pdf pdf = printer.print(printOptions);
        return pdf.getContent();
    }

    /**
     * 传统定位器 --className
     */
    public static WebElement ByClassName(WebDriver driver,String className){
        return driver.findElement(By.className(className));
    }

    /**
     * 传统定位器 --id
     */
    public static WebElement ById(WebDriver driver,String id){
        return driver.findElement(By.id(id));
    }

    /**
     * 传统定位器 --name
     */
    public static WebElement ByName(WebDriver driver,String name){
        return driver.findElement(By.name(name));
    }

    /**
     * 传统定位器 --cssSelector
     */
    public static WebElement ByCssSelector(WebDriver driver,String cssSelector){
        return driver.findElement(By.cssSelector(cssSelector));
    }

    /**
     * 传统定位器 --link—text
     */
    public static WebElement ByLinkText(WebDriver driver,String linkText){
        return driver.findElement(By.linkText(linkText));
    }

    /**
     * 传统定位器 --PartialLinkText
     */
    public static WebElement ByPartialLinkText(WebDriver driver,String PartialLinkText){
        return driver.findElement(By.partialLinkText(PartialLinkText));
    }

    /**
     * 传统定位器 --tagName
     */
    public static WebElement ByTagName(WebDriver driver,String tagName){
        return driver.findElement(By.tagName(tagName));
    }

    /**
     * 传统定位器 --xpath
     */
    public static WebElement ByXpath(WebDriver driver,String xpath){
        return new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    /**
     * 获取WebElement元素 -- 万能 ExpectedConditions
     */
    public static WebElement findElement(WebDriver driver,String conditions, By by){
        WebElement element = null;
        MyConditions myConditions = MyConditions.valueOf(conditions.trim());
        switch (myConditions){
            case elementToBeClickable:
                element = new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(by));
                break;
            case visibilityOfElementLocated:
            default:
                element = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(by));
                break;
        }
        return element;
    }

    /*
     * 相对定位器 --
     */
    public void  test(WebDriver driver){
        driver.notify();

    }

    /**
     * 切换到焦点元素
     * 跟踪（或）查找在当前浏览上下文中具有焦点的 DOM 元素
     */
    public static WebElement activeElement(WebDriver driver){
        return driver.switchTo().activeElement();
    }

    /**
     * 点击元素
     * 元素单击命令在元素的中心执行
     */
    public static void click(WebElement element){
        element.click();
    }

    /**
     * 发送文本
     * 元素是具有text类型的表单的输入元素或具有content-editable属性的元素。
     */
    public static void sendKeys(WebElement element,String text){
        element.sendKeys(text);
    }

    /**
     * element clear 命令重置元素的内容。这要求元素是可编辑和可重置的。
     * 元素是具有text类型的表单的输入元素或具有content-editable属性的元素。
     */
    public static void clear(WebElement element){
        element.clear();
    }

    /**
     * 元素是否可见
     */
    public static Boolean isDisplayed(WebElement element){
        return element.isDisplayed();
    }

    /**
     * 元素是否启用
     */
    public static Boolean isEnabled(WebElement element){
        return element.isEnabled();
    }

    /**
     * 元素是否被选择
     */
    public static Boolean isSelected(WebElement element){
        return element.isSelected();
    }

    /**
     * 获取在当前浏览上下文中具有焦点的引用元素的TagName 。
     */
    public static String getTagName(WebElement element){
        return element.getTagName();
    }

    /**
     * 它用于获取引用元素的尺寸和坐标。
     * 元素左上角的 X 轴位置
     * 元素左上角的 y 轴位置
     * 元素的高度
     * 元素的宽度
     */
    public static Rectangle getRect(WebElement element){
        return element.getRect();
    }

    /**
     * 检索当前浏览上下文中元素的指定计算样式属性的值。
     */
    public static String getCssValue(WebElement element,String key){
        return element.getCssValue(key);
    }

    /**
     * 检索指定元素的呈现文本。
     */
    public static String getText(WebElement element){
       return element.getText();
    }

    /**
     * 获取selectObject对象
     * 有三种方法可以从上述元素中选择第一个选项 selectByIndex(1);selectByValue("value1");selectByVisibleText("Bread");
     * 选择了哪些选项: getAllSelectedOptions(); getFirstSelectedOption();
     * 取消选择任何元素: deselectByIndex(1);deselectByValue("value1"); deselectByVisibleText("Bread");deselectAll();
     * 是否多选：isMultiple()
     */
    public static Select getSelect(WebElement element){
        return new Select(element);
    }

    /**
     *  远程driver
     */
    public static void remoteWebDriver(WebDriver driver,Capabilities capabilities){
        try {
           driver = new RemoteWebDriver(new URL("http://www.example.com"),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 远程 WebDriver 可以在运行时自动将文件从本地机器传输到远程 Web 服务器
     */
    public static void setFileDetector(WebDriver driver){
        ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
    }

    /**
     * 键盘操作
     * keyDown 按下键盘
     * keyUp 释放键盘
     * perform() 执行键盘事件
     */
    public static Actions getActions(WebDriver driver){
        actions = new Actions(driver);
        return actions;
    }

    /**
     * 拖动 --源元素上执行单击并按住，移动到给定的偏移量，然后释放鼠标。
     */
    public static void dragAndDrop(WebElement element,int EleXOffset, int EleYOffset){
        actions.dragAndDropBy(element,EleXOffset,EleYOffset).build().perform();
    }

    /**
     * 获取元素颜色
     */
    public static String getColor(WebElement element){
        Color color = Color.fromString(element.getCssValue("color"));
        return String.valueOf(color.getColor());
    }

    /**
     * 获取元素背景色
     */
    public static String getBackgroundColor(WebElement element){
        Color color = Color.fromString(element.getCssValue("background-color"));
        return String.valueOf(color.getColor());
    }

}


