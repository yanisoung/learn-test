package com.learn.test.demo.myThread.wait;

import com.learn.test.PrintUtils;
import com.learn.test.demo.myThread.executors.ThreadExecutors;

import java.util.concurrent.ExecutorService;

public class WaitDemo {

    Object obj = new Object();

    public void waitDemo() {
        PrintUtils.printClassLayout(obj);
        synchronized (obj) {
            PrintUtils.printClassLayout(obj);
            try {
                obj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        WaitDemo waitDemo = new WaitDemo();
        waitDemo.waitDemo();
        ExecutorService fixedThreadPool = ThreadExecutors.getFixedThreadPool();
        fixedThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                WaitDemo waitDemo = new WaitDemo();
                waitDemo.waitDemo();
            }
        });
        fixedThreadPool.shutdown();
    }

}
