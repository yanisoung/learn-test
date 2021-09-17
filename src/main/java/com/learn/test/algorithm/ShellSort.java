package com.learn.test.algorithm;

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
		//计算每次递进的间隔
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

	@Override
	void descSort (int[] arr) {
		if (null == arr || arr.length < 2) {
			return;
		}
		//计算每次递进的间隔
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
//		int[] arr = {7, 1, 5, 2, 6, 4, 3, 8};
//		ShellSort shellSort = new ShellSort();
//		shellSort.ascSort(arr);
//		print(arr);
	}

}
