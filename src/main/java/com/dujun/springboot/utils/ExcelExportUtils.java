/**
 * author     : dujun
 * date       : 2022/9/6 11:11
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Duration;
import java.time.Instant;

public class ExcelExportUtils {

    public static void resSetting(HttpServletResponse response){
        response.setContentType("application/force-download;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    public static void resReturn(HttpServletResponse response,HSSFWorkbook hssfWorkbook){
        Instant begin = Instant.now();
        try (
                OutputStream outputStream = response.getOutputStream();
//                ByteArrayOutputStream bos = new ByteArrayOutputStream()
        ){
            // Excel对象转换成字节数组
            hssfWorkbook.write(outputStream);
//            outputStream.write(bos.toByteArray());
            // 将字节数据进行返回
            resSetting(response);
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Instant end = Instant.now();
        Duration duration = Duration.between(begin,end);
        System.out.println("Excel转为字节数组流耗时：" + duration.toMillis());
    }
}
