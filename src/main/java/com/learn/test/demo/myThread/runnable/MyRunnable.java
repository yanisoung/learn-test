package com.learn.test.demo.myThread.runnable;

/**
 * 2.实现Runnable类创建线程
 *
 * @author Bai
 * @date 2022/4/26 21:35
 */
public class MyRunnable implements Runnable {

	@Override
	public void run () {
		for (int i = 0; i < 10; i++) {
			//这里只能使用Thread的内部类查询线程名
			System.out.println("线程名：" + Thread.currentThread().getName() + "，运行了" + i);
		}
		System.out.println("线程名：" + Thread.currentThread().getName() + "，运行结束");
	}
}
