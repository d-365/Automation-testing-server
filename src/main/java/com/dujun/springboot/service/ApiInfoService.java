package com.dujun.springboot.service;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.ApiInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 接口详情 服务类
 * </p>
 *
 * @author dujun
 * @since 2021-11-29
 */
public interface ApiInfoService extends IService<ApiInfo> {

    // 运行单个测试用例（debug）
    Result<?> debug(Integer envId,ApiInfo apiInfo);

}
