package com.learn.test.demo.myThread.runnable;

/**
 * 使用Runnable优雅创建线程的方式1：
 * 匿名内部类实现Runnable接口
 *
 * @author Bai
 * @date 2022/4/27 21:54
 */
public class AnonymousCreateDemo {

	public static void create () {
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run () {
					for (int i = 0; i < 10; i++) {
						System.out.println("线程名：" + Thread.currentThread().getName() + "，运行了" + i);
					}
					System.out.println("线程名：" + Thread.currentThread().getName() + "，运行结束");
				}
			}, "AnonymousRunnable-" + i);
			thread.start();
		}
	}
}
