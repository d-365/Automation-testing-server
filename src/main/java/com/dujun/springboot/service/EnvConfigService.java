package com.dujun.springboot.service;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.EnvConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 环境配置表 服务类
 * </p>
 *
 * @author dujun
 * @since 2022-05-09
 */
public interface EnvConfigService extends IService<EnvConfig> {

    Result<?> envList();

    Result<?> updateAndSave(String payload);

    Result<?> delEnv(Integer id);

    Result<?> userBind(Long userId, Integer envId);

    Result<?> userProDomain( Integer envId,  Integer proId);

    Result<?> dbOptions(Integer envId);
}
