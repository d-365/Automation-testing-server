package com.dujun.springboot.service;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dujun
 * @since 2021-11-21
 */
public interface UserService extends IService<User> {

    void export(HttpServletResponse response) throws IllegalAccessException;

}
