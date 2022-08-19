package com.dujun.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dujun.springboot.entity.DingRobot;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 钉钉机器人提醒配置表 Mapper 接口
 * </p>
 *
 * @author dujun
 * @since 2022-08-18
 */
public interface DingRobotMapper extends BaseMapper<DingRobot> {

    Page<DingRobot> robotList(Page<DingRobot> page, @Param("name") String name, @Param("status")Integer status);

}
