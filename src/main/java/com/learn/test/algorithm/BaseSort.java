package com.learn.test.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Bai
 * @date 2021/9/11 14:17
 */
public abstract class BaseSort {
	private Integer forCnt = 100000;

	public BaseSort () {
	}

	public BaseSort (Integer forCnt) {
		this.forCnt = forCnt;
	}

	/**
	 * 升序排序
	 *
	 * @param arr
	 */
	abstract void ascSort (int[] arr);

	/**
	 * 降序排序
	 *
	 * @param arr
	 */
	abstract void descSort (int[] arr);

	public void ascForCheck () {
		for (int i = 0; i < forCnt; i++) {
			//升序
			int[] arr = getArr();
			int[] arr1 = arr.clone();
			ascSort(arr);
			ascCheck(arr, arr1);
		}
	}

	public void descForCheck () {
		for (int i = 0; i < forCnt; i++) {
			//升序
			int[] arr = getArr();
			int[] arr1 = arr.clone();
			descSort(arr);
			descCheck(arr, arr1);
		}
	}

	public static void run (BaseSort baseSort) {
		baseSort.ascForCheck();
		baseSort.descForCheck();
	}

	/**
	 * 该方法只使用 两个变量不是同一内存，否则会出现问题
	 *
	 * @param arr
	 * @param i
	 * @param j
	 */
	public static void swap (int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static int[] getArr () {
		Random random = new Random();
		int[] arr = new int[random.nextInt(1000)];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(10000);
		}
		return arr;
	}

	public static void ascCheck (int[] arr, int[] arr1) {
		Arrays.sort(arr1);
		check(arr, arr1);
	}

	public static void descCheck (int[] arr, int[] arr1) {
		Integer[] trans = trans(arr1);
		Arrays.sort(trans, Collections.reverseOrder());
		check(arr, trans(trans));
	}

	public static void check (int[] arr, int[] arr1) {
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

	public static Integer[] trans (int[] arr1) {
		Integer[] integers = new Integer[arr1.length];
		for (int i = 0; i < arr1.length; i++) {
			integers[i] = arr1[i];
		}
		return integers;
	}

	public static int[] trans (Integer[] arr1) {
		int[] integers = new int[arr1.length];
		for (int i = 0; i < arr1.length; i++) {
			integers[i] = arr1[i];
		}
		return integers;
	}

	public static void print (Object o) {
		System.out.println(JSONObject.toJSONString(o));
	}
}
