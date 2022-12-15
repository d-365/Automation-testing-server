///*
// * author     : dujun
// * date       : 2022/9/16 14:39
// * description: 消费者
// */
//
//package com.dujun.springboot.tools.MQ;
//
//import org.apache.rocketmq.spring.annotation.MessageModel;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.annotation.SelectorType;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Component;
//
//import java.time.Instant;
//
//@Component
//@RocketMQMessageListener(
//        consumerGroup = "consumerGroup2",
//        topic = "TestTopic",
//        messageModel = MessageModel.CLUSTERING,
//        selectorType = SelectorType.TAG,
//        selectorExpression = "tag3 || tag2"
//)
//public class Consumer2 implements RocketMQListener<String> {
//
//    @Override
//    public void onMessage(String message) {
//        System.out.println("消费者2 : "+ message);
//        System.out.println(Instant.now().toString());
//    }
//
//}
//
//
