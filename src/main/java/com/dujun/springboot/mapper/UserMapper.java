package com.dujun.springboot.mapper;

import com.dujun.springboot.entity.Role;
import com.dujun.springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dujun
 * @since 2021-11-21
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     根据账号查询对应用户
     */
    User selectByAccount(@Param("account") String account);

    /**
     * 查询对应用户权限列表
     */
    Role findPermission(@Param("userId") Integer userId);

}
