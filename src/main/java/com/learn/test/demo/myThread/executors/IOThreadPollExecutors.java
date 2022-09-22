package com.learn.test.demo.myThread.executors;

import com.learn.test.PrintUtils;
import com.learn.test.thead.ThreadUtils;

import java.util.concurrent.*;

/**
 * 针对IO密集型比较多的任务，创建对于的IO密集型线程
 */
public class IOThreadPollExecutors {

    /**
     * 针对IO密集型线程池，核心线程数一般是CPU核心数的2倍
     */
    static ExecutorService threadExecutors = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2,
            Runtime.getRuntime().availableProcessors() * 2, 30, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(128));


    public static void main(String[] args) {
        System.out.println(ThreadUtils.getCorePoolSize(threadExecutors));
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
