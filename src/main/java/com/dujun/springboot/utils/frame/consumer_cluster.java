/*
 * author     : dujun
 * date       : 2022/7/4 16:53
 * description: 负载均衡消费
 */

package com.dujun.springboot.utils.frame;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

public class consumer_cluster{

    public static void main(String[] args) {
        // 实例化消息生产者,指定组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("myGroup1");
        //设置NameServer地址
        consumer.setNamesrvAddr("121.40.229.114:9876");
        consumer.setInstanceName("consumer-instance-1");
        // 订阅Topic
        try {
            consumer.subscribe("myTopic1", "*");
            //负载均衡模式消费
            consumer.setMessageModel(MessageModel.CLUSTERING);
            consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
                System.out.println("消费");
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), list);
                System.out.println();
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        //启动消费者
        try {
            consumer.start();
            System.out.printf("Consumer Started.%n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
