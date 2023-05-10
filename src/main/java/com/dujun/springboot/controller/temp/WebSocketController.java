/**
 * author     : dujun
 * date       : 2022/9/21 17:14
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.controller.temp;


import com.dujun.springboot.config.config.WebSocket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/socket")
public class WebSocketController {

    @Resource
    WebSocket webSocket;

    @GetMapping("/accept")
    public void acceptMessage(@RequestParam String message){
        System.out.println("服务端接收 ：  "+message);
        webSocket.sendMessage("服务端下发的消息");
    }


}
