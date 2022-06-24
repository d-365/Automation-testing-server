package com.dujun.springboot.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dujun.springboot.entity.AppConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * APP 启动配置表 Mapper 接口
 * </p>
 *
 * @author dujun
 * @since 2022-06-23
 */
public interface AppConfigMapper extends BaseMapper<AppConfig> {

    Page<AppConfig> appConfigs(Page<AppConfig> appConfigPage,String name);

}
