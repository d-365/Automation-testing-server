/**
 * author     : dujun
 * date       : 2022/1/17 17:15
 * description: 字符串工具类
 */

package com.dujun.springboot.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTools {

    public static String parseToList(String target){
        String expression = "(.*?\\[)(.*?)(\\].*?)";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(target);
        if(matcher.find()){
            return matcher.group(2);
        }else {
            return "";
        }
    }

    public static String[] split(String target,String unit){
           return target.split(unit);
    }

    public static void main(String[] args) {
        String s = "[hh][ff]";
        System.out.println(parseToList(s));
    }
}
