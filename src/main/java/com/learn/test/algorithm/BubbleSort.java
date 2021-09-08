package com.learn.test.algorithm;

import com.alibaba.fastjson.JSONObject;

/**
 * 冒泡算法
 * 比较数组中相邻两个值的大小，并交换位置，将最小或最大的数分别放置在数组的首位或者末尾
 *
 * @author Bai
 * @date 2021/8/22 10:49
 */
public class BubbleSort {

	/**
	 * 冒泡算法：升序排序
	 *
	 * @param arr
	 */
	public static void ascSort (int[] arr) {
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
	 * 冒泡算法：正序排序
	 *
	 * @param arr
	 */
	public static void descSort (int[] arr) {
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

	/**
	 * 该方法只使用 两个变量不是同一内存，否则会出现问题
	 *
	 * @param arr
	 * @param i
	 * @param j
	 */
	private static void swap (int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}

	public static void main (String[] args) {
		int[] arr = {6, 3, 5, 2, 4, 1};
		int[] arr1 = {6, 3, 5, 2, 4, 1};
		ascSort(arr);
		descSort(arr1);
		System.out.println(JSONObject.toJSONString(arr));
		System.out.println(JSONObject.toJSONString(arr1));
	}
}
