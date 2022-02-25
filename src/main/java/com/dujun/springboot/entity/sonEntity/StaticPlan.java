/**
 * author     : dujun
 * date       : 2022/2/10 15:59
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.entity.sonEntity;

import lombok.Data;

@Data
public class StaticPlan {

    // 计划总数
    private double planCount;
    // 执行成功数
    private double success;
    // 执行失败数
    private double failed ;

}
