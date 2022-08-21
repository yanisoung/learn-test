package com.learn.test.demo.myThread.executors;

import com.learn.test.demo.myThread.thread.MyThread;
import com.learn.test.thead.SleepUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadFactory implements ThreadFactory {

    private static AtomicInteger COUNT = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, "MyThreadFactory-" + COUNT.get());
        if (COUNT.get() / 2 == 1) {
            thread.setPriority(6);
        }
        COUNT.incrementAndGet();
        //设置为守护线程
        thread.setDaemon(true);
        return thread;
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1, new MyThreadFactory());
        for (int i = 0; i < 4; i++) {
            MyThread myThread = new MyThread();
            executorService.submit(myThread);
        }
        executorService.shutdown();
        SleepUtils.sleep(10000);
    }
}
