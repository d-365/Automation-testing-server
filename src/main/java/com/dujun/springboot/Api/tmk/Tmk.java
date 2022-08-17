/*
  author     : dujun
  date       : 2021/12/21 14:10
  description: 告诉大家我是干啥的
 */

package com.dujun.springboot.Api.tmk;

import com.alibaba.fastjson.JSONObject;
import com.dujun.springboot.utils.request;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class Tmk {

    public String token="";
    private final request request;

    public Tmk(){
        request= new request();
        login("fen_interface","16d7a4fca7442dda3ad93c9a726597e4");

    }
    public Tmk(String username,String password){
        request= new request();
        login(username,password);

    }
    // 通用的headers
    public HashMap<String,String> getHeaders(String token){
        return new HashMap<String, String>() {{
            put("Content-Type", "application/json;charset=UTF-8");
            put("token", token);
        }};
    }

    /**
     * 登录
     * @param account
     * @param password
     */
    public JSONObject login(String account,String password){
        String login_url = "http://testdrktm.wanqiandaikuan.com/api/tmk/user/login";
        HashMap<String,String> headers = new HashMap<String, String>(){{
            put("Content-Type","application/json");
        }};
        HashMap<Object,Object> payload = new HashMap<Object, Object>(){{
            put("account",account);
            put("password",password); //16d7a4fca7442dda3ad93c9a726597e4
            put("validate",1234);
        }};
        CloseableHttpResponse response = request.post(login_url,headers,payload);
        JSONObject responseJson = request.getResponseJson(response);
        this.token = responseJson.getJSONObject("data").getString("token");
        return responseJson;
    }

    // 电销填单
    public JSONObject apply(HashMap<String, Object> payload) {
        HashMap<String, String> headers = getHeaders(token);
        String login_url = "http://testdrktm.wanqiandaikuan.com/api/tmk/electricity/telemarketing/apply";
        CloseableHttpResponse response = request.post(login_url, headers, payload);
        return request.getResponseJson(response);
    }

}

