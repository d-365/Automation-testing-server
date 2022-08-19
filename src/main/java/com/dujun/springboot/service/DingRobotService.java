package com.dujun.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.DingRobot;

/**
 * <p>
 * 钉钉机器人提醒配置表 服务类
 * </p>
 *
 * @author dujun
 * @since 2022-08-18
 */
public interface DingRobotService extends IService<DingRobot> {

    Result<?> robotList(Integer size, Integer current, String name, Integer status);

    Result<?> updateRobot(DingRobot robot);

    Result<?> del(Integer id);
}
