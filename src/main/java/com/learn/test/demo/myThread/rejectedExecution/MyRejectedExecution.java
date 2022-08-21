package com.learn.test.demo.myThread.rejectedExecution;

import com.learn.test.PrintUtils;
import com.learn.test.demo.myThread.thread.MyThread;
import com.learn.test.thead.SleepUtils;

import java.util.concurrent.*;

/**
 * 实现自定义拒绝策略
 */
public class MyRejectedExecution implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        PrintUtils.print("当前线程名：" + Thread.currentThread().getName());
        PrintUtils.print("taskCount：" + executor.getTaskCount());

    }

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(1, 4,
                10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), new MyRejectedExecution());
        for (int i = 0; i < 15; i++) {
            executorService.submit(new MyThread());
        }
        executorService.shutdown();
        SleepUtils.sleep(1000);
    }

}
