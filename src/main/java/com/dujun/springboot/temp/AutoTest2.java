/*
 * author     : dujun
 * date       : 2023/5/9 15:59
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import org.testng.annotations.Test;


@Test(groups = "B")
public class AutoTest2 {

//    @Test
//    public void test_1() throws InterruptedException {
//        Thread.sleep(3000);
//        System.out.println("1执行");
//    }
//
//    @Test
//    public void test_11() throws InterruptedException {
//        Thread.sleep(3000);
//        System.out.println("1执行");
//    }
//    @Test
//    public void test_21() throws InterruptedException {
//        Thread.sleep(3000);
//        System.out.println("1执行");
//    }
//
//    @Test
//    public void test_4() throws InterruptedException {
//        Thread.sleep(3000);
//        System.out.println("4执行");
//    }
//
//    @Test
//    public void test_5() throws InterruptedException {
//        Thread.sleep(3000);
//        System.out.println("5执行");
//    }
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

    public void test_9() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("文件B ----1执行");
    }

    //    @Test
    public void test_10() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("文件B ----2执行");
    }

    public void t11() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("文件B ----3执行");
    }


    public String test_2() {
        System.out.println("2执行");
        return "2执行完了";
    }

    public String test_fun3() {
        System.out.println("3开始执行");
        System.out.println(test_2());
        return "";
    }

}
