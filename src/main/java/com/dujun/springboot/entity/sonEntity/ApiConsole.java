/**
 * author     : dujun
 * date       : 2021/12/16 16:09
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.entity.sonEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiConsole {

    //控制台消息结果
    private boolean success;

    // 控制台消息
    private String msg;

    //消息位置
    private  String location;
}
