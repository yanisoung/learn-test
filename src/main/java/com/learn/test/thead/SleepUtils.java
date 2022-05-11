package com.learn.test.thead;

/**
 * @author Bai
 * @date 2022/5/11 22:10
 */
public class SleepUtils {

	public static void sleep (long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {

		}
	}
}
