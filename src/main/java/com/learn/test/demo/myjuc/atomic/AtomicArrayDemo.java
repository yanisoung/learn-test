package com.learn.test.demo.myjuc.atomic;

import com.learn.test.thead.ThreadPoolExecutorUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子操作类：数组型
 */
public class AtomicArrayDemo {

    public static void main(String[] args) {
        int[] array = {1, 2, 3};
        CountDownLatch countDownLatch = new CountDownLatch(3);
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(array);
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorUtil.getThreadPoolExecutor();
        for (int i = 0; i < 3; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 3; j++) {
                        atomicIntegerArray.getAndAdd(0, j);
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
        System.out.println(atomicIntegerArray.get(0));
        threadPoolExecutor.shutdown();
    }
}
