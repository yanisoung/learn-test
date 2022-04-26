package com.learn.test.demo.myThread;

import com.learn.test.PrintUtils;

/**
 * @author Bai
 * @date 2022/4/26 21:16
 */
public class ThreadDemo {

	public static void main (String[] args) {
//		emptyThread();
		createTread();
	}

	/**
	 * 创建线程的4种方式：
	 * 1.继承Thread类
	 * 2.实现Runnable类
	 * 优雅实现Runnable类的两种方式：1.使用匿名内部类 2.使用lamb表达式
	 * 3.使用线程池
	 * 4.使用线程工具类
	 */
	public static void createTread () {
		createByThread();
		createByRunnable();
		anonymousCreateByRunnable();
		lambdaCreateByRunnable();
	}

	/***
	 * 1.继承Thread类创建线程
	 */
	public static void createByThread () {
		for (int i = 0; i < 3; i++) {
			MyThread myThread = new MyThread("MyThread-" + i);
			myThread.start();
		}
	}

	/***
	 * 2.实现Runnable类创建线程
	 */
	public static void createByRunnable () {
		for (int i = 0; i < 3; i++) {
			MyRunnable myThread = new MyRunnable();
			Thread thread = new Thread(myThread, "MyRunnable-" + i);
			thread.start();
		}
	}

	/**
	 * 2.1匿名内部类创建线程
	 */
	public static void anonymousCreateByRunnable () {
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
	/**
	 * 2.2 Lambda表达式创建线程
	 */
	public static void lambdaCreateByRunnable () {
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

	/**
	 * 空线程：run方法并未完全执行
	 */
	public static void emptyThread () {
		Thread thread = new Thread();
		PrintUtils.print("线程名：" + thread.getName());
		PrintUtils.print("线程id：" + thread.getId());
		PrintUtils.print("线程状态：" + thread.getState());
		PrintUtils.print("线程优先级：" + thread.getPriority());
		thread.start();
		//这里的run并没有运行,因为target是null
//		@Override
//		public void run() {
//			if (target != null) {
//				target.run();
//			}
//		}
		//init方法第二个入参就是target，可以看出来这里是给的null
//		 public Thread() {
//			init(null, null, "Thread-" + nextThreadNum(), 0);
//		}
	}
}
