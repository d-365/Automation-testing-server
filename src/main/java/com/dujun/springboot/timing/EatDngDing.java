/*
 * author     : dujun
 * date       : 2022/9/19 11:12
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.timing;

import com.alibaba.fastjson.JSONObject;
import com.dujun.springboot.tools.RandomValue;
import com.dujun.springboot.utils.request;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Log4j2
@Component
public class EatDngDing implements SchedulingConfigurer {

    private final ArrayList<String> eatList = new ArrayList<String>(){{
        add("一鸣真咸奶吧( 酸奶 + 面包 )");
        add("今天别吃了、饿着吧");
        add("今天别吃了、饿着吧");
        add("今天别吃了、饿着吧");
        add("今天别吃了、饿着吧");
    }};

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(task(),getTrigger());

    }

    /**
     * 定时任务操作
     */
    private Runnable task(){
        return this::Ding;
    }

    /**
     * 钉钉机器人
     */
    public void Ding(){
        request request = new request();
        HashMap<String,String> header = new HashMap<String, String>(){{
            put("Content-Type","application/json;charset=UTF-8");
        }};
        String url = "https://oapi.dingtalk.com/robot/send?access_token=9e3e13d31ba4728583cdba65377d145fad3550de294c0087d3bd0ea2b12546f4";
        String photo2 = "http://testcdn.qyihua.com/FmLwEcGFETetaewL2XbQKQ8pp1pE";
        HashMap<String,String> link = new HashMap<String,String>(){{
            String title = String.format("干饭时间到: 今日午餐__%1$s",eatList.get(RandomValue.getNum(0,1)));
            put("title",title);
            put("messageURL",photo2);
            put("picURL",photo2);
        }};
        HashMap<String,Object> payload = new HashMap<String, Object>(){{
            put("msgtype","feedCard");
            put("feedCard",new HashMap<String,Object>(){{
                put("links",new ArrayList<Object>(){{
                    add(link);
                }});
            }});
        }};
        CloseableHttpResponse response = request.post(url,header,payload);
        JSONObject jsonObject = request.getResponseJson(response);
        log.debug(jsonObject);
    }

    /**
     * 定时任务执行时间
     * @return Trigger
     */
    private Trigger getTrigger() {
        return triggerContext -> {
            CronTrigger cronTrigger = new CronTrigger("0 0 11 * * ?");
            return cronTrigger.nextExecutionTime(triggerContext);
        };
    }


}
