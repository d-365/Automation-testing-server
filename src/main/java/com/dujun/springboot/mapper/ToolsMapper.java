package com.dujun.springboot.mapper;


import org.apache.ibatis.annotations.Select;

public interface ToolsMapper{

    // 根据手机号查询订单ID（jgq.think_loan）
    String id_byPhone(String phone);

    @Select("SELECT id from qyh.qyh_order WHERE customer_phone = #{phone} order BY id DESC  LIMIT 1")
    String qyh_idByPhone(String phone);

}
