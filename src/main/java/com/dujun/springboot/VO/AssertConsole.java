/*
 * author     : dujun
 * date       : 2022/4/24 17:21
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 断言控制台
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssertConsole {

    // 步骤ID
    private Integer stepId;

    // 断言方式
    private String assertType;

    // 期望值
    private String expectValue;

    // 断言值
    private String realityValue;

    //断言结果
    private Boolean result;

    // 消息
    private String msg;

}
