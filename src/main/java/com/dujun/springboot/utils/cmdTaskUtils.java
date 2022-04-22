/*
 * author     : dujun
 * date       : 2022/4/18 14:50
 * description: 调用本机cmd命令执行窗口
 */

package com.dujun.springboot.utils;

import java.io.IOException;

public class cmdTaskUtils {

    static Process pro= null;

    // 执行命令
    public static void execCommand(String name){
        Runtime runtime=Runtime.getRuntime();
        try {
            pro = runtime.exec(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //销毁进程
    public static void destory(){
        if (pro!=null){
            pro.destroy();
        }
    }


    public static void main(String[] args) {
        cmdTaskUtils.execCommand("cmd /k start mspaint.exe");
    }

}
