package com.learn.test.demo.myThread.runnable;

/**
 * 使用Runnable优雅创建线程的方式2：
 * 使用lambda实现Runnable接口
 *
 * @author Bai
 * @date 2022/4/27 21:56
 */
public class LambdaCreateThreadDemo {

	/**
	 * 2.2 Lambda表达式实现Runnable创建线程
	 */
	public static void create () {
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(() -> {
				for (int i1 = 0; i1 < 10; i1++) {
					System.out.println("线程名：" + Thread.currentThread().getName() + "，运行了" + i1);
				}
				System.out.println("线程名：" + Thread.currentThread().getName() + "，运行结束");
			}, "LambdaRunnable-" + i);
			thread.start();
		}
	}
}
