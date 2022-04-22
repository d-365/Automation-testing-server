/**
 * author     : dujun
 * date       : 2022/1/17 15:22
 * description: 轻易花APP
 */

package com.dujun.springboot.Api.qyh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dujun.springboot.tools.JsonTools;
import com.dujun.springboot.tools.YmlTools;
import com.dujun.springboot.tools.dateTools;
import com.dujun.springboot.utils.MyRedis;
import com.dujun.springboot.utils.MysqlTools;
import com.dujun.springboot.utils.request;
import com.mysql.cj.jdbc.MysqlSavepoint;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class Qyh {

    YmlTools ymlTools = new YmlTools("globalConfig.yml");
    MyRedis myRedis = new MyRedis(ymlTools.getValueByKey("test.redis.qyh.ip",""),ymlTools.getValueByKey("test.redis.qyh.password",""));
    String domain = ymlTools.getValueByKey("test.domain.qyh","");

    private String token = "";

    static MysqlTools mysqlTools = new MysqlTools();


    public Qyh (String phone){
        login(phone);
    }

    // 执行sql操作(对应手机号订单改为3天前)
    public static void apply_step(String phone){
        String beforeTime = dateTools.currentTime(-5);
        mysqlTools.execute(String.format("UPDATE qyh.qyh_order SET dock_time ='%1$s', refresh_time='%1$s',create_time='%1$s',update_time='%1$s'  WHERE customer_phone =%2$s",beforeTime,phone));
    }

    // 登录
    public void login(String phone){

        myRedis.set(String.format("customer_%s",phone),"1234");
        String url = domain+"/api/customer/v1/user/code/login";
        HashMap<String,String> header = new HashMap<String, String>(){{
            put("Content-Type","application/x-www-form-urlencoded");
        }};
        HashMap<String,String> payload = new HashMap<String, String>(){{
            put("phone",phone);
            put("code","1234");
        }};
        CloseableHttpResponse response =  request.post_url(url,header,payload);
        JSONObject jsonObject = request.getResponseJson(response);
        this.token = JsonTools.parseJson("data.token",jsonObject);
    }

    // 通用的headers(json)
    public HashMap<String,String> header_json(){
        return new HashMap<String, String>(){{
            put("Content-Type","application/json;charset=UTF-8");
            put("token",token);
        }};
    }

    // 填单
    public JSONObject fillForm(String phone ,String payload){
        apply_step(phone);
        String url = domain+"/api/customer/v1/orderCondition/fillForm";
        CloseableHttpResponse response = request.post(url,header_json(), payload);
        return request.getResponseJson(response);
    }

    public static void main(String[] args) {
        new Qyh("17637898368");
    }


}
