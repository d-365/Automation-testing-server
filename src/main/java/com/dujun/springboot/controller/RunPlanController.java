package com.dujun.springboot.controller;


import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.PlanParam;
import com.dujun.springboot.entity.RunPlan;
import com.dujun.springboot.service.impl.RunPlanServiceImpl;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  执行计划
 * </p>
 *
 * @author dujun
 * @since 2022-01-12
 */
@RestController
@RequestMapping("/plan")
public class RunPlanController {

    @Autowired
    private RunPlanServiceImpl planService;

    // 删除计划
    @DeleteMapping("/delete/{planID}")
    public Result deletePlan(@PathVariable("planID") int planId) {
        return planService.deletePlan(planId) ;
    }


    // 新增修改计划
    @PostMapping("/save")
    public Result update(@RequestBody RunPlan plan){
        return planService.update(plan) ;
    }

    // 计划列表
    @PostMapping("/list")
    public Result<List<RunPlan>> planList(@RequestBody RunPlan planFilter){
        System.out.println(planFilter);
        return planService.planList(planFilter);
    }

    // 运行接口测试计划
    @PostMapping("/runPlan")
    public Result<?> runPlan(@RequestBody RunPlan planInfo){
        return planService.runPlan(planInfo);
    }

    @PostMapping("/webRun/{planId}")
    public Result<?> webRun(@PathVariable("planId")Integer planId){
        return planService.webRun(planId);
    }



}

