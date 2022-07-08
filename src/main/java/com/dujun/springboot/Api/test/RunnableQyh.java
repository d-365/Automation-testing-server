/*
 * author     : dujun
 * date       : 2022/6/27 13:58
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.Api.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dujun.springboot.Api.qyh.Qyh;
import com.dujun.springboot.data.ApiOrderData;
import com.dujun.springboot.tools.RandomValue;
import com.google.gson.JsonObject;
import lombok.extern.log4j.Log4j2;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Log4j2
public class RunnableQyh implements Runnable{

    @Override
    public void run() {
        String phone = RandomValue.getTel();
        Qyh qyh = new Qyh("18397858213");
        String city = RandomValue.getAddress();
        HashMap<String,Object> payload = ApiOrderData.qyh_applyData("北京市");
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
                JSONObject jsonObject = qyh.fillForm(JSON.toJSONString(payload));
                System.out.println(jsonObject);
                log.debug(jsonObject);
                return "";
            });
        try {
            completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        log.debug(Thread.currentThread().getName());
        qyh.request.close();
    }

}
