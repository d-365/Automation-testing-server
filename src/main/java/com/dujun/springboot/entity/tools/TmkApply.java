/**
 * author     : dujun
 * date       : 2021/12/30 13:57
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.entity.tools;

import lombok.Data;

@Data
public class TmkApply {
    // 手机号
    private String phone;
    //城市
    private String city;
    // 循环次数
    private int loop;
    // 开始结束状态（0 ： 开始  1 结束）
    private  int status;
    // 请求间隔时间
    private Long step;

}
