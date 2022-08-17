/*
  author     : dujun
  date       : 2021/12/21 14:42
  description: 告诉大家我是干啥的
 */

package com.dujun.springboot.data;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

public class ApiOrderData {
    // 电销填单数据
    public static HashMap<String, Object> tmk_data(String phone, String city) {
        return new HashMap<String, Object>() {{
            put("realname", "du");
            put("age", 55);
            put("sex", "1");
            put("loan_money", "10");
            put("loan_time", 1);
            put("loan_goal", "结婚贷款");
            put("loan_id_name", "上班族");
            put("city_name", city);
            put("social_security", "连续6个月");
            put("provident_fund", "连续6个月");
            put("is_house", "有房产，不接受抵押");
            put("is_car", "有车，不接受抵押");
            put("credit_money", "3000元以下");
            put("car_money", "10万以下");
            put("wld_money", 500);
            put("zmf", "500");
            put("lnsurance_name", "阳光保险");
            put("lnsurance_value", "5万元以下");
            put("loan_id", 0);
            put("money", "2000元以下");
            put("income_type", "现金发放");
            put("workage", "6个月以上");
            put("house_type", "按揭房");
            put("house_money", "50万及以下");
            put("is_wld", "有");
            put("is_zmf", "1");
            put("credit_record", "1年内逾期超过3次或者90天");
            put("lnsurance", "投保人寿险且投保两年以下");
            put("education", "初中");
            put("workunit", "测试");
            put("car_data",new HashMap<String,String>(){{
                put("car_money", "10万以下");
            }} );
            put("house_data",new HashMap<String,String>(){{
                put("house_money", "50万及以下");
                put("house_type", "按揭房");
            }} );
            put("wld_data", new HashMap<String,Integer>(){{
                put("wld_money",500);
            }});
            put("loan", new HashMap<String,String>(){{
                put("money", "2000元以下");
                put("income_type","现金发放");
            }});
            put("phone",phone);

        }};
    }
    //轻易花填单数据
    public static HashMap<String,Object> qyh_applyData(String city){
        return new HashMap<String, Object>(){{
            put("loanMoney","5");
            put("loanTime",1);
            put("loanGoal",0); // 资金用途
            put("province","浙江省");
            put("city",city);
            put("education",4);
            put("occupation",2); // 职业身份
            put("occupationData", JSON.toJSONString(new HashMap<String,Object>(){{
                put("year_income",299999);
                put("is_license",0);
                put("operating_years",11);
            }}));
            put("socialSecurity", 2);
            put("accumulationFund", 2);
            put("isHasHouse", 1);
            put("houseData",JSON.toJSONString(new HashMap<String,Object>(){{
                put("house_type",0);
                put("house_price",500000);
            }}) );
            put("isHasCar", 1);
            put("carData",JSON.toJSONString(new HashMap<String,Object>(){{
                put("car_price",99999);
            }}) );
            put("creditLimit", 3000);
            put("wld", 4999);
            put("zmf", 700);
            put("creditRecord", 3);
            put("insurance", 1);
            put("insuranceData",JSON.toJSONString(new HashMap<String,Integer>(){{
                put("insurance_company",1);
                put("insurance_price",49999);
            }}));
        }};
    }
}
