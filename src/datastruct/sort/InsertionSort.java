package datastruct.sort;

import java.util.Arrays;

public class InsertionSort {

	public static void main(String[] args) {
		int[] arr = { 101, 34, 119, 1 };

		/*
		 * 程序方面的思路~ 假設陣列資料{101, 34, 119, 1} 
		 * 第一輪 用34比較前面[資料],當為真時,往後移
		 * 101,101,119,1
		 * 34,101,119,1<-最後交換 
		 * 第二輪 用119比較前面 
		 * 第三輪 用1比較前面[資料],當為真時,往後移 
		 * 34,101,119,119
		 * 34,101,101,119 
		 * 34,34,101,119 
		 * 1,34,101,119<-最後交換
		 */
		insertionSort2(arr);
		// insertionSort(arr);

		// System.out.println(Arrays.toString(arr));
	}

	/**
	 * 選擇排序的概念,逐步推倒,方便理解....
	 * 
	 * @param arr
	 */
 	public static void insertionSort2(int[] arr) {

		boolean flag = false;
		int inserVal = arr[1];
		int inserIndex = 1 - 1;

		while (inserIndex >= 0 && inserVal < arr[inserIndex]) {
			arr[inserIndex + 1] = arr[inserIndex]; // 往後移
			inserIndex--;
		}
		arr[inserIndex + 1] = inserVal;

//		System.out.println("第一輪:"+Arrays.toString(arr));

		inserVal = arr[2];
		inserIndex = 2 - 1;

		while (inserIndex >= 0 && inserVal < arr[inserIndex]) {

			arr[inserIndex + 1] = arr[inserIndex]; // 往後移
			inserIndex--;
		}
		arr[inserIndex + 1] = inserVal;

		System.out.println("第2輪:" + Arrays.toString(arr));

		inserVal = arr[3];
		inserIndex = 3 - 1;
		System.out.printf("參數 => arr[3]:%d ,inserIndrx:%d\n", arr[3], inserIndex);
		while (inserIndex >= 0 && inserVal < arr[inserIndex]) {
			System.out.println(inserIndex + "@:" + arr[inserIndex]);

			arr[inserIndex + 1] = arr[inserIndex]; // 往後移
			System.out.println("往後移的資料:" + Arrays.toString(arr));
			inserIndex--;
		}
		arr[inserIndex + 1] = inserVal;

		System.out.println("最3輪:" + Arrays.toString(arr));

	}

	/**
	 * 重購
	 * 
	 * @param arr
	 */
	public static void insertionSort(int[] arr) {

		int inserVal = 0;
		int inserIndex = 0;
		
		for (int i = 1; i < arr.length; i++) {

			inserVal = arr[i];
			inserIndex = i - 1;

			while (inserIndex >= 0 && inserVal < arr[inserIndex]) {
				arr[inserIndex + 1] = arr[inserIndex]; // 往後移
				inserIndex--;
			}
			
			arr[inserIndex + 1] = inserVal;

		}

	}
}
