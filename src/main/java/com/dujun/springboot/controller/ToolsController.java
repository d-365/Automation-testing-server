/**
 * author     : dujun
 * date       : 2021/12/21 11:45
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.sonEntity.EnvDataBase;
import com.dujun.springboot.entity.tools.TmkApply;
import com.dujun.springboot.mapper.ToolsMapper;
import com.dujun.springboot.service.ToolsService;
import com.dujun.springboot.service.impl.ToolsServiceImpl;
import com.mysql.cj.protocol.x.CompressionSplittedOutputStream;
import com.mysql.cj.xdevapi.JsonString;
import com.sun.org.apache.bcel.internal.generic.ReturnInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.smartcardio.CardTerminal;

/*
    工具类
 */
@RestController
@RequestMapping("/tools")
public class ToolsController {


    @Autowired
    private  ToolsServiceImpl toolsService;


    // 电销填单
    @PostMapping("/tmkApply")
    public Result tmkApply(@RequestBody JSONObject tmkApplyData ){
        String phone = tmkApplyData.getString("phone");
        String city = tmkApplyData.getString("city");
        return toolsService.tmkApply(phone,city);
    }

    // 电销循环填单
    @PostMapping("/tmkApplyLoop")
    public Result tmkApplyLoop(@RequestBody TmkApply tmkApplyData){
        return toolsService.tmkApplyLoop(tmkApplyData);
    }

    @PostMapping("/tmkRandom")
    public Result tmkApplyRandom(@RequestBody TmkApply tmkApplyData){
        return toolsService.tmkApplyRandom(tmkApplyData);
    }

    // 轻易花填单
    @PostMapping("qyhApply")
    public Result qyhApply(@RequestBody TmkApply apply){
        String phone = apply.getPhone();
        String city = apply.getCity();
        return toolsService.qyhApply(phone, city);
    }

}
