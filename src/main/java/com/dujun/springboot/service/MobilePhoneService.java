package com.dujun.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.MobilePhone;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * APP-执行机 服务类
 * </p>
 *
 * @author dujun
 * @since 2022-06-23
 */
public interface MobilePhoneService extends IService<MobilePhone> {

    Result<IPage<MobilePhone>> mobilePhoneList(Integer current, Integer size,Integer status);

}
