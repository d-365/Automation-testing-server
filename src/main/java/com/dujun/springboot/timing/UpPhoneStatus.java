/*
 * author     : dujun
 * date       : 2022/7/1 16:04
 * description: 修改手机号状态
 */

package com.dujun.springboot.timing;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dujun.springboot.entity.MobilePhone;
import com.dujun.springboot.mapper.MobilePhoneMapper;
import com.dujun.springboot.utils.cmdTaskUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Log4j2
@Component
public class UpPhoneStatus implements SchedulingConfigurer {

    @Resource
    private MobilePhoneMapper mobilePhoneMapper;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(task(),getTrigger());
    }

    /**
     * 定时任务操作
     */
    private Runnable task(){
        return this::phoneStatus;
    }

    /**
     * 定时任务执行时间
     * @return Trigger
     */
    private Trigger getTrigger() {
        return triggerContext -> {
            CronTrigger cronTrigger = new CronTrigger("0 0 0/1 * * ?");
            return cronTrigger.nextExecutionTime(triggerContext);
        };
    }

    /**
     * 查询手机是否在线
     */
    public void phoneStatus(){
        try {
            log.debug("-------------开始执行定时任务-更新手机上下线状态-----------------");
            String data = cmdTaskUtils.getCmdResult("cmd /c adb devices");
            List<MobilePhone> phones = mobilePhoneMapper.selectList(new QueryWrapper<MobilePhone>().eq("del_flag",0).ne("status",2));
            for (MobilePhone phone : phones) {
                if (phone.getIp()!=null&&!phone.getIp().equals("")&&data.contains(phone.getIp())){
                    phone.setStatus(0);
                }else {
                    phone.setStatus(1);
                }
                mobilePhoneMapper.updateById(phone);
            }
            log.debug("-------------手机状态定时任务-执行完毕---------------------");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


}
