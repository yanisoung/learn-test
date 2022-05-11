package com.learn.test.demo.myThread.priority;

/**
 * 线程优先级
 *
 * @author Bai
 * @date 2022/5/3 16:00
 */
public class ThreadSetPriority extends Thread {

	public ThreadSetPriority () {
		super("ThreadSetPriority");
	}

	public long numb = 0;

	@Override
	public void run () {
		for (int i = 0; ; i++) {
			numb++;
		}
	}
}
