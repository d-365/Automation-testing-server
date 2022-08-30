package com.dujun.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication()
// 开启SpringSecurity接口权限注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan("com.dujun.springboot.mapper")
@EnableScheduling
@EnableCaching
public class SpringbootApplication {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
