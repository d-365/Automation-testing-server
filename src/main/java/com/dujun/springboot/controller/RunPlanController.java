package com.dujun.springboot.controller;


import com.dujun.springboot.VO.Result;
import com.dujun.springboot.common.myAnnotation.DingRobotAn;
import com.dujun.springboot.entity.RunPlan;
import com.dujun.springboot.service.impl.RunPlanServiceImpl;
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
    public Result<?>  deletePlan(@PathVariable("planID") int planId) {
        return planService.deletePlan(planId) ;
    }

    // 新增修改计划
    @PostMapping("/save")
    public Result<?> update(@RequestBody RunPlan plan){
        return planService.update(plan) ;
    }

    // 计划列表
    @PostMapping("/list")
    public Result<List<RunPlan>> planList(@RequestBody RunPlan planFilter){
        return planService.planList(planFilter);
    }

    // 运行接口测试计划
    @PostMapping("/runPlan")
    public Result<?> runPlan(@RequestBody RunPlan planInfo){
        return planService.runApiPlan(planInfo);
    }

    // 执行web端测试计划
    @PostMapping("/webRun/{planId}")
    public Result<?> webRun(@PathVariable("planId")Integer planId){
        return planService.webRun(planId);
    }

    // 执行App端测试计划
    @PostMapping("/execute/appPlan/{planId}")
    public Result<?> appPlan(@PathVariable("planId")Integer planId){
        return planService.appPlanExec(planId);
    }

    @PostMapping("/checked/case")
    public Result<?> checkedCase(@RequestBody List<Integer> caseId){
        return planService.checkedCase(caseId);
    }

    @PostMapping("/setup/{planId}")
    public Result<?> setupList(@PathVariable("planId") Integer planId){
        return planService.setupList(planId);
    }

    @PostMapping("/round/update")
    public Result<?> updateRound(@RequestBody String payload){
        return planService.updateRound(payload);
    }

    @DeleteMapping("/round/delete/{id}")
    public Result<?> delRound(@PathVariable("id") Integer id){
        return planService.delRound(id);
    }

}

