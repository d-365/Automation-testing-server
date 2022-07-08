/**
 * author     : dujun
 * date       : 2022/1/18 15:37
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dateTools {

    public static String currentTime(){
        Calendar date  = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date.getTime());
    }

    /**
     * @param day -day day天前  day day天后
     * @return String
     */
    public  static  String currentTime(int day){
        Calendar calendar  = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(calendar.getTime());
    }

    public  static  String currentDay(int day){
        Calendar calendar  = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(calendar.getTime());
    }

    public static void main(String[] args) {
        for (int i=0;i>-7;i--){
            System.out.println(dateTools.currentDay(i));
        }
    }


}
