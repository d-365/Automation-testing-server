package com.dujun.springboot.common.selenium;

import com.dujun.springboot.utils.MysqlTools;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

class MySeleniumTest {
    @Test
    public void test1() throws Exception {
        WebDriver driver = MySelenium.getRemoteDriver("http://192.168.2.161:8585","fireFox");
    }

}