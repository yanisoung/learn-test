package com.learn.test.demo.myThread.executors;

import com.learn.test.PrintUtils;
import com.learn.test.thead.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 固定线程数线程池
 * 1.固定线程数线程池 每提交一个任务，则新增一个线程，直到线程池内线程数达到指定固定个数后，不再新增线程。
 * 2.如果某个线程因为执行时出现异常而终止，那么线程池则会补充一个新的线程到线程池内
 * 3.当线程池内的线程全部在繁忙状态时，新加入的任务会被放到无界队列中
 */
public class FixedThreadPoolDemo {

    private static AtomicInteger COUNT = new AtomicInteger(1);

    static class FixedThread implements Runnable {
        private String name;

        public FixedThread() {
            this.name = "task-" + COUNT.get();
            COUNT.incrementAndGet();
        }

        @Override
        public void run() {
            PrintUtils.printPool(name + "开始执行了");
            for (int i = 0; i < 10; i++) {

            }
            if (COUNT.get() * 2 != 0) {
                throw new RuntimeException();
            }
            PrintUtils.printPool(name + "执行结束了");
        }
    }

    /**
     * 增加指定线程数
     * 每提交一个任务，则新增一个线程，直到线程池内线程数达到指定固定个数后，不再新增线程
     * 如果线程遇到异常而终止，则会添加一个新的线程补充进线程池
     */
    static void addFixedThread() {
        //线程池固定5个线程
        ExecutorService fixedThreadPool = ThreadExecutors.getFixedThreadPool(5);
        //提交3个任务，看下线程池内的线程个数
        for (int i = 0; i < 3; i++) {
            fixedThreadPool.execute(new FixedThread());
        }
        PrintUtils.printPool("提交3个任务，当前活跃线程池数：" + ThreadUtils.getActiveCount(fixedThreadPool));
        //提交5个任务，看下线程池内的线程个数
        for (int i = 0; i < 2; i++) {
            fixedThreadPool.execute(new FixedThread());
        }
        PrintUtils.printPool("提交5个任务，当前活跃线程池数：" + ThreadUtils.getActiveCount(fixedThreadPool));
        //提交5个以上任务，看下线程池内的线程个数
        for (int i = 0; i < 2; i++) {
            fixedThreadPool.execute(new FixedThread());
        }
        PrintUtils.printPool("提交5个以上任务，当前活跃线程池数：" + ThreadUtils.getActiveCount(fixedThreadPool));

        fixedThreadPool.shutdown();
    }


    public static void main(String[] args) {
        addFixedThread();
    }
}
