package datastruct.sort;

import java.util.Arrays;

/**
 * 氣泡排序
 * 
 * @author snack
 *
 */
public class Bubblesort {

	public static void main(String[] args) {

		int arr[] = { 3, 9, -1, 10, 20 }; // 只會跑三趟
		// int arr[] = {3, 2, -1, 10, -2 };//只會跑四趟

		System.out.println("排序前");
		System.out.println(Arrays.toString(arr));
		bubbleSort(arr);
		System.out.println("排序號");
		System.out.println(Arrays.toString(arr));

	}

	public static void bubbleSort(int[] arr) {
		int temp = 0; // 暫時變量
		boolean flag = false;

		for (int j = 0; j < arr.length - 1; j++) { // -2 ,-1 ,3 ,9 ,10 ,

			for (int i = 0; i < arr.length - 1 - j; i++) {
				if (arr[i] > arr[i + 1]) {
					flag = true;
					temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
				}
			}
//			System.out.printf("第%d趟排序的資料\n", j + 1);
//			System.out.println(Arrays.toString(arr));
			if (!flag) { // 在一趟排序中,一次都沒交換過
				break;
			} else {
				flag = false;
			}

		}

	}

}
