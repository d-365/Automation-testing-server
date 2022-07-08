/*
 * author     : dujun
 * date       : 2022/7/4 15:53
 * description: rocketMq工具类
 */

package com.dujun.springboot.utils.frame;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.List;

public class RocketMqUtils {

    @Test
    // 发送同步消息
    public void Test_product_sync(){
        // 创建消息生产者product,并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("myGroup1");

        // 设置NameServer的地址
        producer.setNamesrvAddr("121.40.229.114:9876");
        // 启动生产者
        try {
            producer.start();
            for (int i =0;i<2;i++){
                // 创建消息，并指定Topic，Tag和消息体
                Message message = new Message("myTopic1","tag1","hello1".getBytes(RemotingHelper.DEFAULT_CHARSET));
                // 发送消息到一个Broker
                SendResult sendResult = producer.send(message);
                // 通过sendResult返回消息是否成功送达
                System.out.printf("%s%n", sendResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 如果不再发送消息，关闭Producer实例。
//        producer.shutdown();

    }

    // 发送异步消息
    @Test
    public void product_async(){
        // 实例化消息生产者,制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("myGroup1");
        //设置NameServer地址
        producer.setNamesrvAddr("121.40.229.114:9876");
        // 启动生产者
        try {
            producer.start();
            for (int i =0;i<10;i++){
                // 创建消息，并指定Topic，Tag和消息体
                Message message = new Message("myTopic1","tag2","hello2".getBytes(RemotingHelper.DEFAULT_CHARSET));
                // 发送消息到一个Broker
                int finalI = i;
                producer.send(message,new SendCallback(){
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.printf("%-10d OK %s %n", finalI, sendResult.getMsgId());
                    }
                    @Override
                    public void onException(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 如果不再发送消息，关闭Producer实例。
//        producer.shutdown();
    }

    // 发送单向消息
    @Test
    public void product_oneWay(){
        // 实例化消息生产者,制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("myGroup1");
        //设置NameServer地址
        producer.setNamesrvAddr("121.40.229.114:9876");
        // 启动生产者
        try {
            producer.start();
            for (int i =0;i<2;i++){
                // 创建消息，并指定Topic，Tag和消息体
                Message message = new Message("myTopic1","tag4","hello3".getBytes(RemotingHelper.DEFAULT_CHARSET));
                // 发送消息到一个Broker
                producer.sendOneway(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 如果不再发送消息，关闭Producer实例。
//        producer.shutdown();
    }


    // 发送异步消息
    @Test
    public void product_queue(){
        // 实例化消息生产者,制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("myGroup1");
        //设置NameServer地址
        producer.setNamesrvAddr("121.40.229.114:9876");
        // 启动生产者
        try {
            producer.start();
            for (int i =0;i<10;i++){
                // 创建消息，并指定Topic，Tag和消息体
                Message message = new Message("myTopic1","tag2","hello2".getBytes(RemotingHelper.DEFAULT_CHARSET));
                // 发送消息到一个Broker
                int finalI = i;
                producer.send(message, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                        return null;
                    }
                },9,9);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 如果不再发送消息，关闭Producer实例。
//        producer.shutdown();
    }


}
