/*
 * author     : dujun
 * date       : 2022/3/21 15:45
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class myTest{
    public static void main(String[] args) throws Exception {

        ExecutorService es = Executors.newCachedThreadPool();
        Task task = new Task("dujun");
        Future<String> future =es.submit(task);
        System.out.println(future.get());
        System.out.println("执行完毕了");

    }
}

@Data
@AllArgsConstructor
class Task implements Callable<String> {

    String name;

    @Override
    public String call() throws Exception {
        System.out.println(name);
        return name;
    }

}