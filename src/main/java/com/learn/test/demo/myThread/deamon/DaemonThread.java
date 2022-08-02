package com.learn.test.demo.myThread.deamon;

import com.learn.test.PrintUtils;
import com.learn.test.demo.myThread.thread.MyThread;
import com.learn.test.thead.SleepUtils;

/**
 * 守护线程
 */
public class DaemonThread extends Thread {

    private DaemonThread() {
    }

//    @Override
//    public void run() {
//        PrintUtils.synTco("--daemon线程开始.");
//        for (int i = 1; ; i++) {
//            PrintUtils.synTco("守护线程--轮次：" + i);
//            PrintUtils.synTco("守护线程--守护状态为:" + isDaemon());// 线程睡眠一会，500毫秒
//            SleepUtils.sleep(500);
//        }

    /**
     * 守护线程创建的线程也是守护线程
     */
    @Override
    public void run() {
        PrintUtils.synTco("--daemon线程开始.");
        for (int i = 0; i < 4; i++) {
            Thread thread = new MyThread("线程-" + i);
            //守护线程创建的线程也是守护线程，除非手动设置守护状态为false
//            thread.setDaemon(false);
            thread.start();
            PrintUtils.synTco(thread.getName() + "--守护状态为:" + thread.isDaemon());// 线程睡眠一会，500毫秒
            SleepUtils.sleep(500);
        }
    }

    public static DaemonThread getDaemonThread() {
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        return daemonThread;
    }
}
