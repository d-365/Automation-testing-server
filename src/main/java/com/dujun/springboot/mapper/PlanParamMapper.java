package com.dujun.springboot.mapper;

import com.dujun.springboot.entity.PlanParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dujun
 * @since 2022-01-12
 */
public interface PlanParamMapper extends BaseMapper<PlanParam> {

    // 按照计划ID进行查找
    PlanParam byPlanId(int planId);

    // 按照计划ID进行删除
    @Delete("DELETE FROM plan_param WHERE  plan_id = #{planId};")
    void delByPlanId(int planId);

}
