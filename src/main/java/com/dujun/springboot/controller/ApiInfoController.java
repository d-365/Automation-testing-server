package com.dujun.springboot.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.ApiInfo;
import com.dujun.springboot.mapper.ApiInfoMapper;
import com.dujun.springboot.service.impl.ApiInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private ApiInfoMapper apiInfoMapper;

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
    public Result saveApi(@RequestBody ApiInfo apiInfo){
        return apiInfoService.saveApi(apiInfo);
    }

    //删除分类
    @DeleteMapping("/delete/{id}")
    public  Result delete(@PathVariable("id") int id){
        return apiInfoService.delete(id);
    }

    @PostMapping("/temp")
    public void temp(@RequestBody ApiInfo obj){
//        String name = obj.getString("name");
//        System.out.println(name);
//        JSONArray reqParams = obj.getJSONArray("reqParams");
//        System.out.println(reqParams);
//        ApiInfo apiInfo = new ApiInfo();
//        apiInfo.setReqParams(reqParams);
//        apiInfo.setName(name);
//        apiInfoMapper.insert(apiInfo);
        System.out.println(obj);
        List<ApiInfo> list = apiInfoMapper.selectList(null);
        System.out.println(list);
    }

    @PostMapping("/debug")
    public Result debug(@RequestBody ApiInfo apiInfo){
        return apiInfoService.debug(apiInfo);
    }


}

