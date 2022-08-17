package com.dujun.springboot.common.appium;

public enum MyExpected {

    elementToBeClickable("元素可被点击"),
    visibilityOfElementLocated("元素可见定位"),
    presenceOfElementLocated("元素出现在doom树-但不一定可见");
    String describe;

    MyExpected(String describe) {
        this.describe = describe;
    }
}