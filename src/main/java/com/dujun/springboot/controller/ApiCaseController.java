package com.dujun.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.ApiCase;
import com.dujun.springboot.entity.ApiInfo;
import com.dujun.springboot.entity.CaseApiRelation;
import com.dujun.springboot.service.impl.ApiCaseServiceImpl;
import com.mysql.cj.xdevapi.JsonString;
import com.sun.org.apache.bcel.internal.generic.ReturnInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dujun
 * @since 2021-12-24
 */
@RestController
@RequestMapping("/case")
public class ApiCaseController {

    @Autowired
    private ApiCaseServiceImpl apiCaseService;

    @PostMapping("/info")
    public Result<ApiCase> info(@RequestBody JSONObject payload){
        Integer categoryId = payload.getInteger("categoryId");
        return apiCaseService.info(categoryId);
    }

    // 将分类ID转换为用例ID
    @PostMapping("/relation")
    public Result<ArrayList<ApiInfo>> relation(@RequestBody  ArrayList<Integer> categoryList){
        return apiCaseService.relation(categoryList);
    }

    // 用例ID返回用例详情列表
    @PostMapping("/apiInfoList")
    public Result<ArrayList<ApiInfo>> apiInfoList(@RequestBody  ArrayList<Integer> apiIdList){
        return apiCaseService.apiInfoList(apiIdList);
    }

    //保存用例
    @PostMapping("/update")
    public Result update(@RequestBody ApiCase caseInfo){
        return apiCaseService.update_caseInfo(caseInfo);
    }

    @PostMapping("/apiCase")
    // 用例和接口进行关联
    public Result apiCase_relation(@RequestBody  ArrayList<CaseApiRelation> CaseApiRelation){
        return apiCaseService.apiCase_relation(CaseApiRelation);
    }

    @PostMapping("caseApiDetail")
    // 用例对应的接口列表
    public Result caseApiDetail(@RequestBody int caseId){
        return apiCaseService.caseApiDetail(caseId);
    }

    @DeleteMapping("deleteCaseApi/{id}")
    //删除用例接口关联信息
    public Result deleteCaseApi(@PathVariable("id") int id){
        return apiCaseService.deleteCaseApi(id);
    }


    @PostMapping("/debugCase")
    // 用例deBUG调试
    public Result debugCase(@RequestBody ApiCase apiCase){
        return apiCaseService.debugCase(apiCase);
    }



}

