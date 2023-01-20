package com.learn.test.demo.myjuc.atomic;

import com.learn.test.PrintUtils;
import com.learn.test.demo.myThread.executors.ThreadExecutors;
import com.learn.test.thead.SleepUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * LongAdder：提供CAS高并发场景下的性能
 * @author duomao
 */
public class LongAdderDemo {

    public static void main(String[] args) {
//        当数据量达到10w以上的计算量时，longAdder的性能优化才显著的体现出来
        atomicDemo();
        longAdderDemo();
        SleepUtils.sleep(100000);
    }

    private static void atomicDemo() {
        long start = System.currentTimeMillis();
        int threadCnt = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadCnt);
        AtomicLong atomicLong = new AtomicLong(0L);
        ExecutorService fixedThreadPool = ThreadExecutors.getFixedThreadPool(threadCnt);
        for (int i = 0; i < threadCnt; i++) {
            fixedThreadPool.execute(() -> {
                for (int j = 0; j < 1000000; j++) {
                    atomicLong.incrementAndGet();
                }
                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            PrintUtils.print(e.getMessage());
        }
        fixedThreadPool.shutdown();

        PrintUtils.print(System.currentTimeMillis() - start);
        PrintUtils.print("atomicDemo结果：" + atomicLong.get());
        PrintUtils.printSplitLine();
    }

    private static void longAdderDemo() {
        long start = System.currentTimeMillis();
        int threadCnt = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadCnt);
        LongAdder longAdder = new LongAdder();
        ExecutorService fixedThreadPool = ThreadExecutors.getFixedThreadPool(threadCnt);
        for (int i = 0; i < threadCnt; i++) {
            fixedThreadPool.execute(() -> {
                for (int j = 0; j < 1000000; j++) {
                    longAdder.add(1);
                }
                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            PrintUtils.print(e.getMessage());
        }
        fixedThreadPool.shutdown();

        PrintUtils.print(System.currentTimeMillis() - start);
        PrintUtils.print("longAdderDemo结果：" + longAdder.longValue());
        PrintUtils.printSplitLine();
    }
}
