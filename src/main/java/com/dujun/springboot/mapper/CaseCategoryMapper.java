package com.dujun.springboot.mapper;

import com.dujun.springboot.entity.CaseCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dujun
 * @since 2021-12-22
 */
public interface CaseCategoryMapper extends BaseMapper<CaseCategory> {

    //按照分类ID 修改用例名称
    @Update("UPDATE api_case SET case_name = #{name} WHERE category_id = #{category_id};")
    void update_caseName(@Param("name") String name,@Param("category_id") int id);

}
