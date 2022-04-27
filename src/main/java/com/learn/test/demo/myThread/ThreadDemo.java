package com.learn.test.demo.myThread;

import com.learn.test.PrintUtils;
import com.learn.test.demo.myThread.runnable.AnonymousCreateDemo;
import com.learn.test.demo.myThread.runnable.GoodsStockDemo;
import com.learn.test.demo.myThread.runnable.LambdaCreateThreadDemo;
import com.learn.test.demo.myThread.runnable.MyRunnable;
import com.learn.test.demo.myThread.thread.MyThread;

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
		//lambda表达式创建线程
		LambdaCreateThreadDemo.create();
		//使用匿名类创建线程
		AnonymousCreateDemo.create();
		//实现Runnable模拟库存减少
		goodsStockTest();
	}

	/**
	 * 模拟库存减少
	 */
	private static void goodsStockTest () {
		//库存数量：被所有销售员共享
		GoodsStockDemo goodsStockDemo = new GoodsStockDemo();
		//模拟5个销售员
		for (int i = 0; i < 5; i++) {
			//每次开启一个新线程模拟一个销售员，共享一个实例goodsStockDemo，保证库存数量被所有销售员共享
			Thread thread = new Thread(goodsStockDemo, "销售员-" + i);
			thread.start();
		}
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