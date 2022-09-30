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
                    //为什么不同线程下，访问同一个ThreadLocal，地址值不一样？
                    System.out.println(System.identityHashCode(ThreadLocalHandler.getThreadLocal().hashCode()));
                    ThreadLocalHandler.remove();
                }
            });
        }
        executorService.shutdown();
        SleepUtils.sleep(1000);
    }
}
