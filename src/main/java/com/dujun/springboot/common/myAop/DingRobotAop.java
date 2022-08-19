/**
 * author     : dujun
 * date       : 2022/8/18 16:25
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.common.myAop;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dujun.springboot.entity.DingRobot;
import com.dujun.springboot.entity.PlanResult;
import com.dujun.springboot.mapper.DingRobotMapper;
import com.dujun.springboot.mapper.PlanResultMapper;
import com.dujun.springboot.utils.request;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Log4j2
@Aspect
@Component
public class DingRobotAop {

    @Resource
    private DingRobotMapper robotMapper;

    @Resource
    private PlanResultMapper planResultMapper;

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.dujun.springboot.common.myAnnotation.DingRobotAn)")
    public void robotPointCut(){}

    @Around("robotPointCut()")
    public Object round(ProceedingJoinPoint point){
        Object[] args =  point.getArgs();
        Integer planId = (Integer) args[0];
        log.debug("前置机器人开始执行");
        roundRobot(0,planId);
        Object result = null;
        try {
            result = point.proceed();
            System.out.println(result);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        log.debug("后置机器人开始执行");
        roundRobot(1,planId);
        return result;
    }

    /**
     * 测试计划 -机器人执行
     * @param robotType 0 计划执行前 1 计划执行后
     */
    public void roundRobot(Integer robotType,Integer planId){
        LambdaQueryWrapper<DingRobot> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DingRobot::getStatus,0).eq(DingRobot::getRobotType,robotType);
        List<DingRobot> dingRobots = robotMapper.selectList(wrapper);
        if (dingRobots.size()!=0){
            if (robotType==0){
                HashMap<String,String> link = new HashMap<String,String>(){{
                    String title = String.format("测试计划开始执行了--计划ID: %1$s",planId);
                    put("title",title);
                    put("messageURL","https://www.dingtalk.com/");
                    put("picURL","http://testcdn.qyihua.com/FmLwEcGFETetaewL2XbQKQ8pp1pE");
                }};
                HashMap<String,Object> payload = new HashMap<String, Object>(){{
                    put("msgtype","feedCard");
                    put("feedCard",new HashMap<String,Object>(){{
                        put("links",new ArrayList<Object>(){{
                            add(link);
                        }});
                    }});
                }};
                dingRobots.forEach((item)->{
                    RobotExec(item,payload);
                });
            }else if (robotType ==1){
                LambdaQueryWrapper<PlanResult> planResultWrapper = new LambdaQueryWrapper<>();
                planResultWrapper
                        .select(PlanResult::getId,PlanResult::getCaseSuccessCount,PlanResult::getCaseFailedCount,PlanResult::getResultAddress)
                        .eq(PlanResult::getPlanId,planId)
                        .orderByDesc(PlanResult::getEndTime);
                List<PlanResult> planResultList = planResultMapper.selectList(planResultWrapper);
                PlanResult planResult = planResultList.get(0);
                String text = String.format("#### 测试报告 \n  成功用例数: %1$s, 失败用例数: %2$s  \n [报告详情](%3$s)",
                        planResult.getCaseSuccessCount(),
                        planResult.getCaseFailedCount(),
                        planResult.getResultAddress());
                HashMap<String,Object> payload = new HashMap<String, Object>(){{
                    put("msgtype","markdown");
                    put("markdown",new HashMap<String,String>(){{
                        put("title","测试报告");
                        put("text",text);
                    }});
                    put("at",new HashMap<String,Object>(){{
                        put("atMobiles",new ArrayList<>());
                        put("atUserIds",new ArrayList<>());
                        put("isAtAll",true);
                    }});
                }};
                dingRobots.forEach((item)-> RobotExec(item,payload));
            }

        }
    }

    /**
     * 机器人执行-发送测试报告
     * @param robot 机器人
     */
    public void RobotExec(DingRobot robot,HashMap<String,Object> payload){
        request  request = new request();
        HashMap<String,String> header = new HashMap<String, String>(){{
            put("Content-Type","application/json;charset=UTF-8");
        }};
        String url = robot.getRobotAddress();
        CloseableHttpResponse response = request.post(url,header,payload);
        JSONObject jsonObject = request.getResponseJson(response);
        log.debug(jsonObject);
    }

}
