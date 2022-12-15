///*
// * author     : dujun
// * date       : 2022/9/16 14:39
// * description: 消费者
// */
//
//package com.dujun.springboot.tools.MQ;
//
//import com.dujun.springboot.entity.User;
//import com.dujun.springboot.mapper.UserMapper;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.apache.rocketmq.spring.annotation.MessageModel;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
//@Component
//@RocketMQMessageListener(consumerGroup = "consumerGroup", topic = "TestTopic",messageModel = MessageModel.CLUSTERING)
//public class SpringBootConsumer implements RocketMQListener<MessageExt> {
//
//    @Resource
//    UserMapper userMapper;
//
//    @Override
//    public void onMessage(MessageExt message) {
//        String data = new String(message.getBody());
//        System.out.println("消费者1 : "+ data);
//        if (message.getReconsumeTimes()>=2){
//            return;
//        }
//        User user = new User();
//        user.setAccount("消费");
//        user.setPassword("1234");
//        userMapper.insert(user);
//        if (true){
//            throw new RuntimeException("消费者错误");
//        }
//
//    }
//
//}
//
