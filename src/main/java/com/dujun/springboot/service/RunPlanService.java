package com.dujun.springboot.service;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.RunPlan;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dujun
 * @since 2022-01-12
 */
public interface RunPlanService extends IService<RunPlan> {

    //计划列表
    Result<List<RunPlan>> planList(RunPlan planFilter);

    //删除计划
    Result deletePlan(int planId);

    // 运行计划
    Result runPlan(RunPlan planInfo);

    // 新增修改计划
    Result<?> update(RunPlan plan);

    Result<?> webRun(Integer planId);

}
