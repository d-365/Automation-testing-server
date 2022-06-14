package com.dujun.springboot.common;

// Action枚举
public enum actionEnum{
    DEFAULT("默认"),
    OPENURL("打开网址"),
    CLICK("点击元素"),
    SLEEP("强制等待"),
    INPUT("文本输入"),
    CLEAR("清除文本"),
    BACK("driver后退"),
    FORWARD("driver前进"),
    REFRESH("网址刷新"),
    GETCOLOR("获取颜色"),
    GETBACKGROUNDCOLOR("获取背景色"),
    GETTEXT("获取元素内显示文本"),
    CSSVALUE("元素的指定计算样式属性的值"),
    SENDALERTKEYS("Alert弹窗发送文本"),
    ACCEPTALERT("接收Alert弹窗"),
    DISMISSALERT("取消Alert弹窗"),
    SWITCHFRAMEBYNAME("按照name切换Frame"),
    SWITCHFRAMEBYELEMENT("按照元素切换Frame"),
    TODEFAULTCONTENT("切换回默认内容"),
    EXECPYTHON("执行python文件"),
    QUERYSQL("执行查询sql"),
    UPDATESQL("执行sql-无结果");

    private final String describe;

    public String getDescribe() {
        return describe;
    }

    actionEnum(String describe) {
        this.describe =describe;
    }
}
