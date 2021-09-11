package com.learn.test.algorithm;

/**
 * 插入算法
 * 比较像整理纸牌，是对未排序的数据，在有序队列中从后向前扫描找到合适位置插入。
 *
 * @author Bai
 * @date 2021/8/22 13:53
 */
public class InsertionSort extends BaseSort {

	/**
	 * 升序排序
	 *
	 * @param arr
	 */
	@Override
	void ascSort (int[] arr) {
		if (null == arr || arr.length < 2) {
			return;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
				swap(arr, j, j - 1);
			}
		}
	}

	/**
	 * 算法优化
	 * 使用临时变量替代swap操作
	 *
	 * @param arr
	 */
	public static void ascSortOp (int[] arr) {
		if (null == arr || arr.length < 2) {
			return;
		}
		for (int i = 1; i < arr.length; i++) {
			//记录最左边需要交换的下标
			int lastMinIndex = i;
			for (int j = i - 1; j >= 0 && arr[i] < arr[j]; j--) {
				lastMinIndex = j;
			}
			if (lastMinIndex != i) {
				//交换i位置的数据到lastMinIndex位置，将lastMinIndex与i之间的数集体后移一位
				int tem = arr[i];
				for (int j = i; j > lastMinIndex; j--) {
					arr[j] = arr[j - 1];
				}
				arr[lastMinIndex] = tem;
			}
		}
	}

	@Override
	void descSort (int[] arr) {
		if (null == arr || arr.length < 2) {
			return;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0 && arr[j] < arr[j + 1]; j--) {
				swap(arr, j, j + 1);
			}
		}
	}

	public static void main (String[] args) {
		run(new InsertionSort());

		//优化算法check
		for (int i = 0; i < 100000; i++) {
			int[] arr = getArr();
			int[] clone = arr.clone();
			ascSortOp(arr);
			ascCheck(arr, clone);
		}
	}
}
