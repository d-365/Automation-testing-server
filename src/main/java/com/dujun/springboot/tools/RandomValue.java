/**
 * author     : dujun
 * date       : 2022/1/4 11:53
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.tools;

import org.testng.annotations.Test;

public class RandomValue {

    // 返回随机一位整数
    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

    /**
     * 返回手机号码
     */
    private static final String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    public static String getTel() {
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }

    /**
     * 返回中国城市
     */
    private  static final String[] address = {"北京市","重庆市","中山市","潮州市","东莞市","广州市","佛山市","河源市","惠州市","揭阳市","江门市","茂名市","梅州市","汕尾市","汕头市","韶关市","清远市","深圳市","杭州市","嘉兴市","金华市","丽水市","宁波市","衢州市","绍兴市","台州市","温州市","舟山市","上海市"};
    public   static String getAddress(){
        int index = getNum(0,address.length);
        return address[index];
    }

    @Test
    public void test(){
        System.out.println(getAddress());
    }

}


