/*
 * author     : dujun
 * date       : 2022/4/18 14:50
 * description: 调用本机cmd命令执行窗口
 */

package com.dujun.springboot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    /**
     * 执行命令--获取结果
     * @param command 命令
     * @return 命令执行结果
     */
    public static String getCmdResult(String command) throws IOException {
        Process process = execCommand(command);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = br.readLine())!=null){
            stringBuilder.append(line).append(",");
        }
        return String.valueOf(stringBuilder);
    }

}
