/**
 * author     : dujun
 * date       : 2022/2/10 14:28
 * description: 数据统计service
 */

package com.dujun.springboot.service;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.statics.HomeStatics;

public interface StaticService {

    // 首页数据统计
    Result<HomeStatics> home();

    // 首页折线图数据统计
    Result<?> line();


}
