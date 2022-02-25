/**
 * author     : dujun
 * date       : 2022/1/19 13:45
 * description: 计划运行结果详情
 */

package com.dujun.springboot.entity.sonEntity;

import lombok.Data;

@Data
public class PlanResultCount {

    // 接口运行成功数量
    private int api_success;

    // 接口运行失败数量
    private int api_failed;

    // 用例运行成功数量
    private int case_success;

    // 用例运行失败数量
    private int case_failed;

}
