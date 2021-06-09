package com.learn.test.thead;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Bai
 * @date 2021/6/9 21:31
 */
public class ThreadPoolExecutorUtil {
	/**
	 * 设置核心池大小
	 */
	private static int corePoolSize = 5;
	/**
	 * 设置线程池最大能接受多少线程
	 */
	private static int maximumPoolSize = Integer.MAX_VALUE;
	/**
	 * 当前线程数大于corePoolSize、小于maximumPoolSize时，超出corePoolSize的线程数的生命周期
	 */
	private static long keepAliveTime = 100;
	/**
	 * 设置时间单位是秒
	 */
	private static TimeUnit unit = TimeUnit.SECONDS;
	/**
	 * 设置线程池缓存队列的排队策略为FIFO，并且指定缓存队列大小为5
	 */
	private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(5);
	/**
	 * 设置线程工厂
	 */
	private static ThreadFactory threadFactory = Executors.defaultThreadFactory();
	// 设置线程饱和策略
	private static RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

	//创建ThreadPoolExecutor线程池对象，并初始化该对象的各种参数
	private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
		keepAliveTime, unit, workQueue, handler);

	public static ThreadPoolExecutor getThreadPoolExecutor () {
		return threadPoolExecutor;
	}
}
