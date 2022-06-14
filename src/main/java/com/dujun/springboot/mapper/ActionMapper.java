package com.dujun.springboot.mapper;

import com.dujun.springboot.entity.Action;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dujun
 * @since 2022-04-06
 */
public interface ActionMapper extends BaseMapper<Action> {

    @Select("SELECT action_key  FROM action WHERE id = #{actionId} AND del_flag = 0;")
    String actionTypeById(@Param("actionId") Integer actionId);

}
