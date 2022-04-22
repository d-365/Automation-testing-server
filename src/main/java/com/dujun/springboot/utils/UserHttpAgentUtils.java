/*
 * author     : dujun
 * date       : 2022/4/18 11:40
 * description: http客户端请求信息获取工具类
 */

package com.dujun.springboot.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserHttpAgentUtils {

    // 获取用户IP地址
    public static String getUserRealIP(HttpServletRequest request) throws UnknownHostException {
        String ip = "";

        // 有的user可能使用代理，为处理用户使用代理的情况，使用x-forwarded-for

        if  (request.getHeader("x-forwarded-for") == null)  {

            ip = request.getRemoteAddr();

        }  else  {

            ip = request.getHeader("x-forwarded-for");

        }

        if  ("127.0.0.1".equals(ip))  {

            // 获取本机真正的ip地址

            ip = InetAddress.getLocalHost().getHostAddress();

        }
        return ip;
    }

    // 获取用户http请求头信息
    public static String getUserAgent(HttpServletRequest request,String headerName){
        return request.getHeader(headerName);
    }

    // 获取用户请求浏览器版本信息
    public static String geUserBrowser(HttpServletRequest request){
        String rawBrowser = request.getHeader("User-Agent");
        String result = null;
        Pattern pattern = Pattern.compile("^Mozilla.*?[)].*?[)](.*?)Safari.*?$");
        Matcher matcher = pattern.matcher(rawBrowser);
        if (matcher.find()){
            result = matcher.group(1);
        }
        return result;
    }


}
