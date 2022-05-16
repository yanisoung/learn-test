package com.learn.test.demo.myThread.status;

import java.util.List;

import com.learn.test.PrintUtils;
import com.learn.test.demo.list.MyList;
import com.learn.test.thead.SleepUtils;

/**
 * 线程状态测试
 *
 * @author Bai
 * @date 2022/5/11 21:59
 */
public class ThreadStatusDemo {

	private static int MAX = 30;
	private static List<Thread> THREAD_LIST = new MyList<>();

	public static void main (String[] args) {
		THREAD_LIST.add(Thread.currentThread());
		ThreadStatus threadStatus1 = new ThreadStatus("threadStatus1");
		ThreadStatus threadStatus2 = new ThreadStatus("threadStatus2");
		ThreadStatus threadStatus3 = new ThreadStatus("threadStatus3");

		THREAD_LIST.add(threadStatus1);
		THREAD_LIST.add(threadStatus2);
		THREAD_LIST.add(threadStatus3);

		threadStatus1.start();
		SleepUtils.sleep(200);
		threadStatus2.start();
		SleepUtils.sleep(200);
		threadStatus3.start();
		SleepUtils.sleep(200);

		SleepUtils.sleep(200000);
	}

	public static class ThreadStatus extends Thread {
		public ThreadStatus (String name) {
			super(name);
		}

		@Override
		public void run () {
			PrintUtils.print(getName() + "-" + getState());
			for (int i = 0; i < MAX; i++) {
				SleepUtils.sleep(20000);
				printAllStatus();
			}
			PrintUtils.print(getName() + "-" + "执行结束了");
		}
	}

	public static void printAllStatus () {
		for (int i = 0; i < THREAD_LIST.size(); i++) {
			Thread thread = THREAD_LIST.get(i);
			PrintUtils.print(thread.getName() + "-" + thread.getState());
		}
	}
}
