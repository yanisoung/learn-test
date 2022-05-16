package com.learn.test.thead;

import com.learn.test.PrintUtils;

/**
 * @author Bai
 * @date 2022/5/11 22:10
 */
public class SleepUtils {

	public static void sleep (long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			PrintUtils.print(Thread.currentThread().getName() + "抛出了InterruptedException");
		}
	}
}
