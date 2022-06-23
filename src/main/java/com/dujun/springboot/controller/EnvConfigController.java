package com.dujun.springboot.controller;


import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.EnvConfig;
import com.dujun.springboot.entity.User;
import com.dujun.springboot.service.impl.EnvConfigServiceImpl;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 环境配置表 前端控制器
 * </p>
 *
 * @author dujun
 * @since 2022-05-09
 */
@RestController
@RequestMapping("/env")
public class EnvConfigController {

    @Autowired
    private EnvConfigServiceImpl envConfigService;

    @GetMapping("/envList")
    public Result<?>  envList (){
        return envConfigService.envList();
    }

    @PostMapping("/update")
    public Result<?> updateAndSave(@RequestBody String payload){
        return envConfigService.updateAndSave(payload);
    }

    @DeleteMapping("/del/env/{id}")
    public Result<?> delEnv(@PathVariable("id") Integer envId){
        return envConfigService.delEnv(envId);
    }

    @PostMapping("/user/bind")
    public Result<?> userBind(@RequestBody User user){
        Integer userId = user.getId();
        Integer envId = user.getEnvId();
        return envConfigService.userBind(userId,envId);
    }

    @GetMapping("/list")
    public Result<List<EnvConfig>> envListsOp(){
        return envConfigService.envListsOp();
    }

    /**
     * 通过环境ID和 项目ID 获取对应环境下域名信息
     */
    @GetMapping("/domain")
    public Result<?> userProDomain(@RequestParam Integer envId,@RequestParam Integer proId){
        return envConfigService.userProDomain(envId,proId);
    }

    @GetMapping("/dbOptions/{envId}")
    public Result<?> dbOptions(@PathVariable("envId") Integer envId){
        return envConfigService.dbOptions(envId);
    }

}

