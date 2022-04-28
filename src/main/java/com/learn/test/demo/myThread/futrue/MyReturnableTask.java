package com.learn.test.demo.myThread.futrue;

import java.util.concurrent.Callable;

/**
 * Callable实现类：
 * 实现带有返回值的线程类
 *
 * @author Bai
 * @date 2022/4/28 22:25
 */
public class MyReturnableTask implements Callable<Integer> {

	@Override
	public Integer call () throws Exception {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + ":" + i);
		}
		return 100;
	}
}
