package com.learn.test.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import com.alibaba.fastjson.JSONObject;

/**
 * 选择排序
 * 每次寻找未排序的数字中最小或最大的数值，放到未排序的数值首位或是末尾
 *
 * @author Bai
 * @date 2021/8/22 13:20
 */
public class SelectionSort {

	/**
	 * 升序
	 */
	public static void ascSortV1 (int[] arr) {
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
	public static void descSortV1 (int[] arr) {
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
		v1();
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

	private static void v1 () {
		for (int i = 0; i < 100000; i++) {
			//升序
			int[] arr = getArr();
			int[] arr1 = arr.clone();
			ascSortV1(arr);
			Arrays.sort(arr1);
			check(arr, arr1);
			//降序
			int[] arr2 = getArr();
			int[] arr3 = arr2.clone();
			descSortV1(arr2);
			Integer[] trans = trans(arr3);
			Arrays.sort(trans, Collections.reverseOrder());
			check(arr2, trans(trans));
		}
	}

	private static void swap (int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	private static int[] getArr () {
		Random random = new Random();
		int[] arr = new int[random.nextInt(1000)];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(10000);
		}
		return arr;
	}

	private static void check (int[] arr, int[] arr1) {

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != arr1[i]) {
				System.out.println("↓----排序失败----↓");
				System.out.println(JSONObject.toJSONString(arr));
				System.out.println(JSONObject.toJSONString(arr1));
				System.out.println("↑----排序失败----↑");
				break;
			}
		}
	}

	private static Integer[] trans (int[] arr1) {
		Integer[] integers = new Integer[arr1.length];
		for (int i = 0; i < arr1.length; i++) {
			integers[i] = arr1[i];
		}
		return integers;
	}

	private static int[] trans (Integer[] arr1) {
		int[] integers = new int[arr1.length];
		for (int i = 0; i < arr1.length; i++) {
			integers[i] = arr1[i];
		}
		return integers;
	}
}
