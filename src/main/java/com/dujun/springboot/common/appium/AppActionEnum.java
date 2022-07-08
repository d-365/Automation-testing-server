package com.dujun.springboot.common.appium;

/*
  APP操作枚举类
 */
public enum AppActionEnum {
    DEFAULT("默认"),
    SLEEP("线程等待"),
    PRESSKEY("键盘输入"),
    KEY_DEL("键盘删除"),
    CATCH_TOAST("toast捕捉"),
    SWITCH_TO_H5("切换到H5视图"),
    SWITCH_TO_NATIVE("切换到原生视图"),
    CLICK("点击元素"),
    INPUT("文本输入"),
    ;

    private final String describe;
    public String getDescribe() {
        return describe;
    }
    AppActionEnum(String describe) {
        this.describe = describe;
    }
}
