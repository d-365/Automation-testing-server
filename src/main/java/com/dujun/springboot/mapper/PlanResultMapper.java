package com.dujun.springboot.mapper;

import com.dujun.springboot.entity.PlanResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dujun
 * @since 2022-01-19
 */
public interface PlanResultMapper extends BaseMapper<PlanResult> {

    List<PlanResult> planResultList(Integer page, Integer size, String planName, Integer planType, Integer resultStatus, String startTime);

    Long planResultTotal(Integer resultStatus, String planName, Integer planType, String startTime);

}
