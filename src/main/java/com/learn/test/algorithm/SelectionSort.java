package com.learn.test.algorithm;

import java.util.Arrays;

/**
 * 选择排序
 * 每次寻找未排序的数字中最小或最大的数值，放到未排序的数值首位或是末尾
 *
 * @author Bai
 * @date 2021/8/22 13:20
 */
public class SelectionSort extends BaseSort {

	/**
	 * 升序
	 */
	@Override
	void ascSort (int[] arr) {
		if (null == arr || arr.length < 2) {
			return;
		}
		for (int i = 0; i < arr.length - 1; i++) {
			//每次循环之后最小值
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				minIndex = arr[j] < arr[minIndex] ? j : minIndex;
			}
			swap(arr, i, minIndex);
		}
	}

	/**
	 * 降序
	 */
	@Override
	void descSort (int[] arr) {
		if (null == arr || arr.length < 2) {
			return;
		}
		for (int i = 0; i < arr.length - 1; i++) {
			//每次循环之后最大值
			int maxIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				maxIndex = arr[j] > arr[maxIndex] ? j : maxIndex;
			}
			swap(arr, i, maxIndex);
		}
	}

	/**
	 * 升序
	 */
	public static void ascSortV2 (int[] arr) {
		if (null == arr || arr.length < 2) {
			return;
		}
		int end = arr.length - 1;
		for (int i = 0; i < end; i++) {
			//每次循环之后最小值
			int minIndex = i;
			int minMax = i;
			for (int j = i + 1; j < end + 1; j++) {
				minIndex = arr[j] < arr[minIndex] ? j : minIndex;
				minMax = arr[j] > arr[minMax] ? j : minMax;
			}
			if (i == minMax && end == minIndex) {
				swap(arr, minIndex, minMax);
			} else if (i == minMax) {
				swap(arr, end, minMax);
				if (i != minIndex) {
					swap(arr, i, minIndex);
				}
			} else if (minIndex != minMax) {
				if (i != minIndex) {
					swap(arr, i, minIndex);
				}
				if (end != minMax) {
					swap(arr, end, minMax);
				}
			}
			end--;
		}
	}

	public static void main (String[] args) {
		run(new SelectionSort());
		//同时找出 最大 最小 进行选择
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
