/*
 * author     : dujun
 * date       : 2022/4/24 15:37
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.common.selenium;

public enum MyConditions{
    visibilityOfElementLocated("元素定位并可见后"),
    elementToBeClickable("元素可点击后");

    private final String describe;

    MyConditions(String describe) {
        this.describe = describe;
    }

    public String getDescribe(){
        return this.describe;
    }

}
