/**
 * author     : dujun
 * date       : 2022/1/4 10:27
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


interface task{

    void run();
    default void run2(){
        System.out.println("jjj");
    };
}


class Main{

    public static void main(String[] args) {



    }
}