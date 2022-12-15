/*
 * author     : dujun
 * date       : 2022/7/28 14:50
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

public class Temp {

    static TestInterface testInterface;

    public Temp() {
    }


    public static void main(String[] args) {
        TestInterface  testInterface = Temp::new;

    }

}

interface TestInterface{

    Temp fun2();

}
