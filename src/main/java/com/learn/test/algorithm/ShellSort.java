package com.learn.test.algorithm;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * @author Bai
 * @date 2021/9/16 22:24
 */
public class ShellSort extends BaseSort {

	@Override
	void ascSort (int[] arr) {
		if (null == arr || arr.length < 2) {
			return;
		}
		//计算每次递进的间隔，控制一共几次
		//例如数组 int[] arr = {7, 1, 5, 2, 6, 4, 3, 8};
		//第一次循环 n = 4 ，索引4之前的0 - 3的范围正好是一个间隔范围，4-9 又是一个间隔范围，索尔排序的规则就是依次拿4-9范围内的数与0-3范围内的数进行比较
		for (int n = arr.length / 2; n >= 1; n /= 2) {
			//控制每次间隔的起始位：取第一个间隔数之后的首位，来控制每次循环的间隔范围，也就是 arr[4] - arr[9] 位置上的值
			// i = n = 4;
			for (int i = n; i < arr.length; i++) {
				//循环每次间隔范围内的数 进行比较&替换
				//j = i = 4 : 是为了取获取当前间隔范围的首位比较数值，也就是取arr[4]位置上的数字
				//j > n - 1 : 为了防止出现不满足间隔范围的 序列出现 例如 j = 4, n = 4 ,j > 3,而0-3是正好是一个间隔范围
				//j -= n : 是为了进入下一个循环的间隔范围，跳槽当前已经比较过的间隔范围
				for (int j = i; j > n - 1; j -= n) {
					//比较 arr[j] 位置上的数是否大于arr[j-n]位置上的数，也就是 arr[4] > arr[0] , 6 > 7 ? 如果不是，则交换4 跟 0 下标上的数
					if (arr[j] < arr[j - n]) {
						swap(arr, j, j - n);
					}
				}
			}
		}
	}

	/**
	 * 使用knuth 间隔排序 3h+1
	 * 注意：无论是什么间隔序列，最后必须满足一个条件，就是逐渐减小的间隔最后一定要等于1，因此最后一趟排序一定是简单的插入排序
	 *
	 * @param arr
	 */
	public static void ascSortV2 (int[] arr) {
		if (null == arr || arr.length < 2) {
			return;
		}
		//初始间隔为1
		int gep = 1;
		//寻找最佳gep：公式 gep*3+10 ， gep的大小不能超过数组的三分之一
		while (gep <= arr.length / 3) {
			gep = gep * 3 + 1;
		}
		System.out.println("get：" + gep);
		//计算每次递进的间隔位置，控制一共间隔几次
		//例如数组 int[] arr = {7, 1, 5, 2, 6, 4, 3, 8};  gep = 4
		//第一次循环 n = gep = 4 ，索引4之前的0 - 3的范围正好是一个间隔范围，4-9 又是一个间隔范围，索尔排序的规则就是依次拿4-9范围内的数与0-3范围内的数进行比较
		for (int n = gep; n > 0; n = (n - 1) / 3) {
			//控制每次间隔的起始位：取第一个间隔数之后的首位，来控制每次循环的间隔范围，也就是 arr[4] - arr[9] 位置上的值
			// i = n = 4;
			for (int i = n; i < arr.length; i++) {
				//循环每次间隔范围内的数 进行比较&替换
				//j = i = 4 : 是为了取获取当前间隔范围的首位比较数值，也就是取arr[4]位置上的数字
				//j > n - 1 : 为了防止出现不满足间隔范围的 序列出现 例如 j = 4, n = 4 ,j > 3,而0-3是正好是一个间隔范围
				//j -= n : 是为了进入下一个循环的间隔范围，跳槽当前已经比较过的间隔范围
				for (int j = i; j > n - 1; j -= n) {
					if (arr[j] < arr[j - n]) {
						swap(arr, j, j - n);
					}
				}
			}
		}
	}

	@Override
	void descSort (int[] arr) {
		if (null == arr || arr.length < 2) {
			return;
		}
		//计算每次递进的间隔，控制一共几次
		//例如数组 int[] arr = {7, 1, 5, 2, 6, 4, 3, 8};
		//第一次循环 n = 4 ，索引4之前的0 - 3的范围正好是一个间隔范围，4-9 又是一个间隔范围，索尔排序的规则就是依次拿4-9范围内的数与0-3范围内的数进行比较
		for (int n = arr.length / 2; n >= 1; n /= 2) {
			//控制每次间隔的起始位：取第一个间隔数之后的首位，来控制每次循环的间隔范围，也就是 arr[4] - arr[9] 位置上的值
			// i = n = 4;
			for (int i = n; i < arr.length; i++) {
				//循环每次间隔范围内的数 进行比较&替换
				//j = i = 4 : 是为了取获取当前间隔范围的首位比较数值，也就是取arr[4]位置上的数字
				//j > n - 1 : 为了防止出现不满足间隔范围的 序列出现 例如 j = 4, n = 4 ,j > 3,而0-3是正好是一个间隔范围
				//j -= n : 是为了进入下一个循环的间隔范围，跳槽当前已经比较过的间隔范围
				for (int j = i; j > n - 1; j -= n) {
					//比较 arr[j] 位置上的数是否大于arr[j-n]位置上的数，也就是 arr[4] > arr[0] , 6 > 7 ? 如果不是，则交换4 跟 0 下标上的数
					if (arr[j] > arr[j - n]) {
						swap(arr, j, j - n);
					}
				}
			}
		}
	}

	public static void main (String[] args) {
		run(new ShellSort());
		v2();
	}

	private static void v2 () {
		for (int i = 0; i < 100000; i++) {
			//升序
			int[] arr = getArr();
			int[] arr1 = arr.clone();
			ascSortV2(arr);
			Arrays.sort(arr1);
			check(arr, arr1);
		}
	}
}
