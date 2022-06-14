package com.dujun.springboot.mapper;

import com.dujun.springboot.entity.CategoryApi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 接口分类列表 Mapper 接口
 * </p>
 *
 * @author dujun
 * @since 2021-11-25
 */
public interface CategoryApiMapper extends BaseMapper<CategoryApi> {

    // 根据接口ID 找到对应的分类ID
    @Select("SELECT api_suite_id FROM api_info WHERE id = #{id}")
    Long categoryIdById(Long id);

}
