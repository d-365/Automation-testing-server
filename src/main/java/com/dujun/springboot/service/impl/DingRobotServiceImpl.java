package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.DingRobot;
import com.dujun.springboot.mapper.DingRobotMapper;
import com.dujun.springboot.service.DingRobotService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 钉钉机器人提醒配置表 服务实现类
 * </p>
 *
 * @author dujun
 * @since 2022-08-18
 */
@Service
public class DingRobotServiceImpl extends ServiceImpl<DingRobotMapper, DingRobot> implements DingRobotService {

    @Override
    public Result<?> robotList(Integer size, Integer current, String name, String status) {
        return Result.success();
    }
}
