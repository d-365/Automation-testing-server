package com.dujun.springboot.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class ToolsServiceImplTest {

//    @Autowired
//    private StringRedisTemplate redisTemplate;

    @Test
    public void fun1(){
//        ValueOperations<String, String> forValue = redisTemplate.opsForValue();
//        String k1 = forValue.get("k1");
        System.out.println("k1");
    }

}