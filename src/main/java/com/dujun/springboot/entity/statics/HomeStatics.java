/**
 * author     : dujun
 * date       : 2022/2/10 11:57
 * description: 数据统计实体类
 */

package com.dujun.springboot.entity.statics;

import lombok.Data;

import java.util.ArrayList;

@Data
public class HomeStatics {

    // API自动化统计
    private ApiAutoStatics ApiAutoStatics;

    // UI自动化统计
    private ApiAutoStatics webAutoStatics;

    // App自动化统计
    private String AppAutoStatics;

    // APi折线图数据
    private String ApiLineChart;


}
