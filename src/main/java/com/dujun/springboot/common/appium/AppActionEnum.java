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
    CLEAR("文本清除"),
    SWIPE_TOP("向上滑动"),
    SWIPE_BOTTOM("向下滑动"),
    SWIPE_LEFT("向左滑动"),
    SWIPE_RIGHT("向右滑动"),
    TAP_CLICK("使用界面坐标（比例系数）进行定位点击"),
    EXECJS("执行JS命令"),
    QYHAPPLY("轻易花APP用户填单");

    private final String describe;

    public String getDescribe() {
        return describe;
    }

    AppActionEnum(String describe) {
        this.describe = describe;
    }
}
