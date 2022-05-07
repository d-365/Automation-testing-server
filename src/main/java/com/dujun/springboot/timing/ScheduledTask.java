/*
 * author     : dujun
 * date       : 2022/5/7 14:46
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.timing;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dujun.springboot.entity.RunPlan;
import com.dujun.springboot.mapper.RunPlanMapper;
import com.dujun.springboot.service.impl.RunPlanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

@EnableScheduling
@Configuration
public class ScheduledTask implements SchedulingConfigurer {

    @Autowired
    private RunPlanServiceImpl planService;

    @Resource
    // 计划详情表
    private RunPlanMapper planInfoMapper;

    private volatile ScheduledTaskRegistrar taskRegistrar;
    private final HashMap<Integer, ScheduledFuture<?>> scheduledFutures = new HashMap<>();
    private final ConcurrentHashMap<Integer, CronTask> cronTasks = new ConcurrentHashMap<>();

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(3));
        this.taskRegistrar  = taskRegistrar;
    }

    /**
     * 修改 cron 需要 调用该方法
     */
    public void refresh(List<RunPlan> tasks){
        //获取所有定时任务ID并进行关闭
        stop();
        // 循环添加定时任务
        if (tasks.size()>0){
            for (RunPlan plan : tasks) {
                String expression = plan.getClock();
                //计划任务表达式为空则跳过
                if ("".equals(expression)) {
                    continue;
                }
                //计划任务已存在并且cron表达式未发生变化则跳过
                if (scheduledFutures.containsKey(plan.getId()) && cronTasks.get(plan.getId()).getExpression().equals(expression)) {
                    continue;
                }
                //业务逻辑处理
                CronTask task = cronTask(plan, expression);
                //执行业务
                ScheduledFuture<?> future = taskRegistrar.getScheduler().schedule(task.getRunnable(), task.getTrigger());
                cronTasks.put(plan.getId(), task);
                scheduledFutures.put(plan.getId(), future);
            }
        }

    }

    /**
     * 停止 cron 运行
     */
    public void stop(){
        Set<Integer> sids = scheduledFutures.keySet();
        for (Integer sid : sids) {
            scheduledFutures.get(sid).cancel(false);
            scheduledFutures.remove(sid);
            cronTasks.remove(sid);
        }
    }

    /**
     * 业务逻辑处理
     */
    public CronTask cronTask(RunPlan plan, String expression)  {
        return new CronTask(() -> {
            // 接口自动化定时任务
            if (plan.getPlanType() == 0) {
                planService.runApiPlan(plan);
            }// Web 自动化定时
            else if (plan.getPlanType() == 1) {
                planService.webRun(plan.getId());
            }//App计划定时
            else if (plan.getPlanType() == 2) {
                System.out.println("App计划定时");
            }
        }, expression);
    }

    // 从数据库里取得所有要执行的定时任务
    public List<RunPlan> getAllTasks() {
        LambdaQueryWrapper<RunPlan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RunPlan::getIsClock, 1).eq(RunPlan::getStatus,1);
        return planInfoMapper.selectList(queryWrapper);
    }

}


@Component
class StartInitTask implements CommandLineRunner {

    @Autowired
    private ScheduledTask scheduledTask;

    @Override
    public void run(String... args) throws Exception {
        scheduledTask.refresh(scheduledTask.getAllTasks());
    }
}






