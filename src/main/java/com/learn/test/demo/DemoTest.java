package com.learn.test.demo;

/**
 * @author Bai
 * @date 2021/6/27 16:24
 */
public class DemoTest {

	public static void main (String[] args) {
		demo1();
	}

	/**
	 * 一个小球从300米空中落下，每次反弹3/4的高度，当弹起的高度小于0.0001米时，可以认为小球达到静止状态。
	 * 编写逻辑，输出当小球达到静止状态时的弹跳次数，以及累积弹跳的总高度是多少？
	 *
	 * 注意：这里使用的都是double类型，如果使用其他数据类型可能会导致结果不一致，是因为数据类型的精度越高，弹跳的次数也就越多，高度就会越精准
	 */
	private static void demo1 () {
		//最高点
		double high = 300.0;
		//最低点
		double low = 0.0001;
		//每次的弹跳比例
		double proportion = 3.0 / 4.0;
		//每次弹跳的高度：初始化为最低点的原始值
		double tmpHigh = low / proportion;
		//弹跳总次数
		int count = 0;
		//弹跳累积高度
		double jumpHigh = 0.0;

		while (tmpHigh < high) {
			tmpHigh = tmpHigh / proportion;
			count++;
			jumpHigh += tmpHigh;
		}
		System.out.println("弹跳总次数：" + count);
		System.out.println("弹跳累积高度：" + jumpHigh);
	}
}
