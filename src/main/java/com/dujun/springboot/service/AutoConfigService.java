package com.dujun.springboot.service;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.AutoConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dujun
 * @since 2022-04-06
 */
public interface AutoConfigService extends IService<AutoConfig> {

    Result<?> locationWay();

}
