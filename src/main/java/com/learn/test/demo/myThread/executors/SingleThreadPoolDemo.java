package com.learn.test.demo.myThread.executors;


import com.learn.test.thead.SleepUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单线程化线程池
 * 1.只有一个线程，按照任务提交的顺序，依次执行加入队列的任务
 * 2.如果不调用shutdown()方法来关闭线程池，那么线程池中的线程时间就是无限的
 * 3.当唯一的线程执行繁忙时，新加入的任务会被放入阻塞无解队列，队列的最大线程数是Integer.MAX_VALUE
 * 4.调用shutdown()方法后，线程池不会马上关闭，会等待添加到队列中的所有任务执行完毕后退出；
 * 同时调用过shutdown()后无法再向线程池内添加新的任务，添加新的任务则会报java.util.concurrent.RejectedExecutionException
 */
public class SingleThreadPoolDemo {
    static AtomicInteger count = new AtomicInteger(1);

    static class TargetTask implements Runnable {

        private String name;

        public TargetTask() {
            name = "task-" + count.get();
            count.incrementAndGet();
        }


        @Override
        public void run() {
            System.out.println(name + "开始执行了");
            for (int i = 0; i < 10; i++) {

            }
            System.out.println(name + "执行了结束了");
        }
    }

    public static void main(String[] args) {
        ExecutorService singleThreadPool = ThreadExecutors.getSingleThreadPool();
        for (int i = 0; i < 5; i++) {
            singleThreadPool.execute(new TargetTask());
        }
        singleThreadPool.shutdown();
        System.out.println("执行了shutdown");
        SleepUtils.sleep(1000);
        singleThreadPool.execute(new TargetTask());
    }
}
