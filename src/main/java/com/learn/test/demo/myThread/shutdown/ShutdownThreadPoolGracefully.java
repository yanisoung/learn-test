package com.learn.test.demo.myThread.shutdown;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 优雅的关闭线程池
 */
public class ShutdownThreadPoolGracefully {


    public static void shutdownThreadPoolGracefully(ExecutorService threadPool) {
        //已经关闭则返回
        if (!(threadPool instanceof ExecutorService) || threadPool.isTerminated()) {
            return;
        }
        try {
            //1.调用shutdown 拒绝接受新任务
            threadPool.shutdown();
        } catch (SecurityException e) {
            return;
        } catch (NullPointerException e) {
            return;
        }
        try {
            // 2.执行awaitTermination 等待 60 s，等待线程池中的任务完成执行
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                // 3.如果awaitTermination执行失败，调用 shutdownNow 取消正在执行的任务
                threadPool.shutdownNow();
                // 4.再次调用awaitTermination，再次等待 60 s，如果还未结束，可以再次尝试，或则直接放弃
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("线程池任务未正常执行结束");
                }
            }
        } catch (InterruptedException ie) {
            // 5.捕获异常，重新调用 shutdownNow
            threadPool.shutdownNow();
        }
        //6.仍然没有关闭，循环关闭 1000 次，每次等待 10 毫秒
        if (!threadPool.isTerminated()) {
            try {
                for (int i = 0; i < 1000; i++) {
                    if (threadPool.awaitTermination(10, TimeUnit.MILLISECONDS)) {
                        break;
                    }
                    threadPool.shutdownNow();
                }
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            } catch (Throwable e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
