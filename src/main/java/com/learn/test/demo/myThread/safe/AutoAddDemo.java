package com.learn.test.demo.myThread.safe;

import java.util.concurrent.CountDownLatch;

/**
 * 自增线程安全测试
 */
public class AutoAddDemo {
    static Integer maxRunCnt = 10000000;
    static Integer maxThreadNum = 10;
    static Integer cnt = 0;
    static Integer cnt2 = 0;

    public static void autAddCnt() {
        //使用变量本身作为内置锁，则会导致结果出现异常
        synchronized (cnt) {
            cnt++;
            cnt2++;
        }
        synchronized (maxRunCnt) {
            cnt++;
            cnt2++;
        }
    }

    public static void autAddCntV2() {
        synchronized (maxRunCnt) {
            cnt++;
        }
        synchronized (maxThreadNum) {
            cnt2++;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        //等待倒数闩，当所有线程执行完后才会继续往下执行
        CountDownLatch countDownLatch = new CountDownLatch(maxThreadNum);
        Runnable runnable = (() -> {
            for (int i = 0; i < maxRunCnt; i++) {
//                cnt++;
                //使用synchronized解决线程安全问题
//                autAddCnt();
                //多变量时，使用同步代码块，解放临界区不需要同步的资源，增加临界区吞吐量
                autAddCntV2();
            }
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + "- " + countDownLatch.getCount());

        });
        for (int i = 0; i < maxThreadNum; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }
        countDownLatch.await();
        System.out.println("结束了");
        System.out.println(cnt);
    }
}
