package datastruct.sort;

import java.util.Arrays;

/**
 * 選擇排序
 * 
 * @author snack
 * 選擇排序比氣泡排序快很多
 */
public class SelectionSort {

	public static void main(String[] args) {

		// 由低到高排序
		int[] arr = { 101, 34, 119, 1 };
		selectSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void selectSort(int[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i; // 索引
			int min = arr[i]; // 假設最小進行比較

			for (int j = i + 1; j < arr.length; j++) {

				if (min > arr[j]) {
					min = arr[j]; // 重置最小的
					minIndex = j; // 索引移至最小
				}

			}

			// 將最小值,放在arr[i],交換
			if (minIndex != i) {
				arr[minIndex] = arr[i];
				arr[i] = min;
			}
		}
	
	}

}
