/*
 * author     : dujun
 * date       : 2023/3/10 9:30
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.timing;

import com.dujun.springboot.utils.MysqlTools;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

@Component
public class ChartsTiming implements SchedulingConfigurer {

    public static HashMap<String, HashMap<String, Integer>> chartsData = new HashMap<>();

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(task(), getTrigger());
    }

    /**
     * 定时任务操作
     */
    private Runnable task() {
        return this::Chart;
    }

    /**
     * 统计QYH订单对接情况
     */
    public void Chart() {
        MysqlTools mysqlTools = new MysqlTools();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat simpleDateFormatShow = new SimpleDateFormat("HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            String endTime = simpleDateFormat.format(calendar.getTime());
            String endTimeShow = simpleDateFormatShow.format(calendar.getTime());
            calendar.add(Calendar.MINUTE, -1);
            String startTime = simpleDateFormat.format(calendar.getTime());
            HashMap<String, Integer> chatData = new HashMap<>();
            String sql = String.format("SELECT COUNT(CASE WHEN `status` = 2 AND dock_time >= '%1$s' AND dock_time < '%2$s' THEN `status` END) 'Booth',COUNT( CASE WHEN `status` = 3 AND dock_time >= '%1$s' AND dock_time < '%2$s' THEN `status` END) 'CRM' FROM qyh.qyh_order;", startTime, endTime);
            System.out.println(sql);
            ResultSet resultSet = mysqlTools.executeQuery(sql);
            while (resultSet.next()) {
                int crm = resultSet.getInt("CRM");
                int booth = resultSet.getInt("Booth");
                chatData.put("crm", crm);
                chatData.put("booth", booth);
                chatData.put("total", crm + booth);
            }
            chartsData.put(endTimeShow, chatData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定时任务执行时间
     *
     * @return Trigger
     */
    private Trigger getTrigger() {
        return triggerContext -> {
            CronTrigger cronTrigger = new CronTrigger("0 0/1 * * * ?");
            return cronTrigger.nextExecutionTime(triggerContext);
        };
    }


}
