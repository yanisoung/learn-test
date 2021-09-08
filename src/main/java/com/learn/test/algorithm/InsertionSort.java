package com.learn.test.algorithm;

import com.alibaba.fastjson.JSONObject;

/**
 * 插入算法
 * 比较像整理纸牌，是对未排序的数据，在有序队列中从后向前扫描找到合适位置插入。
 *
 * @author Bai
 * @date 2021/8/22 13:53
 */
public class InsertionSort {

	/**
	 * 升序排序
	 *
	 * @param arr
	 */
	public static void ascSort (int[] arr) {
		if (null == arr || arr.length < 2) {
			return;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
				swap(arr, j, j + 1);
			}
		}
	}

	public static void descSort (int[] arr) {
		if (null == arr || arr.length < 2) {
			return;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0 && arr[j] < arr[j + 1]; j--) {
				swap(arr, j, j + 1);
			}
		}
	}

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
