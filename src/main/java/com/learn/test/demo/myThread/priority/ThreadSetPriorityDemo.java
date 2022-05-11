package com.learn.test.demo.myThread.priority;

/**
 * 线程优先级
 *
 * @author Bai
 * @date 2022/5/3 16:00
 */
public class ThreadSetPriorityDemo {
	public static class ThreadSetPriority extends Thread {
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

	/**
	 * 线程优先级
	 * 某次的执行结果：
	 * 优先级1,执行次数：2048
	 * 优先级2,执行次数：0
	 * 优先级3,执行次数：0
	 * 优先级4,执行次数：18141291
	 * 优先级5,执行次数：740775471
	 * 优先级6,执行次数：739640429
	 * 优先级7,执行次数：1647130431
	 * 优先级8,执行次数：1634365376
	 * 优先级9,执行次数：613232553
	 * 优先级10,执行次数：611550245
	 */
	public static void threadSetPriority () {
		ThreadSetPriority[] threadSetPriorities = new ThreadSetPriority[10];
		//初始化
		for (int i = 0; i < threadSetPriorities.length; i++) {
			ThreadSetPriority threadSetPriority = new ThreadSetPriority();
			threadSetPriority.setPriority(i + 1);
			threadSetPriorities[i] = threadSetPriority;
		}
		//启动线程设置优先级
		for (int i = 0; i < threadSetPriorities.length; i++) {
			threadSetPriorities[i].start();
		}
		//停止线程(生产环境不会用stop接口)
		for (int i = 0; i < threadSetPriorities.length; i++) {
			threadSetPriorities[i].stop();
		}
		//打印线程优先级与执行结果
		for (int i = 0; i < threadSetPriorities.length; i++) {
			ThreadSetPriority threadSetPriority = threadSetPriorities[i];
			System.out.println("优先级" + threadSetPriority.getPriority() + ",执行次数：" + threadSetPriority.numb);
		}
	}
}
