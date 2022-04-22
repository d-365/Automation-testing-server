package com.dujun.springboot.mapper;

import com.dujun.springboot.entity.PlanResultDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dujun
 * @since 2022-01-20
 */
public interface PlanResultDetailMapper extends BaseMapper<PlanResultDetail> {

    //根据用例ID查询用例名
    @Select("select name from ui_web_case where id = #{caseId}")
    String findCaseName(Integer caseId);

}
