package com.learn.test.demo.myThread.method;

import com.learn.test.PrintUtils;
import com.learn.test.thead.SleepUtils;

/**
 * join方法：合并线程
 * 在线程A中去调用线程B的join方法，那么线程A就是主动合并线程，线程B就是被动合并线程。
 * join有3种重载方式
 *
 * @author Bai
 * @date 2022/5/23 22:38
 */
public class JoinDemo {

	static class SleepThread extends Thread {
		public SleepThread (String name) {
			super(name);
		}

		@Override
		public void run () {
			PrintUtils.print(getName() + "开始执行了");
			SleepUtils.sleep(2000L);
			PrintUtils.print(getName() + "执行结束了");
		}
	}

	public static void main (String[] args) {
		PrintUtils.print("main-执行了");
		SleepThread sleepThread1 = new SleepThread("sleepThread1");
		try {
			sleepThread1.start();
			sleepThread1.join();
		} catch (InterruptedException e) {
		}
		SleepThread sleepThread2 = new SleepThread("sleepThread2");
		try {
			sleepThread2.start();
			sleepThread2.join(1000); //等待执行1000ms后继续执行main线程
		} catch (InterruptedException e) {
		}
		SleepThread sleepThread3 = new SleepThread("sleepThread3");
		try {
			sleepThread3.start();
			sleepThread3.join(1000, 1000);//等待执行1000ms+1000nanos后继续执行main线程
		} catch (InterruptedException e) {
		}
		PrintUtils.print("main-执行结束了");
	}
}
