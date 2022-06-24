package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.AppConfig;
import com.dujun.springboot.mapper.AppConfigMapper;
import com.dujun.springboot.service.AppConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * APP 启动配置表 服务实现类
 * </p>
 *
 * @author dujun
 * @since 2022-06-23
 */
@Service("AppConfigServiceImpl")
public class AppConfigServiceImpl extends ServiceImpl<AppConfigMapper, AppConfig> implements AppConfigService {

    @Resource
    private AppConfigMapper appConfigMapper;

    @Override
    public Result<?> appConfigList(Integer size, Integer current,String name) {
        Page<AppConfig> configPage = appConfigMapper.appConfigs(new Page<>(current, size),name);
        return Result.success(configPage);
    }

    @Override
    public Result<?> delAppConfig(Integer id) {
        appConfigMapper.deleteById(id);
        return Result.success();
    }

    @Override
    public Result<?> updateAppConf(AppConfig appConfig) {
        if (appConfig.getId()!=null){
            appConfigMapper.updateById(appConfig);
        }else {
            appConfigMapper.insert(appConfig);
        }
        return Result.success();
    }

}
