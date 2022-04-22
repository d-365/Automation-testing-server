package com.dujun.springboot.utils;

import com.dujun.springboot.entity.User;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;


class JwtUtilTest {

    @Test
    public void test1(){
        User user = new User();
        String token = JwtUtil.sign(user);
    }

}