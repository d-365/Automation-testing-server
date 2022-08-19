package com.dujun.springboot.controller;


import com.dujun.springboot.VO.Result;
import com.dujun.springboot.common.myAnnotation.DingRobotAn;
import com.dujun.springboot.common.myAnnotation.MyLog;
import com.dujun.springboot.entity.DingRobot;
import com.dujun.springboot.service.DingRobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 钉钉机器人提醒配置表 前端控制器
 * </p>
 *
 * @author dujun
 * @since 2022-08-18
 */
@RestController
@RequestMapping("/dingRobot")
public class DingRobotController {

    @Autowired
    private DingRobotService dingRobotService;

    @GetMapping("/list")
    public Result<?> robotList(@RequestParam(defaultValue = "10") Integer size,
                               @RequestParam(defaultValue = "1") Integer current,
                               @RequestParam(required = false, value = "name") String name,
                               @RequestParam(required = false, value = "status") Integer status) {
        return dingRobotService.robotList(size, current, name, status);
    }

    @PostMapping("/update")
    public Result<?> updateRobot(@RequestBody DingRobot robot) {
        return dingRobotService.updateRobot(robot);
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> del(@PathVariable(value = "id")Integer id) {
        return dingRobotService.del(id);
    }

}

