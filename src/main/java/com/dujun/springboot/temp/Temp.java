/*
 * author     : dujun
 * date       : 2022/6/1 18:11
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import org.apache.tomcat.jni.Poll;
import org.junit.jupiter.api.Test;
import org.testng.collections.Lists;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Temp {
    public static void main(String[] args) throws Exception {

        CompletableFuture<String> noReturn  = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步执行----"+Thread.currentThread().getName());
            FutureTask<String> futureTask = new FutureTask<>(new threadResult());
            Thread thread = new Thread(futureTask);
            thread.start();
            return "异步执行结果";
        });
        System.out.println("主线程开始执行");
        noReturn.complete("我是新设置的值");
        System.out.println(noReturn.get());
        System.out.println("主线程执行结束");
        Thread.sleep(4000);
        System.out.println(noReturn.get());
    }
}

class MyAsynchronous{

    @Test
    public void fun1(){

    }

}

class Temp2{
    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new threadResult());
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());
    }
}

class threadResult implements Callable<String> {
    private Lock lock = new ReentrantLock();

    @Override
    public  String call(){
        lock.lock();
        lock.lock();
        try {
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName()+"线程开始执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            lock.unlock();
        }

        return "获取到了线程中的结果";
    }

}

class threadNoResult implements Runnable{

    @Override
    public void run() {
        System.out.println("线程执行了");
    }

}


class Test_dujun{

    private List<Integer> pool = new ArrayList<Integer>();

    public synchronized void fun1() {
        System.out.println(String.format("剩余%s",pool.size()));
        while (pool.size()==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"正在消费");
        pool.clear();
        notify();
    }

    public synchronized void fun2() {

        while (pool.size()>1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"制作完成");
        pool.add(1);
        notify();
    }


    @Test
    public void test_fun1(){
//        synchronized (Test_dujun.class){

            CompletableFuture<String> futureOne = CompletableFuture.supplyAsync(()-> {
                System.out.println(Thread.currentThread().getName()+"异步一开始执行");
                return "one";
            });
            CompletableFuture<String> futureTwo = CompletableFuture.supplyAsync(() ->{
                System.out.println(Thread.currentThread().getName()+"异步二开始执行");
                return "tow";
            });
            CompletableFuture<String> futureThree = CompletableFuture.supplyAsync(() ->{
                System.out.println(Thread.currentThread().getName()+"异步三 开始执行--完毕");
                return "three";
            });
            CompletableFuture<Void> futureFinal = CompletableFuture.runAsync(()->{
                System.out.println("执行最后的异步任务--完毕");
            });

            System.out.println("主线程执行中");

            CompletableFuture<String> futureOne2 = CompletableFuture.supplyAsync(()-> {
                System.out.println(Thread.currentThread().getName()+"异步2 一开始执行");
                return "one";
            });
            CompletableFuture<String> futureTwo2 = CompletableFuture.supplyAsync(() ->{
                System.out.println(Thread.currentThread().getName()+"异步2 二开始执行");
                return "tow";
            });
            CompletableFuture<String> futureThree2 = CompletableFuture.supplyAsync(() ->{
                System.out.println(Thread.currentThread().getName()+"异步2 三 开始执行--完毕");
                return "three";
            });
            CompletableFuture<Void> futureFinal2 = CompletableFuture.runAsync(()->{
                System.out.println("执行最后的异步2 任务--完毕");
            });

            System.out.println("主线程执行完毕");
//        }
    }

    @Test
    public void test_fun2(){
        CompletableFuture.supplyAsync(()->{
            //发生异常
            int i = 10/0;
            return "Success";
        }).handle((response,exception)->{
            System.out.println(response);
            exception.printStackTrace();
            return "error";
        }).thenApply((s -> {
            System.out.println(s);
            return "执行完毕";
        }));
        CompletableFuture.runAsync(()-> System.out.println("CompletableFuture.runAsync"));

    }

    @Test
    public void  test_fun3_IO() throws Exception{
        String path = "C:\\Users\\dujun\\Downloads\\d.png";
        FileInputStream file = new FileInputStream(path);
        OutputStream outputStream = new FileOutputStream("C:\\Users\\dujun\\Downloads\\d2.png");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(file);
        byte[] bytes = new byte[1024];
        while (bufferedInputStream.read(bytes)!=-1){
            outputStream.write(bytes);
        }
    }

    @Test
    public void test__fun4(){
        String s  = "dujun";
        int d = 0;
        System.out.println(s.getClass().getName());
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        ExecutorService executorService = Executors.newCachedThreadPool();
        threadResult threadResult = new threadResult();
        Future<String> future = executorService.submit(threadResult);
        Thread thread = new Thread(new threadNoResult());
        LockSupport.park(thread);
        LockSupport.unpark(thread);
    }


}
