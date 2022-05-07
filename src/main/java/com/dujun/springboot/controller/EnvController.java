package com.dujun.springboot.controller;


import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.Env;
import com.dujun.springboot.service.impl.EnvServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dujun
 * @since 2021-12-10
 */
@RestController
@RequestMapping("/env")
public class EnvController {

    @Autowired
    private EnvServiceImpl envService;

    // 环境列表
    @GetMapping("/envList")
    public Result<List<Env>> envList(){
        return envService.envList();
    }

    // 更新环境
    @PostMapping("/update")
    public Result<?> envUpdate(@RequestBody ArrayList<Env> envArrayList){
        return envService.envUpdate(envArrayList);
    }

    // 查看单个环境详情
    @GetMapping("/envDetail/{id}")
    public Result<Env> envDetail(@PathVariable("id") int id ){
        return envService.envDetail(id);
    }

    //删除环境信息
    @DeleteMapping("/delEnv/{id}")
    public Result<?> delEnv(@PathVariable("id") int id){
        return envService.delEnv(id);
    }


}

