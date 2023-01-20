package com.learn.test.demo.myThread.wait;

import com.learn.test.PrintUtils;
import com.learn.test.thead.SleepUtils;

public class WaitNotifyDemo {

    static Object lock = new Object();

    static class WaitDemo implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                PrintUtils.toStr("等待进来了");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                PrintUtils.toStr("等待结束了");

            }
        }
    }

    static class NotifyDemo implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                PrintUtils.toStr("唤醒进来了");
                try {
                    lock.notifyAll();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                PrintUtils.toStr("唤醒结束了");

            }
        }
    }

    public static void main(String[] args) {
        WaitDemo waitDemo = new WaitDemo();
        NotifyDemo notifyDemo = new NotifyDemo();

        Thread notify = new Thread(notifyDemo);
        Thread wait = new Thread(waitDemo);


        wait.start();
        SleepUtils.sleep(1000);
        notify.start();

    }
}
