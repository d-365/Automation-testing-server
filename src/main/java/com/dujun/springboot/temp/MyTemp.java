/*
 * author     : dujun
 * date       : 2022/3/21 15:45
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;


import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

class myTest{
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println(serverSocket.getReuseAddress());
    }
}