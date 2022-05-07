package com.dujun.springboot.common.selenium;

import org.openqa.selenium.WebDriver;

class MySeleniumTest {
    public static void main(String[] args) {
        WebDriver driver = MySelenium.getDriver("chrome");
        driver.get("https://juejin.cn/backend");
        driver.switchTo().frame(0);

    }
}