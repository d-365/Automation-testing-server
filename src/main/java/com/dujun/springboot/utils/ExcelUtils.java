/*
 * author     : dujun
 * date       : 2022/9/5 17:57
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.utils;

import com.dujun.springboot.config.annotation.ExportAnnotation;
import lombok.var;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.platform.commons.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExcelUtils {

    /**
     * 创建Excel
     * @param list 数据列表
     * @param sheetName sheet页名称
     * @param tClass  实体类class类信息
     * @param <T> 导出实体类VO
     * @return  HSSFWorkbook对象
     * @throws IllegalAccessException 实体类字段访问权限异常
     */
    public static  <T> HSSFWorkbook createExcel(List<T> list, String sheetName, Class<T> tClass) throws IllegalAccessException, InterruptedException {
        Instant begin = Instant.now();
        // 数据列表需要分割的次数
        // 需要分割的数量
        BigDecimal needSplitNumber = new BigDecimal(10000);
//        BigDecimal page = new BigDecimal(3);
//        BigDecimal bigDecimal = total.divide(page,0, RoundingMode.CEILING);
        HSSFWorkbook wb = new HSSFWorkbook();
        // 设置Excel表格样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFFont fontStyle = wb.createFont();
        fontStyle.setFontHeightInPoints((short)14);
//        设置单元格背景色
//        style.setFillForegroundColor(IndexedColors.RED.getIndex());
//        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(fontStyle);
        sheetName = StringUtils.isBlank(sheetName)? "sheet1" : sheetName;
        HSSFSheet wbSheet = wb.createSheet(sheetName);
        wbSheet.setDefaultColumnWidth(30);
        HSSFRow row = wbSheet.createRow(0);
        row.setHeightInPoints(20.0F);
        // 获取写入表格列字段信息
        Field[] fields = tClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            var exportAnnotations = fields[i].getAnnotation(ExportAnnotation.class);
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(exportAnnotations.value());
        }
        // 判断userList的长度--是否可进行分割
        if (list.size() > needSplitNumber.intValue()){
            // 需要进行分割次数
            BigDecimal listSize = new BigDecimal(list.size());
            BigDecimal splitCounts = listSize.divide(needSplitNumber,0,RoundingMode.CEILING);
            CountDownLatch countDownLatch = new CountDownLatch(splitCounts.intValue() - 2);
            int split = splitCounts.intValue();
            ExecutorService executor = Executors.newCachedThreadPool();

            for (int i = 0; i < split; i++) {
                int beginIndex = i * needSplitNumber.intValue();
                int endIndex = (i+1) * needSplitNumber.intValue();
                if (i == split-1){
                    executor.execute(new Thread(()->{
                        try {
                            workBookDataWrite(beginIndex,list.size(),list,fields,wbSheet,style);
//                            countDownLatch.countDown();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }));
                }else {
                    executor.submit(new Thread(()->{
                        try {
                            workBookDataWrite(beginIndex,endIndex,list,fields,wbSheet,style);
//                            countDownLatch.countDown();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }));
                }
            }

//            countDownLatch.await();
            executor.shutdown();
            while (!executor.isTerminated()){
                System.out.println("线程池未执行完毕");
            }
        }else {
            // 不需要进行分割
            workBookDataWrite(0,list.size(),list,fields,wbSheet,style);
        }

        // 写入表格行字段信息
//        if (list.size()!=0){
//            for (int i = 0; i < list.size(); i++) {
//                HSSFRow excel_row = wbSheet.createRow(i+1);
//                T row_data =  list.get(i);
//                for (int j = 0; j < fields.length; j++){
//                    HSSFCell cell = excel_row.createCell(j);
//                    cell.setCellStyle(style);
//                    Field field = fields[j];
//                    field.setAccessible(true);
//                    Object valueObject = field.get(row_data);
//                    if (valueObject == null) {
//                        valueObject = "";
//                    }
//                    String value;
//                    if (valueObject instanceof String) {
//                        value = String.valueOf(valueObject);
//                    } else if (valueObject instanceof Integer) {
//                        value = String.valueOf(valueObject);
//                    } else if (valueObject instanceof LocalDateTime) {
//                        value = ((LocalDateTime) valueObject).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//                    } else {
//                        value = valueObject.toString();
//                    }
//                    cell.setCellValue(value);
//                }
//
//            }
//        }
        Instant end = Instant.now();
        Duration duration = Duration.between(begin,end);
        System.out.println("数据写入EXCEL耗时："+duration.toMillis());
        return wb;
    }

    /**
     * 数据列表写入Excel
     * @param list 数据列表
     * @param beginRow 开始写入行
     * @param endRow 结束写入行
     * @param fields 字段列表
     * @param wbSheet HSSFSheet
     * @param style HSSFCellStyle
     * @param <T> 导出实体类VO
     * @throws IllegalAccessException 实体类字段访问权限异常
     */
    public static <T> void workBookDataWrite(int beginRow,int endRow,List<T> list,Field[] fields,HSSFSheet wbSheet,HSSFCellStyle style) throws IllegalAccessException {
        // 写入表格行字段信息
        if (list.size()!=0){
            for (int i = beginRow; i < endRow; i++) {
                HSSFRow excel_row = wbSheet.createRow(i+1);
                T row_data =  list.get(i);
                for (int j = 0; j < fields.length; j++){
                    HSSFCell cell = excel_row.createCell(j);
                    cell.setCellStyle(style);
                    Field field = fields[j];
                    field.setAccessible(true);
                    Object valueObject = field.get(row_data);
                    if (valueObject == null) {
                        valueObject = "";
                    }
                    String value;
                    if (valueObject instanceof String) {
                        value = String.valueOf(valueObject);
                    } else if (valueObject instanceof Integer) {
                        value = String.valueOf(valueObject);
                    } else if (valueObject instanceof LocalDateTime) {
                        value = ((LocalDateTime) valueObject).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    } else {
                        value = valueObject.toString();
                    }
                    cell.setCellValue(value);
                }
            }
        }
    }

    /**
     * 读取Excel
     *
     * @param filePath  文件路径
     * @param sheetName sheet名字
     * @return 二维数组
     */
    public static Object[][] readExcelData(String filePath, String sheetName) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1);
            for (int j = 0; j < columnCount; j++) {
                Cell cell = row.getCell(j);
                CellType cellType = cell.getCellType();
                if (cellType == CellType.STRING) {
                    data[i][j] = cell.getStringCellValue();
                } else if (cellType == CellType.NUMERIC) {
                    data[i][j] = cell.getNumericCellValue();
                } // 其他数据类型的处理

            }
        }
        workbook.close();
        file.close();
        return data;
    }


}
