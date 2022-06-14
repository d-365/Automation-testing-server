package com.dujun.springboot.mapper;

import com.dujun.springboot.entity.ApiInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 接口详情 Mapper 接口
 * </p>
 *
 * @author dujun
 * @since 2021-11-29
 */
public interface ApiInfoMapper extends BaseMapper<ApiInfo> {

    // 根据分类ID 删除对应接口
    @Delete("DELETE FROM api_info WHERE api_suite_id = #{apiSuiteId} ")
    void  deleteBy_api_suite_id(@Param("apiSuiteId") int apiSuiteId);


//    // 数据库查询对应的接口详情
//    ApiInfo selectByApiId(@Param("apiId") int apiId);




}
