package com.dujun.springboot.mapper;

import com.dujun.springboot.entity.WebCaseStep;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dujun
 * @since 2022-04-14
 */
public interface WebCaseStepMapper extends BaseMapper<WebCaseStep> {

    @Select("select action_key from action where id = #{actionId}")
    String getActionKey(@Param("actionId") Integer actionId);


}
