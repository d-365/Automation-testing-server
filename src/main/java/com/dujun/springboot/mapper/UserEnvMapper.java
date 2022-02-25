package com.dujun.springboot.mapper;

import com.dujun.springboot.entity.UserEnv;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dujun
 * @since 2022-01-07
 */
public interface UserEnvMapper extends BaseMapper<UserEnv> {

    @Update("UPDATE mall.user_env SET env_id=#{envId} WHERE user_id=#{userId}")
    void updateByUserId(Long userId,int envId);

}
