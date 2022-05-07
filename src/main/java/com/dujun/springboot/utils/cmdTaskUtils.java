/*
 * author     : dujun
 * date       : 2022/4/18 14:50
 * description: 调用本机cmd命令执行窗口
 */

package com.dujun.springboot.utils;

import org.springframework.util.ResourceUtils;

import java.io.*;

public class cmdTaskUtils {

    static Process pro= null;

    // 执行命令
    public static Process execCommand(String name){
        Runtime runtime=Runtime.getRuntime();
        try {
            pro = runtime.exec(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pro;
    }

    //销毁进程
    public static void destory(){
        if (pro!=null){
            pro.destroy();
        }
    }

    // 杀死端口对应进程
    public static void killPid(String pid){
        execCommand(String.format("taskkill /F /pid %s",pid));
    }


}
