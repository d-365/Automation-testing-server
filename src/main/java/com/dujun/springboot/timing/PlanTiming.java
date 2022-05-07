/**
 * author     : dujun
 * date       : 2022/2/7 17:08
 * description: 计划定时任务
 */

package com.dujun.springboot.timing;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dujun.springboot.entity.RunPlan;
import com.dujun.springboot.mapper.RunPlanMapper;
import jdk.nashorn.internal.objects.NativeUint8Array;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

//@Component
@Slf4j
public class PlanTiming implements SchedulingConfigurer {

    @Resource
    // 计划详情表
    private RunPlanMapper planInfoMapper;

    private volatile ScheduledTaskRegistrar registrar;

    @Scheduled(fixedRate = 6000)
    public void sendTimeCancelOrder() {
        List<CronTask> cronTasks = registrar.getCronTaskList();
        System.out.println(cronTasks);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        this.registrar = registrar;
        List<RunPlan> planList = getAllTasks();
        if (planList != null && planList.size() > 0) {
            for (RunPlan plan : planList) {
                this.registrar.addTriggerTask(getRunnable(plan), getTrigger(plan));
            }
        }
    }

    // 从数据库里取得所有要执行的定时任务
    private List<RunPlan> getAllTasks() {
        LambdaQueryWrapper<RunPlan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RunPlan::getIsClock, 1).eq(RunPlan::getStatus,1);
        return planInfoMapper.selectList(queryWrapper);
    }

    // 执行定时任务
    private Runnable getRunnable(RunPlan runPlan) {
        return new Runnable() {
            @Override
            public void run() {
                // 接口自动化定时任务
                if (runPlan.getPlanType() == 0) {
                    System.out.println("接口自动化定时任务");
                }// Web 自动化定时
                else if (runPlan.getPlanType() == 1) {
                    System.out.println("Web 自动化定时");
                }//App计划定时
                else if (runPlan.getPlanType() == 2) {
                    System.out.println("App计划定时");
                }
            }
        };
    }

    // 获取定时任务执行时间
    private Trigger getTrigger(RunPlan plan) {
        return new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                String cron = plan.getClock();
                if (cron == null || "".equals(cron)) {
                    return null;
                }
                CronTrigger cronTrigger = new CronTrigger(cron);
                return cronTrigger.nextExecutionTime(triggerContext);
            }
        };
    }

    // 销毁定时任务
    private void destroy(){
        if (this.registrar!=null){
            registrar.destroy();
        }
    }

}
