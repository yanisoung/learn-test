package com.learn.test.demo.myThread.executors;

import com.learn.test.thead.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 针对CPU密集型的线程池
 */
public class CPUThreadPoolExecutors {
    /**
     * 针对CPU密集型线程池，核心线程数一般是CPU核心数，因为如果线程数大于CPU核心数，则会频繁的切换上下文，就会导致大量消耗在切换上下文上的时间上，
     * 为了提高效率，一般线程池的核心数就是CPU的核心数，这样效率最高
     * <p>
     * 核心线程池与最大线程池数一致，是为了当新任务来时创建新的线程数，而不是将任务放到队列继续等待。
     * 使用有界队列而非无界队列防止突然有大量任务提交时，导致资源耗尽，128如果不够，可根据真实场景来修改
     */
    static ExecutorService threadExecutors = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(), 30, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(128));


    public static void main(String[] args) {
        System.out.println(ThreadUtils.getCorePoolSize(threadExecutors));
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
