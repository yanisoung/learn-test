package com.learn.test.demo.myjuc.atomic;

import com.learn.test.thead.ThreadPoolExecutorUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子操作类：整数型
 */
public class AtomicIntegerDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorUtil.getThreadPoolExecutor();
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        atomicInteger.incrementAndGet();
                    }
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(atomicInteger.get());
        threadPoolExecutor.shutdown();
    }
}
