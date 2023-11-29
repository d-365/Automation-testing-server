package com.dujun.springboot.common.selenium;

import org.openqa.selenium.WebDriver;

class MySeleniumTest {


    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = MySelenium.getDriver("chrome");
        driver.get("https://www.baidu.com/index.htm");
        Thread.sleep(3000);
        driver.quit();
    }


}