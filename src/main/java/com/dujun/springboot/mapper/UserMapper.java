package com.dujun.springboot.mapper;

import com.dujun.springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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

}
