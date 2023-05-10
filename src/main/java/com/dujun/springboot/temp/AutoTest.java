/*
 * author     : dujun
 * date       : 2023/5/9 15:59
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import org.testng.annotations.Test;

@Test
public class AutoTest {

    public void test_1() {
        System.out.println("1执行");
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
