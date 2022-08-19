package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.DingRobot;
import com.dujun.springboot.mapper.DingRobotMapper;
import com.dujun.springboot.service.DingRobotService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private DingRobotMapper robotMapper;

    @Override
    public Result<?> robotList(Integer size, Integer current, String name, Integer status) {
        Page<DingRobot> robotIPage = new Page<>(current, size);
        Page<DingRobot> robotList = robotMapper.robotList(robotIPage,name,status);
        return Result.success(robotList);
    }

    @Override
    public Result<?> updateRobot(DingRobot robot) {
        if (robot.getRobotAddress()==null||robot.getRobotAddress().equals("")){
            return Result.error("机器人地址不能为空");
        }
        try {
            if (robot.getId()!=null){
                robotMapper.updateById(robot);
            }else {
                robotMapper.insert(robot);
            }
        }catch (Exception exception){
            exception.printStackTrace();
            return Result.error("系统异常");
        }
        return Result.success();
    }

    @Override
    public Result<?> del(Integer id) {
        if (id!=null){
            robotMapper.deleteById(id);
        }
        return Result.success();
    }
}
