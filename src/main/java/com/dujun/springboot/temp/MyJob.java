/*
 * author     : dujun
 * date       : 2022/3/17 16:44
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        int count = 0;
        System.out.println("执行我的任务"+count);
        count+=1;
    }
}
