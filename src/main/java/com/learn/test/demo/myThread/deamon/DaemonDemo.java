package com.learn.test.demo.myThread.deamon;

import com.learn.test.PrintUtils;
import com.learn.test.thead.SleepUtils;

/**
 * 守护线程
 * 守护线程和用户线程的区别：终止时机不同。用户线程可以自主执行结束，当用户线程全部结束后，JVM进程也会终止，而守护线程是跟随JVM的运行。而当用户线程全部不存在，只存在守护线程时，JVM就会终止所有守护线程。
 * 守护线程创建的线程也是守护线程
 */
public class DaemonDemo {
    //每一轮的睡眠时长
    public static final int SLEEP_GAP = 500;
    //用户线程执行轮次
    public static final int MAX_TURN = 4;


    public static void main(String args[]) {
        Thread daemonThread = DaemonThread.getDaemonThread();
        daemonThread.start();//创建一条用户线程，执行4轮
        Thread userThread = new Thread(() -> {
            PrintUtils.synTco(">>用户线程开始.");
            for (int i = 1; i <= MAX_TURN; i++) {
                PrintUtils.synTco("用户线程>>轮次：" + i);
                PrintUtils.synTco("用户线程>>守护状态为:" + Thread.currentThread().isDaemon());
                SleepUtils.sleep(SLEEP_GAP);
            }
        });
        userThread.start();
        SleepUtils.sleep(10000);
    }
}