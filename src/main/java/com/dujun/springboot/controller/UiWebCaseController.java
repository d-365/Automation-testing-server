package com.dujun.springboot.controller;


import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.UiWebCase;
import com.dujun.springboot.service.impl.UiWebCaseServiceImpl;
import jdk.nashorn.internal.objects.NativeUint8Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dujun
 * @since 2022-04-11
 */
@RestController
@RequestMapping("/webCase")
public class UiWebCaseController {

    @Autowired
    private UiWebCaseServiceImpl webCaseService;

    @PostMapping("/caseList")
    public Result<?> caseList(){
        return webCaseService.caseList();
    }

    // 更新用例
    @PostMapping("/update")
    public Result<?> update(@RequestBody UiWebCase uiWebCase){
        return webCaseService.update(uiWebCase);
    }

    // 删除用例
    @PostMapping("/delete")
    public Result<?> delWebCase(@RequestBody UiWebCase webCase) throws Exception {
        return webCaseService.delWebCase(webCase);
    }

    // 复制用例
    @PostMapping("/deepCopy")
    public Result<?> deepCopy(@RequestBody UiWebCase uiWebCase){
        return webCaseService.deepCopy(uiWebCase);
    }

    //用例步骤列表
    @GetMapping("/steps/{caseId}")
    public Result<?> steps(@PathVariable("caseId") Integer caseId){
        return webCaseService.steps(caseId);
    }

    @DeleteMapping("/caseStep/delete/{stepId}")
    public Result<?> delCaseStep(@PathVariable("stepId") Integer stepId){
        return webCaseService.delCaseStep(stepId);
    }



}

