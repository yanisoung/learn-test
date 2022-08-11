package com.learn.test.demo.myThread.executors;

import com.learn.test.PrintUtils;
import com.learn.test.thead.SleepUtils;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 周期性调度线程池
 */
public class ScheduledThreadDemo {

    private static AtomicInteger COUNT = new AtomicInteger(1);

    static class ScheduledThread implements Runnable {
        private String name;

        public ScheduledThread() {
            this.name = "task-" + COUNT.get();
            COUNT.incrementAndGet();
        }

        @Override
        public void run() {
            PrintUtils.printPool(name + "开始执行了," + System.currentTimeMillis());
            for (int i = 0; i < 100000; i++) {

            }
            PrintUtils.printPool(name + "执行结束了");
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = ScheduledThreadExecutors.getScheduledThreadPool(2);
        for (int i = 0; i < 2; i++) {
            //0 首次执行任务的延迟时间，500 任务执行的时间间隔，TimeUnit.MICROSECONDS 时间间隔单位
//            scheduledExecutorService.scheduleAtFixedRate(new ScheduledThread(), 0, 5000, TimeUnit.MICROSECONDS);
            scheduledExecutorService.scheduleWithFixedDelay(new ScheduledThread(), 0, 7000, TimeUnit.MICROSECONDS);

        }
        SleepUtils.sleep(1000);
        scheduledExecutorService.shutdown();
    }

}
