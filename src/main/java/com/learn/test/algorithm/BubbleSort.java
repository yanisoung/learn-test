package com.learn.test.algorithm;

import java.util.Arrays;

/**
 * 冒泡算法
 * 比较数组中相邻两个值的大小，并交换位置，将最小或最大的数分别放置在数组的首位或者末尾
 *
 * @author Bai
 * @date 2021/8/22 10:49
 */
public class BubbleSort extends BaseSort {

	/**
	 * 冒泡算法：升序排序
	 *
	 * @param arr
	 */
	@Override
	void ascSort (int[] arr) {
		//边界
		if (null == arr || arr.length < 2) {
			return;
		}
		//倒循环：缩小每次循环的范围，去除已经排序完成的位置
		for (int e = arr.length; e > 0; e--) {
			//正循环: 主要是用于排序
			for (int i = 0; i < e - 1; i++) {
				//相邻两个值进行比较 ，如果i位置的值大于i+1位置的值，则进行交换
				if (arr[i] > arr[i + 1]) {
					swap(arr, i, i + 1);
				}
			}
		}
	}

	/**
	 * 冒泡算法：算法最优写法
	 * 算法最好情况下时间复杂度是O(n),也就是数组本身已经是有序的状态下，只需要对数组进行一次循环，也就是O(n)
	 *
	 * @param arr
	 */
	public static void bestSort (int[] arr) {
		//边界
		if (null == arr || arr.length < 2) {
			return;
		}
		//倒循环：缩小每次循环的范围，去除已经排序完成的位置
		for (int e = arr.length; e > 0; e--) {
			//初始假设这个数组是有序
			boolean orderly = true;
			//正循环: 主要是用于排序
			for (int i = 0; i < e - 1; i++) {
				//相邻两个值进行比较 ，如果i位置的值大于i+1位置的值，则进行交换
				if (arr[i] > arr[i + 1]) {
					swap(arr, i, i + 1);
					orderly = false;
				}
			}
			//循环一次后，如果数组数组是有序的，则无须再排序
			if (orderly) {
				break;
			}
		}
	}

	/**
	 * 冒泡算法：正序排序
	 *
	 * @param arr
	 */
	@Override
	void descSort (int[] arr) {
		//边界
		if (null == arr || arr.length < 2) {
			return;
		}
		//倒循环：缩小每次循环的范围，去除已经排序完成的位置
		for (int e = arr.length; e > 0; e--) {
			//正循环: 主要是用于排序
			for (int i = 0; i < e - 1; i++) {
				//相邻两个值进行比较 ，如果i位置的值大于i+1位置的值，则进行交换
				if (arr[i] < arr[i + 1]) {
					swap(arr, i, i + 1);
				}
			}
		}
	}

	public static void main (String[] args) {
		run(new BubbleSort());
		//最好情况下排序，时间复杂度是O(n)
		int[] best = getArr();
		Arrays.sort(best);
		bestSort(best);
	}
}
