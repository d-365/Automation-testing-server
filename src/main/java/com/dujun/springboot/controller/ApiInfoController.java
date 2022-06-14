package com.dujun.springboot.controller;


import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.ApiInfo;
import com.dujun.springboot.service.impl.ApiInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 接口详情 前端控制器
 * </p>
 *
 * @author dujun
 * @since 2021-11-29
 */
@RestController
@RequestMapping("/interface")
public class ApiInfoController {

    @Autowired
    private  ApiInfoServiceImpl apiInfoService;

    //接口详情 根据分类ID
    @GetMapping("/detail")
    public Result<ApiInfo> apiDetail(@RequestParam int suiteId){
        return apiInfoService.detail(suiteId);
    }

    //接口详情 根据接口ID
    @GetMapping("/info/{id}")
    public Result<ApiInfo> apiInfo(@PathVariable("id") int id){
        return apiInfoService.info(id);
    }

    //保存接口
    @PostMapping("/save")
    public Result<?> saveApi(@RequestBody ApiInfo apiInfo){
        return apiInfoService.saveApi(apiInfo);
    }

    //删除分类
    @DeleteMapping("/delete/{id}")
    public  Result<?> delete(@PathVariable("id") int id){
        return apiInfoService.delete(id);
    }

    @PostMapping("/debug/{envId}")
    public Result<?> debug(@PathVariable("envId")Integer envId,@RequestBody ApiInfo apiInfo){
        return apiInfoService.debug(envId,apiInfo);
    }


}

