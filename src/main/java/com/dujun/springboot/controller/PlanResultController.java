package com.dujun.springboot.controller;


import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.PlanResult;
import com.dujun.springboot.entity.RespPageEntity;
import com.dujun.springboot.service.impl.PlanResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * <p>
 *  计划运行结果
 * </p>
 *
 * @author dujun
 * @since 2022-01-19
 */
@RestController
@RequestMapping("/planResult")
public class PlanResultController {

    @Autowired
    private PlanResultServiceImpl planResultService;

    @GetMapping("/list")
    // 计划运行结果列表
    public RespPageEntity result_list(PlanResult planResult, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){
        return planResultService.result_list(planResult,page,size);
    }

    // 删除选中的计划列表
    @PostMapping("/delPlanResult")
    public Result<?> delPlanResult(@RequestBody ArrayList<Integer> checkResultId){
        return planResultService.delPlanResult(checkResultId);
    }

    @PostMapping("/report/{planResultId}")
    public Result<?> report(@PathVariable("planResultId") Integer planResultId){
        return planResultService.report(planResultId);
    }

    @PostMapping("/web/report/{id}")
    public Result<?> webReport(@PathVariable("id") Integer planResultId){
        return planResultService.webReport(planResultId);
    }


}

