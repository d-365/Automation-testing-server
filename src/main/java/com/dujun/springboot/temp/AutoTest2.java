/*
 * author     : dujun
 * date       : 2023/5/9 15:59
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AutoTest2 {

    @Parameters({"name", "address"})
    public void test_1(@Optional("usu") String na, @Optional("河南") String ad) throws InterruptedException {
        System.out.println("A - 1执行");
    }

    @Test()
    public void test_11() throws InterruptedException {
        System.out.println(" A - 2执行");
    }


//
//    @Test
//    public void test_6() throws InterruptedException {
//        Thread.sleep(3000);
//        System.out.println("5执行");
//    }
//    @Test
//    public void test_7() throws InterruptedException {
//        Thread.sleep(3000);
//        System.out.println("5执行");
//    }
//    @Test
//    public void test_8() throws InterruptedException {
//        Thread.sleep(3000);
//        System.out.println("5执行");
//    }
//    public void test_9() {
//        System.out.println("文件B ----1执行");
//    }
//
//    public void test_10() throws InterruptedException {
//        Thread.sleep(3000);
//        System.out.println("文件B ----2执行");
//    }
//
//    public void t11() throws InterruptedException {
//        Thread.sleep(3000);
//        System.out.println("文件B ----3执行");
//    }
//
//    public String t2() {
//        System.out.println("2执行");
//        return "2执行完了";
//    }
//
//    public String test_fun3() {
//        System.out.println("3开始执行");
//        System.out.println(t2());
//        return "";
//    }

}
