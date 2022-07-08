package com.dujun.springboot.service;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.AppConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * APP 启动配置表 服务类
 * </p>
 *
 * @author dujun
 * @since 2022-06-23
 */
public interface AppConfigService extends IService<AppConfig> {

    Result<?> appConfigList(Integer size,Integer current,String name);

    Result<?> delAppConfig(Integer id);

    Result<?> updateAppConf(AppConfig appConfig);

    Result<?> appConfigOption();
}
