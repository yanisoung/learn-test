package com.learn.test.algorithm;

/**
 * 二分查找法
 * 在有序的数组中，从中间位置查找某个位置，如果要找的数值就是中间位置的，那么顺利结束。
 * 如果不是，则继续判断要找的数比中间位置的数大或小，
 * 如果比中间的数值大 则继续查找中间位置到末尾这段范围内的数据，并继续找到该段范围内的中间数，继续比较大小
 *
 * @author Bai
 * @date 2021/8/23 22:43
 */
public class BinarySearch {

	/**
	 * 在一个有序数组中，找某个数是否存在
	 *
	 * @return
	 */
	public static boolean exit (int[] arr, int numb) {
		if (null == arr || arr.length == 0) {
			return false;
		}
		int start = 0;
		int end = arr.length - 1;
		int mid = (arr.length - 1) >> 1;
		if (arr[mid] == numb) {
			return true;
		}
		while (arr[mid] > numb && mid >= start & mid <= end) {
			if (arr[mid] == numb) {
				return true;
			}
			mid = mid >> 1;
		}
		if (arr[mid] == numb) {
			return true;
		}
		while (arr[mid] < numb && mid >= start & mid <= end) {
			if (arr[mid] == numb) {
				return true;
			}
			mid = mid + ((end - mid) >> 1);
		}
		if (arr[mid] == numb) {
			return true;
		}
		if (mid <= 0) {
			return arr[0] == numb;
		}
		if (mid >= end) {
			return arr[end] == numb;
		}
		return false;
	}

	public static void main (String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11};
		System.out.println(exit(arr, 3));
		System.out.println(exit(arr, 5));
		System.out.println(exit(arr, 2));
		System.out.println(exit(arr, 7));
	}
}
