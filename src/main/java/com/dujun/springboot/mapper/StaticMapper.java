/**
 * author     : dujun
 * date       : 2022/2/10 15:05
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.mapper;

import com.dujun.springboot.entity.sonEntity.StaticPlan;
import com.dujun.springboot.entity.sonEntity.DayRunDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface StaticMapper {

    @Select("SELECT COUNT(*)  FROM category_api  WHERE source_type = 2")
    int apiCount();

    @Select("SELECT COUNT(*) FROM category_api  WHERE source_type = 2 AND create_time > #{day};")
    int newApiCount(String day);

    @Select("SELECT COUNT(*) FROM ${tableName}  WHERE type = 1 and case_type =#{caseType};")
    int caseCount(String tableName,@Param("caseType") Integer caseType);

    @Select("SELECT COUNT(*) FROM ${tableName}  WHERE type = 1;")
    int ApiCaseCount(String tableName);

    @Select("SELECT COUNT(*)  FROM ${tableName}   WHERE type = 1 AND create_time > #{day} AND case_type = #{caseType};")
    int newCaseCount(@Param("tableName")String tableName,@Param("day") String day,@Param("caseType") Integer caseType);

    @Select("SELECT COUNT(*)  FROM ${tableName}   WHERE type = 1 AND create_time > #{day};")
    int newApiCaseCount(@Param("tableName")String tableName,@Param("day") String day);

    @Select("SELECT COUNT(*) FROM run_plan where plan_type = #{planType};")
    int planCount(Integer planType);

    @Select("SELECT COUNT(*) FROM run_plan where create_time > #{day} and plan_type = #{planType};")
    int newPlanCount(@Param("day") String day,@Param("planType") Integer planType);

    @Select("SELECT COUNT(*) FROM run_plan WHERE status = 1 AND is_clock = 1 and plan_type = #{planType};")
    int clockCount(@Param("planType") Integer planType);

    @Select("SELECT IFNULL(sum(clock_exec_count),0) FROM run_plan where plan_type = #{planType};")
    int clockExecCount(@Param("planType") Integer planType);

    StaticPlan planRate(Integer planType);

    // 页面元素总数
    @Select("SELECT COUNT(id) FROM page_element WHERE type = #{type};")
    int pageElement(@Param("type") Integer type);

    // 本周新增页面元素总数
    @Select("SELECT COUNT(id) FROM page_element WHERE type = #{type} and create_time > #{day};")
    int newPageElement(@Param("type") Integer type,@Param("day") String day);

    // 查询当天自动化成功数
    @Select("SELECT sum(case_success_count) as count  FROM plan_result WHERE start_time LIKE '${today}%' and plan_type = #{planType};")
    Integer planDetailSuccess(String today,Integer planType);

    // 查询当天自动化总失败数
    @Select("SELECT sum(case_failed_count) as count  FROM plan_result WHERE start_time LIKE '${today}%' and plan_type = #{planType};")
    Integer planDetailFailed(String today,Integer planType);


}
