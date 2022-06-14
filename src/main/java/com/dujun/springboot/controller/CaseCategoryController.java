package com.dujun.springboot.controller;


import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.CaseCategory;
import com.dujun.springboot.service.impl.CaseCategoryServiceImpl;
import org.apache.ibatis.annotations.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *   用例分类列表
 * </p>
 *
 * @author dujun
 * @since 2021-12-22
 */
@RestController
@RequestMapping("/caseCategory")
public class CaseCategoryController {

    @Autowired
    private CaseCategoryServiceImpl caseCategoryService;

    // 新增修改接口分类
    @PostMapping("/add")
    public Result<?> update(@RequestBody CaseCategory caseCategory ){
        return caseCategoryService.update_category(caseCategory);
    }

    // 接口分类列表
    @GetMapping("/list")
    public Result<List<CaseCategory>> list(){
        return caseCategoryService.case_categoryList();
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") int id){
        return caseCategoryService.delete(id);
    }

}

