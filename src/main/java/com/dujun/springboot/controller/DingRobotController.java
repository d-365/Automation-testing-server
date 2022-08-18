package com.dujun.springboot.controller;


import com.dujun.springboot.VO.Result;
import com.dujun.springboot.service.DingRobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("list")
    public Result<?> robotList(@RequestParam(defaultValue = "10") Integer size,
                               @RequestParam(defaultValue = "1") Integer current,
                               @RequestParam(required = false, value = "name") String name,
                               @RequestParam(required = false, value = "name") String status) {
        return dingRobotService.robotList(size, current, name, status);
    }

}

