/*
 * author     : dujun
 * date       : 2022/6/17 17:26
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;


import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class Temp1 {

    @Test
    public void test_fun1(){
        Stream<String> stream = Stream.of("1","2","3");
        Stream<String> stream2 = Stream.of("1","2","3");
        Stream<String> stream3 = Stream.concat(stream,stream2);
        stream3.forEach(s -> System.out.println(s));

    }

}
