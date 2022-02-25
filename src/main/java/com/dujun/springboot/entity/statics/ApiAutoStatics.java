/*
  author     : dujun
  date       : 2022/2/10 12:04
  description: API自动化统计
 */

package com.dujun.springboot.entity.statics;

import lombok.Data;

@Data
public class ApiAutoStatics {

    // Api用例数
    private int apiCount;

    // 本周新增Api用例数
    private int newApiCount;

    // 场景用例数
    private int caseCount;

    // 本周新增场景用例数
    private int newCaseCount;

    // 计划总数
    private int planCount;

    // 本周新增计划数
    private int newPlanCount;

    //开启定时任务数量
    private int clockCount;

    // 定时执行次数
    private int clockExecCount;

    // 计划执行成功率
    private double successRatio;

    // 计划执行失败率
    private double failedRatio;



}
