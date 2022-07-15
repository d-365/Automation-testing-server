package com.dujun.springboot.common.selenium;

/**
 * web自动化断言类型
 */
public enum WebAssertType {

    TITLEIS("页面标题"),
    URLIS("页面地址"),
    TOASTASSERT("toast断言");


    private final String describe;

    WebAssertType(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

}
