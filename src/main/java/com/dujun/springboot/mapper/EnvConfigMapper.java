package com.dujun.springboot.mapper;

import com.dujun.springboot.entity.DbConfig;
import com.dujun.springboot.entity.EnvConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 环境配置表 Mapper 接口
 * </p>
 *
 * @author dujun
 * @since 2022-05-09
 */
public interface EnvConfigMapper extends BaseMapper<EnvConfig> {

    // user 和 EnvID 进行绑定
    @Update("UPDATE  user SET env_id = #{envId} WHERE id = #{userId};")
    void userEnvBind(@Param("userId") Long userId , @Param("envId") Integer envId);

    // 通过环境ID查询对应下的数据库信息
    String dbConfigByEnvId(@Param("envId") Integer envId);

}
