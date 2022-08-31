/**
 * author     : dujun
 * date       : 2021/12/6 17:58
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.entity;

import lombok.Data;

@Data
public class RspAsserts {
    //数据源
    private String dataSource;

    //提取数据
    private String extractExpress;

    //期望关系
    private  String expectRelation;

    //期望值
    private  String  expectValue;

    //实际值
    private String realValue;

    //断言结果
    private  Boolean assertResult;



}
