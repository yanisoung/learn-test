package com.learn.test.demo.myThread.thread;

/**
 * 实现线程的方式1：继承Thread类
 *
 * @author Bai
 * @date 2022/4/26 21:16
 */
public class MyThread extends Thread {

	public MyThread (String name) {
		super(name);
	}

	@Override
	public void run () {
		for (int i = 0; i < 10; i++) {
			System.out.println("线程名：" + getName() + "，运行了" + i);
		}
		System.out.println("线程名：" + getName() + "，运行结束");
	}
}
