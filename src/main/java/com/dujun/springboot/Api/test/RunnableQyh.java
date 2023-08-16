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
import lombok.extern.log4j.Log4j2;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Log4j2
public class RunnableQyh implements Runnable{

    @Override
    public void run() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String phone = RandomValue.getTel();
//        phone = "17310045947";
        Qyh qyh = new Qyh(phone);
        String city = RandomValue.getAddress();
        HashMap<String, Object> payload = ApiOrderData.qyh_applyData("重庆市");
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            JSONObject jsonObject = qyh.fillForm(JSON.toJSONString(payload));
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
        stopWatch.stop();
    }

}
