package com.dujun.springboot.controller;


import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.Action;
import com.dujun.springboot.service.impl.ActionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dujun
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/action")
public class ActionController {

    @Autowired
    private ActionServiceImpl actionService;

    @PostMapping("/actionList")
    public Result<List<Action>> actionList(@RequestBody HashMap<String,String> action){
        return actionService.actionList(action);
    }

    @DeleteMapping("/delAction/{id}")
    public Result<?> delAction (@PathVariable("id") Integer id){
        return actionService.delAction(id);
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody Action action){
        return actionService.updateAction(action);
    }



}

