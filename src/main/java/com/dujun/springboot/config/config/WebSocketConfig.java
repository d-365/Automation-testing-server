/*
 * author     : dujun
 * date       : 2022/9/21 17:10
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.config.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
