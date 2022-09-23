/*
 * author     : dujun
 * date       : 2022/9/21 17:12
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.config.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/webSocket/10086")
@Component
@Log4j2
public class WebSocket {

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。
    static CopyOnWriteArraySet<WebSocket> webSocketSet=new CopyOnWriteArraySet<>();

    /**
     *  建立连接成功
     */
    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        webSocketSet.add(this);
        System.out.println("建立了新的连接总数" + webSocketSet.size());
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        log.debug("连接断开，总数{}",webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message){
        log.debug("收到客户端发来的消息：{}",message);
    }

    /**
     * 发送消息
     */
    public void sendMessage(String message){
        log.debug("打印下服务端发送消息：{}",message);
        for (WebSocket webSocket:webSocketSet){
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
