/**
 * author     : dujun
 * date       : 2022/2/7 17:08
 * description: 计划定时任务
 */

package com.dujun.springboot.timing;

import com.dujun.springboot.entity.RunPlan;
import com.dujun.springboot.service.impl.CommonServiceImpl;
import com.dujun.springboot.service.impl.RunPlanServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;


@Component
@Slf4j
public class PlanTiming  implements SchedulingConfigurer {

    @Autowired
    private CommonServiceImpl commonService;

    @Autowired
    private RunPlanServiceImpl runPlanService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> {
                    List<RunPlan> runPlanList = commonService.getPlanList();
                    for (RunPlan planDetail : runPlanList) {
                        runPlanService.runPlan(planDetail);
                    }
                },
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    String cron = "0 0 0 1/1 * ?";
                    List<RunPlan> runPlanList = commonService.getPlanList();
                    if (runPlanList.size()!=0){
                        String newCron =  runPlanList.get(0).getClock();
                        if (!Objects.equals(newCron, "")){
                            cron = newCron;
                        }
                    }
                    //2.1 从数据库获取执行周期
                    //2.2 合法性校验
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }

}
