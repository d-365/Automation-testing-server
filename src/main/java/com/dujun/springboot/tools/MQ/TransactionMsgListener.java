/**
 * author     : dujun
 * date       : 2022/9/19 17:26
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.tools.MQ;

import com.dujun.springboot.entity.User;
import com.dujun.springboot.mapper.UserMapper;
import lombok.SneakyThrows;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RocketMQTransactionListener
public class TransactionMsgListener implements RocketMQLocalTransactionListener {

    @Resource
    UserMapper userMapper;

    @SneakyThrows
    @Override
    @Transactional
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        User user = new User();
        user.setAccount("本地事务");
        user.setPassword("1234");
        userMapper.insert(user);
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        System.out.println("消息回查—--执行回滚操作");
        return RocketMQLocalTransactionState.ROLLBACK;
    }

}
