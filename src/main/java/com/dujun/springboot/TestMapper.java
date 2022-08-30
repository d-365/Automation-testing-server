/*
 * author     : dujun
 * date       : 2022/8/29 15:08
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot;

import com.dujun.springboot.entity.User;
import com.dujun.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = SpringbootApplication.class)
@RunWith(SpringRunner.class)
public class TestMapper {

    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads() {
        User user =  userMapper.selectById(1);
        System.out.println(user.getAccount());
    }

}

