package com.dujun.springboot.service;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.Env;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dujun
 * @since 2021-12-10
 */
public interface EnvService extends IService<Env> {

    // 环境列表
    Result<List<Env>> envList();




}
