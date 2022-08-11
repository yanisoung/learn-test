package com.learn.test.demo.myThread.executors;

import com.learn.test.PrintUtils;
import com.learn.test.thead.SleepUtils;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单线程 可调度线程池
 * 1.线程池内只有一个线程，串行执行任务，只有上一个任务执行结束了，才会执行下个
 */
public class SingleScheduledThreadDemo {

    private static AtomicInteger COUNT = new AtomicInteger(1);

    static class SingleScheduledThread implements Runnable {
        private String name;

        public SingleScheduledThread() {
            this.name = "task-" + COUNT.get();
            COUNT.incrementAndGet();
        }

        @Override
        public void run() {
            PrintUtils.printPool(name + "开始执行了");
            for (int i = 0; i < 10; i++) {

            }
            PrintUtils.printPool(name + "执行结束了");
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService singleThreadScheduledPool = ScheduledThreadExecutors.getSingleScheduledThreadPool();
        for (int i = 0; i < 5; i++) {
            singleThreadScheduledPool.execute(new SingleScheduledThread());
            singleThreadScheduledPool.submit(new SingleScheduledThread());
        }
        SleepUtils.sleep(1000);
        singleThreadScheduledPool.shutdown();
    }
}
