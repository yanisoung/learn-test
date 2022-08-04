package com.learn.test.thead;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadUtils {

    public static String getThreadName() {
        return Thread.currentThread().getName();
    }


    public static int getActiveCount(ExecutorService executorService) {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
        return threadPoolExecutor.getActiveCount();
    }

    public static int getQueueCount(ExecutorService executorService) {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
        return threadPoolExecutor.getQueue().size();
    }
}
