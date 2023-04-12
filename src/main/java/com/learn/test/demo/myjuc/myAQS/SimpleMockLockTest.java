package com.learn.test.demo.myjuc.myAQS;

import com.learn.test.demo.myjuc.IncrementData;
import com.learn.test.thead.ThreadPoolExecutorUtil;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;

public class SimpleMockLockTest {

    @Test
    public void test_success() throws InterruptedException {
        int count = 1000;
        int threadCount = 10;
        Lock locked = new SimpleMockLock();
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorUtil.getThreadPoolExecutor();
        for (int i = 0; i < threadCount; i++) {
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < count; i++) {
                        IncrementData.lockAndFastIncrease(locked);
                    }
                    countDownLatch.countDown();
                }
            });

        }
        countDownLatch.await();
        System.out.println(IncrementData.sum);
        threadPoolExecutor.shutdown();
    }
}
