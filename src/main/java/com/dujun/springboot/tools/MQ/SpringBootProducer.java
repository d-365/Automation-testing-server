//package com.dujun.springboot.tools.MQ;
//
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SpringBootProducer {
//
//    @Autowired
//    private RocketMQTemplate rocketMQTemplate;
//
//    // 发送消息的实例
//    public void sendMessage(String topic, String msg) {
//        rocketMQTemplate.convertAndSend(topic, msg);
//    }
//
//}
