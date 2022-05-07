package com.dujun.springboot.common.selenium;

/**
 * web自动化断言类型
 */
public enum WebAssertType {

    titleIs("页面标题"),
    urlIs("页面地址");

    private final String describe;

    WebAssertType(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

}
