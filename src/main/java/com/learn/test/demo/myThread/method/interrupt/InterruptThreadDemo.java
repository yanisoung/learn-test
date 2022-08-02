package com.learn.test.demo.myThread.method.interrupt;

import com.learn.test.PrintUtils;
import com.learn.test.thead.SleepUtils;

/**
 * interrupt()方法测试
 *
 * @author Bai
 * @date 2022/5/16 21:23
 */
public class InterruptThreadDemo {

	public static void main (String[] args) {
		SleepThread sleepThread = new SleepThread("SleepThread-1");
		SleepThread sleepThread2 = new SleepThread("SleepThread-2");
		sleepThread.start();
		sleepThread2.start();

		sleepThread.interrupt();//抛出中断异常
		SleepUtils.sleep(5000);//此时Thread-2已经运行结束了
		sleepThread2.interrupt();
		SleepUtils.sleep(1000);//等待Thread-2的执行结果
		System.out.println("结束了");
	}

	static class SleepThread extends Thread {

		public SleepThread (String name) {
			super(name);
		}

		@Override
		public void run () {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				PrintUtils.print(Thread.currentThread().getName() + "抛出了InterruptedException");
			}
			PrintUtils.print(Thread.currentThread().getName() + "运行结束了");
		}
	}
}


