package com.dujun.springboot.mapper;

import com.dujun.springboot.entity.ApiCase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dujun.springboot.entity.ApiInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dujun
 * @since 2021-12-24
 */
public interface ApiCaseMapper extends BaseMapper<ApiCase> {

    // 根据用例分类ID查询用例下的接口ID
    List<Integer> selectApiIdsByCategory(Integer categoryId);

    // 根据用例分类ID查询用例中的apiInfo
    List<ApiInfo> apiInfoByCaseCategory(Integer categoryId);

    // 根据用例分类ID 查询用例ID
    @Select("SELECT id from mall.api_case WHERE category_id= #{categoryId}")
    Integer caseIdByCategory(Integer categoryId);


    // 根据用例ID查询完整用例信息（包含用例ID）
    ApiCase selectCase(Integer caseID);

}
