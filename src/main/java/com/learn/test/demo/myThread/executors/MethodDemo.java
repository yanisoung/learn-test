package com.learn.test.demo.myThread.executors;

import com.learn.test.PrintUtils;
import com.learn.test.demo.list.MyArrayList;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * execu和submit()的区别
 */
public class MethodDemo {
    private static AtomicInteger COUNT = new AtomicInteger(1);

    static class ErrorThread implements Runnable {

        private String name;

        public ErrorThread() {
            this.name = "task-" + COUNT.get();
            COUNT.incrementAndGet();
        }

        @Override
        public void run() {
            PrintUtils.printPool(name + "开始执行了," + System.currentTimeMillis());
            if (COUNT.get() / 2 == 1) {
                throw new RuntimeException("运行出错了~");
            }
            PrintUtils.printPool(name + "执行结束了");
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = ScheduledThreadExecutors.getScheduledThreadPool(2);
        List<Future> futureList = new MyArrayList<>();
        for (int i = 0; i < 2; i++) {
            ErrorThread errorThread = new ErrorThread();
            scheduledThreadPool.execute(errorThread);
            futureList.add(scheduledThreadPool.submit(errorThread));
        }

        for (Future future : futureList) {
            try {
                future.get();
            } catch (Exception e) {
                PrintUtils.print(e);
            }
        }
        scheduledThreadPool.shutdown();
    }
}
