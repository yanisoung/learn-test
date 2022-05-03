package com.learn.test.demo.myThread.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用线程池创建线程
 *
 * @author Bai
 * @date 2022/5/3 13:50
 */
public class ThreadExecutors {

	/**
	 * 创建一个固定线程数的线程池
	 */
	public static ExecutorService pool = Executors.newFixedThreadPool(3);

	public static ExecutorService getPool () {
		return pool;
	}
}
