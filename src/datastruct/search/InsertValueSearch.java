package datastruct.search;

import java.util.Arrays;

public class InsertValueSearch {

	private static int count=0;
	public static void main(String[] args) {

		int[] arr = new int[100];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}
		int index = insetValueSearch(arr, 0, arr.length-1, 100);
		System.out.println("查找次數:"+count+",index:"+index);
	}

	/**
	 * 內插收循法
	 * 
	 * @param arr     陣列
	 * @param left    左邊索引
	 * @param right   右邊索引
	 * @param findVal 查詢的值
	 * @return
	 */
	public static int insetValueSearch(int arr[], int left, int right, int findVal) {
		count++;
		if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
			return -1;
		}

		//要背的公式 left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left])
		int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);

		int midVal = arr[mid];

		if (findVal > midVal) { // 向右查找
			return insetValueSearch(arr, mid + 1, right, findVal);
		} else if (findVal < midVal) {// 向左查找
			return insetValueSearch(arr, left, mid - 1, findVal);
		}else {
			return mid;
		}

	}
}
