/*
 * author     : dujun
 * date       : 2022/2/7 17:08
 * description: 计划定时任务
 */

package com.dujun.springboot.timing;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class PlanTiming {

    @Scheduled(fixedRate = 5000)
    public void work(){
        System.out.println("测试定时任务");
//        quartzService.refreshTrigger();
    }

}
