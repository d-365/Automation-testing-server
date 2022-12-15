///*
// * author     : dujun
// * date       : 2022/9/16 14:41
// * description: 告诉大家我是干啥的
// */
//
//package com.dujun.springboot.controller.temp;
//
//import com.alibaba.fastjson.JSON;
//import com.dujun.springboot.VO.Result;
//import com.dujun.springboot.common.selenium.SeleniumUtils;
//import com.dujun.springboot.entity.User;
//import com.dujun.springboot.mapper.UserMapper;
//import com.dujun.springboot.utils.BeanContext;
//import org.apache.rocketmq.client.producer.SendCallback;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.client.producer.TransactionSendResult;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
////@RestController
////@RequestMapping("/mq")
//public class MQController {
//
//    @Resource
//    private RocketMQTemplate rocketMQTemplate;
//
//    String topic = "TestTopic:tag1";
//
//    @Resource
//    UserMapper userMapper;
//
//    @GetMapping("/send")
//    public Result<String> send(){
//        Map<String,Object> orderMap = new HashMap<>();
//        orderMap.put("orderNumber","1357890");
//        orderMap.put("createTime", LocalDateTime.now());
//        Message<String> messages = MessageBuilder.withPayload(JSON.toJSONString(orderMap)).build();
//        TransactionSendResult transactionSendResult = rocketMQTemplate.sendMessageInTransaction(topic,messages,null);
//        System.out.println("消息发送状态"+transactionSendResult.getSendStatus().name());
//        System.out.println("本地事务执行状态"+transactionSendResult.getLocalTransactionState().name());
//        return Result.success("消息发送成功");
//    }
//
//    @GetMapping("/send1")
//    public Result<String> send1(@RequestParam(defaultValue = "du") String msg){
//        rocketMQTemplate.syncSend(topic,"dujun");
//        return Result.success("消息发送成功");
//    }
//
//    @GetMapping("/send2")
//    public Result<String> send2(@RequestParam(defaultValue = "du") String msg){
//        rocketMQTemplate.asyncSend(topic,"异步消息",new SendCallback(){
//            @Override
//            public void onSuccess(SendResult sendResult) {
//                System.out.println(sendResult.getSendStatus());
//            }
//            @Override
//            public void onException(Throwable throwable) {
//                System.out.println(throwable.getMessage());
//            }
//        });
//        return Result.success("消息发送成功");
//    }
//
//    @GetMapping("/time")
//    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW )
//    public Result<?> time() throws Exception {
//        MQController mqController = BeanContext.getApplicationContext().getBean(MQController.class);
//        User user = new User();
//        user.setAccount("事务1");
//        user.setPassword("1234");
//        userMapper.insert(user);
//        Thread.sleep(3000);
////        mqController.temp3();
////        mqController.temp2();
//        return Result.success();
//    }
//
//    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
//    public void temp2() throws IOException {
//        User user = new User();
//        user.setAccount("事务2");
//        user.setPassword("1234");
//        userMapper.insert(user);
//        if (true){
//            throw new IOException("异常了");
//        }
//    }
//
//    @Transactional(propagation = Propagation.NESTED)
//    public void temp3(){
//        User user = new User();
//        user.setAccount("事务3");
//        user.setPassword("1234");
//        userMapper.insert(user);
//    }
//
//}
