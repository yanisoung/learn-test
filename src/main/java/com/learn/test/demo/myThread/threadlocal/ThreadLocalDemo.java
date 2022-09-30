package com.learn.test.demo.myThread.threadlocal;

import com.learn.test.demo.myThread.executors.CPUThreadPoolExecutors;
import com.learn.test.thead.SleepUtils;

import java.util.concurrent.ExecutorService;

public class ThreadLocalDemo {


    public static void main(String[] args) {
        ExecutorService executorService = CPUThreadPoolExecutors.get();
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    String s = String.valueOf(finalI);
                    ThreadLocalHandler.set(s);
                    System.out.println(ThreadLocalHandler.get());
                    //不同线程下访问同一个ThreadLocal，都是同一个TreadLocal实例，地址值是一样的
                    System.out.println(System.identityHashCode(ThreadLocalHandler.getThreadLocal()));
                    ThreadLocalHandler.remove();
                }
            });
        }
        executorService.shutdown();
        SleepUtils.sleep(1000);
    }
}
