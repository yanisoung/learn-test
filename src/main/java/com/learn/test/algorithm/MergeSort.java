package com.learn.test.algorithm;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 归并算法：主要用于将两组已排序好的数组，合并一起并排序
 * 思想：新建一个新的数组来存放合并后的新数据，
 * 首先取A/B两个数组的首字母进行比较，如果A[0]<B[0]，将A[0]位置的数存入新数组中的0位置
 * 接着取A[1]位置的数据与B[0]位置的数进行比较，如果B[0]<A[1]位置的数据，则将B[0]位置上的数放入新数组的1位置
 * 接着继续比较A[1]与B[1]，将小的一方继续放入新数组中
 * 重复上述过程
 * 注意：不论两个数组的长度是否相同时，最后一次比较时总是会有一个数组的最后一位或是超出另外一个数组的部分剩下来，最后都要重新检验漏下的数据
 *
 * @author Bai
 * @date 2021/9/17 22:54
 */
public class MergeSort extends BaseSort {

	public static int[] mergeSort (int[] left, int[] right) {
		if (null == left) {
			return right;
		}
		if (null == right) {
			return left;
		}
		//两个数组合并后的新数组
		int[] newArr = new int[left.length + right.length];
		//新数组的下标
		int k = 0;
		//第一个数组的下标
		int l = 0;
		//第二个数组的下标
		int r = 0;
		while (l < left.length && r < right.length) {
			//取arr数组的第一位与arr2进行比较，如果arr[i]<arr2[j]则将arr[i]的数据放到newArr对应的下标上，继续循环
			//v1
//			if (left[l] < right[r]) {
//				newArr[k] = left[l];
//				k++;
//				l++;
//			} else {
//				newArr[k] = right[r];
//				k++;
//				r++;
//			}
			//v2
			newArr[k++] = left[l] < right[r] ? left[l++] : right[r++];
		}
		//防止数组中有漏掉未放入新数组的数据
		//l or r 未达到数组的长度时，是因为有部分数组未被排序
		//因为是两两比较，所以最后只会有同一个数组中的数被剩下，要么是left要么是right
		while (l < left.length) {
			newArr[k++] = left[l++];
		}
		while (r < right.length) {
			newArr[k++] = right[r++];
		}
		return newArr;
	}

	public static void main (String[] args) {
		for (int i = 0; i < 100000; i++) {
			int[] left = getArr();
			Arrays.sort(left);
			int[] right = getArr();
			Arrays.sort(right);
			int[] mergeArr = ArrayUtils.addAll(left, right);
			Arrays.sort(mergeArr);
			int[] mergeSort = mergeSort(left, right);
			check(mergeSort, mergeArr);
		}
	}

	@Override
	void ascSort (int[] arr) {
		Arrays.sort(arr);
	}

	@Override
	void descSort (int[] arr) {

	}
}
