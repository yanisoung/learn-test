package com.learn.test.demo.myThread.executors;

import com.learn.test.PrintUtils;
import com.learn.test.thead.SleepUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 可缓存的线程池
 * 1.线程池大小无限制，极端情况下如果提交大量异步线程，可能会耗尽资源
 * 2.当不存在空闲线程时，就会新建线程来执行任务
 * 3.线程空闲60s后会被回收
 */
public class CachedThreadPoolDemo {

    private static AtomicInteger COUNT = new AtomicInteger(1);

    static class CachedThread implements Runnable {
        private String name;

        public CachedThread() {
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
        ExecutorService cachedThreadPool = ThreadExecutors.getCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            cachedThreadPool.execute(new CachedThread());
            cachedThreadPool.submit(new CachedThread());
        }
        SleepUtils.sleep(1000);
        cachedThreadPool.shutdown();
    }
}
