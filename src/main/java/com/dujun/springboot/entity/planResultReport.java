/**
 * author     : dujun
 * date       : 2022/1/24 17:03
 * description: 计划执行结果报告
 */

package com.dujun.springboot.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class planResultReport {

    // 场景用例分类列表
    private ArrayList<?> caseTree;

    // Api用例分类列表
    private ArrayList<?> ApiTree;

    // 执行计划详情
    private PlanResult planResult;

    // 单接口执行结果
    private ArrayList<?> apiRunResult;

    // 用例执行结果
    private ArrayList<?> caseRunResult;


}
