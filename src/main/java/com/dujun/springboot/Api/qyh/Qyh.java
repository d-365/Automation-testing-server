/*
  author     : dujun
  date       : 2022/1/17 15:22
  description: 轻易花APP
 */

package com.dujun.springboot.Api.qyh;

import com.alibaba.fastjson.JSONObject;
import com.dujun.springboot.tools.JsonTools;
import com.dujun.springboot.tools.RandomValue;
import com.dujun.springboot.tools.YmlTools;
import com.dujun.springboot.tools.dateTools;
import com.dujun.springboot.utils.MyRedis;
import com.dujun.springboot.utils.MysqlTools;
import com.dujun.springboot.utils.request;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.sql.ResultSet;
import java.util.HashMap;

public class Qyh {

    YmlTools ymlTools = new YmlTools("globalConfig.yml");
    MyRedis myRedis = new MyRedis(ymlTools.getValueByKey("test.redis.qyh.ip",""),ymlTools.getValueByKey("test.redis.qyh.password",""));
    String domain = ymlTools.getValueByKey("test.domain.qyh","http://testmapi.qyihua.com");
    private String token = "";
    public static MysqlTools mysqlTools = new MysqlTools();
    private final String phone;
    public request request = new request();

    public Qyh(String phone) {
        this.phone = phone;
        login(phone);
        user_init();
    }

    public Qyh() {
        this.phone = "17637898369";
        login(phone);
        user_init();
    }

    // 执行sql操作(对应手机号订单改为3天前)
    public void apply_step() {
        String beforeTime = dateTools.currentTime(-5);
        mysqlTools.execute(String.format("UPDATE qyh.qyh_order SET dock_time ='%1$s', refresh_time='%1$s',create_time='%1$s',update_time='%1$s'  WHERE customer_phone =%2$s", beforeTime, phone));
        mysqlTools.execute(String.format("UPDATE jgq.think_loan SET creat_time = '%1$s', update_time = '%1$s',create_time_auto = '%1$s',update_time_auto = '%1$s' WHERE  phone = %2$s", beforeTime, phone));
    }

    // 删除redis填单5min限制
    public void redis_OrderInit() {
        String sql = String.format("SELECT id FROM qyh.qyh_customer_user WHERE phone = %s",phone);
        try {
            ResultSet resultSet = mysqlTools.executeQuery(sql);
            if (resultSet.next()){
                String uId = resultSet.getString("id");
                String key = String.format("fill_order_%s",uId);
                myRedis.del(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    /**
     * 用户登录后二次处理（假数据）
     */
    public void user_init(){
        int age = RandomValue.getNum(20,80);
        int sex = RandomValue.getInteger(1,2);
        mysqlTools.execute(String.format("UPDATE qyh.qyh_customer_user SET real_name='test', sex=%1$s,age = %2$s WHERE phone = '%3$s';",sex,age,phone));
    }

    // 通用的headers(json)
    public HashMap<String, String> header_json() {
        return new HashMap<String, String>() {{
            put("Content-Type", "application/json;charset=UTF-8");
            put("token", token);
        }};
    }

    // 获取手机对应订单ID
    public String getOrderId() {
        String sql = String.format("SELECT id FROM qyh.qyh_order WHERE customer_phone = %s ORDER BY id DESC LIMIT 1", phone);
        try {
            ResultSet resultSet = mysqlTools.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getString("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // 填单
    public JSONObject fillForm(String payload) {
        apply_step();
        redis_OrderInit();
        String url = domain + "/api/customer/v1/orderCondition/fillForm";
        CloseableHttpResponse response = request.post(url, header_json(), payload);
        return request.getResponseJson(response);
    }

    /**
     * 已填订单
     */
    public JSONObject newOrder() {
        String url = domain + "/api/customer/v1/order/newOrder";
        CloseableHttpResponse response = request.get(url, header_json());
        return request.getResponseJson(response);
    }

    /**
     * 我的订单列表
     */
    public JSONObject myOrders() {
        String url = domain + "/api/customer/v1/order/myOrders";
        CloseableHttpResponse response = request.get(url, header_json());
        return request.getResponseJson(response);
    }

    /**
     * 订单保留天数
     */
    public JSONObject orderRetainDays() {
        String url = domain + "/api/customer/v1/order/orderRetainDays";
        CloseableHttpResponse response = request.get(url, header_json());
        return request.getResponseJson(response);
    }


    /**
     * 城市列表
     */
    public JSONObject cityList() {
        String url = domain + "/api/crm/admin/v1/advertising/city/list";
        CloseableHttpResponse response = request.get(url, header_json());
        return request.getResponseJson(response);
    }

}
