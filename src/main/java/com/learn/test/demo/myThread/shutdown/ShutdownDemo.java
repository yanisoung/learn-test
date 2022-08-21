package com.learn.test.demo.myThread.shutdown;

import com.learn.test.PrintUtils;
import com.learn.test.demo.myThread.executors.ScheduledThreadExecutors;
import com.learn.test.demo.myThread.thread.MyThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 注册JVM关闭时的钩子函数
 */
public class ShutdownDemo {

    private static ScheduledExecutorService scheduledExecutorService = ScheduledThreadExecutors.getScheduledThreadPool(2);


    //懒汉式单例创建线程池：用于执行定时、顺序任务
    static {
        //注册 JVM 关闭时的钩子函数
        Runtime.getRuntime().addShutdownHook(
                new ShutdownHookThread("定时和顺序任务线程池",
                        new Callable<Void>() {
                            @Override
                            public Void call() throws Exception {
                                //优雅关闭线程池
                                PrintUtils.print("关闭执行了");
                                ShutdownThreadPoolGracefully.shutdownThreadPoolGracefully(scheduledExecutorService);
                                return null;
                            }
                        }));
    }


    static class ShutdownHookThread extends Thread {
        private String name;
        private Callable callable;

        public ShutdownHookThread(String name, Callable callable) {
            this.name = name;
            this.callable = callable;
        }

        @Override
        public void run() {
            try {
                callable.call();
            } catch (Exception e) {

            }
        }
    }


    public static void main(String[] args) {
        scheduledExecutorService.submit(new MyThread());
    }
}
