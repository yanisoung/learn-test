package com.learn.test.demo.myThread.thread;

import com.learn.test.PrintUtils;

/**
 * 实现线程的方式1：继承Thread类
 *
 * @author Bai
 * @date 2022/4/26 21:16
 */
public class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    public MyThread() {
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            PrintUtils.printPool("线程名：" + getName() + "，运行了" + i);
        }
        PrintUtils.printPool("线程名：" + getName() + "，运行结束");
    }
}
