package com.dujun.springboot.controller;


import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.CategoryApi;
import com.dujun.springboot.service.impl.CategoryApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 接口分类列表 前端控制器
 * </p>
 *
 * @author dujun
 * @since 2021-11-25
 */
@RestController
@RequestMapping("/categoryApi")
public class CategoryApiController {

    @Autowired
    private CategoryApiServiceImpl categoryApiService;

    @GetMapping("/list")
    //分类列表
    public Result<List<CategoryApi>> list(){
        return categoryApiService.List();
    }

    //新增分类
    @PostMapping("/save")
    public Result save(@RequestBody CategoryApi categoryApi){
        return categoryApiService.add(categoryApi);
    }

    //删除分类
    @DeleteMapping("/delete/{id}")
    public  Result delete(@PathVariable("id") int id){
        return categoryApiService.delete(id);
    }

    //修改分类
    @PutMapping("/update")
    public  Result update(@RequestBody CategoryApi categoryApi){
        return categoryApiService.update(categoryApi);
    }

    //根据ID返回分类列表
    @GetMapping("/suitDetail")
    public Result<CategoryApi> suitDetail(@RequestParam Integer suitId ){
        return categoryApiService.suitDetail(suitId);
    }



}

