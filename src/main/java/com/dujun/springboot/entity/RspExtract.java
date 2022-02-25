/**
 * author     : dujun
 * date       : 2021/12/9 9:52
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.entity;

import lombok.Data;

//请求参数提取
@Data
public class RspExtract {

    //数据源
    private String dataSource;

    //提取表达式
    private String extractExpress;

    //变量名称
    private  String varName;

    //变量数据类型
    private String realType;

    //变量值
    private  String realValue;

}
