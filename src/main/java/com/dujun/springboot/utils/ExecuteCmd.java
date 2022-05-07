/*
 * author     : dujun
 * date       : 2022/4/27 14:48
 * description: 执行CMD命令
 */

package com.dujun.springboot.utils;

import java.io.*;

public class ExecuteCmd {

    public static void execute(String cmd) {

        byte[] buffer = new byte[1024];
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            // 错误输出流
            Thread thread = new Thread(() -> {
                    System.out.println(1);
                    OutputStream outputStreamError = new ByteArrayOutputStream();
                    InputStream errorStream = p.getErrorStream();
                    while (true){
                        try {
                            while (errorStream.read(buffer) !=-1){
                                outputStreamError.write(buffer,0,buffer.length);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

            });
            thread.start();
            // 标准输出流
            InputStream successStream = p.getInputStream();

            while (successStream.read(buffer) !=-1 ){
                outStream.write(buffer,0,buffer.length);
            }
            p.waitFor();
            thread.interrupt();
            System.out.println("执行完成");
            System.out.println(outStream.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
