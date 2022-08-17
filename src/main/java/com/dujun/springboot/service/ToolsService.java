/**
 * author     : dujun
 * date       : 2021/12/21 12:03
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.service;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.tools.TmkApply;

public interface ToolsService {

    Result<?> tmkApply(String phone, String city);

    Result<?> tmkApplyLoop(TmkApply tmkApplyData);

    Result<?> tmkApplyRandom(TmkApply tmkApplyData);

    Result<?> qyhApply(String phone, String city);

    Result<?> qyhApplyStart(String phone, String city, int loop);

    Result<?> qyhApplyEnd();
}
