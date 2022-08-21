package com.learn.test.demo.myThread.executors;

import com.learn.test.PrintUtils;
import com.learn.test.demo.myThread.thread.MyThread;
import com.learn.test.thead.SleepUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/***
 *线程池的钩子方法
 */
public class ThreadPoolHooksDemo {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(2)) {
            ThreadLocal<Long> threadLocal = new ThreadLocal();

            @Override
            protected void terminated() {
                PrintUtils.print("线程池关闭了~~");
            }

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                threadLocal.set(System.currentTimeMillis());
                PrintUtils.print("beforeExecute.startTime:" + threadLocal.get());
                super.beforeExecute(t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                PrintUtils.print("afterExecute.startTime:" + threadLocal.get());
                PrintUtils.print("afterExecute.endTime:" + System.currentTimeMillis());
                threadLocal.remove();
                PrintUtils.print("afterExecute.startTime:" + threadLocal.get());
                super.afterExecute(r, t);
            }
        };
        executorService.submit(new MyThread());
        executorService.shutdown();
        SleepUtils.sleep(10000);
    }
}
