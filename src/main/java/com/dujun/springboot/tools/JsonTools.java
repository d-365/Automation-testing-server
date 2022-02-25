/**
 * author     : dujun
 * date       : 2022/1/18 11:17
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.tools;

import com.alibaba.fastjson.JSONObject;

public class JsonTools {

    public static String parseJson(String key, JSONObject jsonObject){

        String[] strings = key.split("\\.");
        String finalValue = "";
        JSONObject jsonObjectTemp = jsonObject;
        for (int i = 0; i < strings.length; i++) {
            if(i == strings.length -1){
                finalValue = jsonObjectTemp.getString(strings[i]);
                return finalValue;
            }
            jsonObjectTemp = jsonObjectTemp.getJSONObject(strings[i]);
        }
        return finalValue;
    }
}
