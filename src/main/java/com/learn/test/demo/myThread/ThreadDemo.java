package com.learn.test.demo.myThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.learn.test.PrintUtils;
import com.learn.test.demo.myThread.executors.ThreadExecutors;
import com.learn.test.demo.myThread.futrue.MyReturnableTask;
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

	public static ExecutorService pool = ThreadExecutors.getPool();

	public static void main (String[] args) {
//		emptyThread();
		createTread();
	}

	/**
	 * 创建线程的4种方式：
	 * 1.继承Thread类
	 * 2.实现Runnable类
	 * 优雅实现Runnable类的两种方式：1.使用匿名内部类 2.使用lamb表达式
	 * 3.使用Callable和FutureTask类创建线程池
	 * 4.使用线程池
	 */
	public static void createTread () {
//		1.继承Thread类
		createByThread();
//		2.实现Runnable类
		createByRunnable();
		//lambda表达式创建线程
		LambdaCreateThreadDemo.create();
		//使用匿名类创建线程
		AnonymousCreateDemo.create();
		//实现Runnable模拟库存减少
		goodsStockTest();
//		3.使用Runnable和Future创建带有异步返回值的线程类
		callable();
		//4.线程池创建线程
		executors();
	}

	public static void executors () {
		//无返回值
		pool.execute(new MyThread("Pool-execute-MyThread"));
		//带返回值
		Future<Integer> submit = pool.submit(new MyReturnableTask());
		try {
			Integer integer = submit.get();
			System.out.println(integer);
		} catch (Exception e) {

		}
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

	private static void callable () {
		//FutureTask是RunnableFuture的默认实现类，RunnableFuture实现了Thread类和Future类同时具有了异步执行和获取异步执行结果的能力
		//因为实现了Runnable类，所以可以作为Thread类的target属性类执行
		FutureTask futureTask = new FutureTask(new MyReturnableTask());
		Thread thread = new Thread(futureTask, "callable");
		thread.start();
		try {
			Object o = futureTask.get();
			System.out.println(o);
		} catch (Exception e) {

		}
		System.out.println("callable执行结束了");
	}
}
