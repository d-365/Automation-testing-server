/*
 * author     : dujun
 * date       : 2022/6/22 14:41
 * description: 自定义显示等待
 */

package com.dujun.springboot.common.appium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyExpectedConditions {

    public static ExpectedCondition<WebElement> run (By by, MyExpected myExpected){
        switch (myExpected){
            case elementToBeClickable:
                return ExpectedConditions.elementToBeClickable(by);
            default:
                return null;
        }
    }

}
