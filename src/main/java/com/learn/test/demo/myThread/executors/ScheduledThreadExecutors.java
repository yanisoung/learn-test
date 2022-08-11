package com.learn.test.demo.myThread.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduledThreadExecutors {

    /**
     * 创建一个单线程的 可调度的线程池
     */
    public static ScheduledExecutorService SINGLE_SCHEDULED_THREAD_POOL = Executors.newSingleThreadScheduledExecutor();



    public static ScheduledExecutorService getSingleScheduledThreadPool() {
        return SINGLE_SCHEDULED_THREAD_POOL;
    }

    public static ScheduledExecutorService getScheduledThreadPool(int size) {
        return Executors.newScheduledThreadPool(size);
    }

}
