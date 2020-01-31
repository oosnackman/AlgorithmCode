package datastruct.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找要求資料是有序的
 * 
 * @author snack
 *
 */
public class BinarySearch {

	public static void main(String[] args) {
		int arr[] = { 1, 8, 10, 89, 1000, 1000,1234 };
	    //方法1:只能查循單一值
		// System.out.println("resIndex="+binarySearch(arr, 0, arr.length-1, 1000));
		
		//方法1:查循多值
		List<Integer> reslist=binarySearch2(arr, 0, arr.length-1, 1);
		System.out.println("resIndex="+reslist);
	
	}

	/**
	 * 二分查找算法
	 * 
	 * @param arr     陣列
	 * @param left    左邊索引
	 * @param right   右邊索引
	 * @param findVal 查詢的值
	 * @return 如找到就返回下標
	 */
	public static int binarySearch(int arr[], int left, int right, int findVal) {

		int reVal = 0;
		int mid = (left + right) / 2;
		if (left > right) {
			return -1;
		}
//		if(findVal==arr[mid]) {
//			return mid;
//		}

		if (findVal > arr[mid]) {
			return binarySearch(arr, mid + 1, right, findVal);

		} else if (findVal < arr[mid]) {

			return binarySearch(arr, left, mid - 1, findVal);
		} else {
			return mid;
		}

	}

	/**
	 * 二分查找算法
	 * 
	 * @param arr     陣列
	 * @param left    左邊索引
	 * @param right   右邊索引
	 * @param findVal 查詢的值
	 * @return 可返回多個值的下標
	 */
	public static ArrayList<Integer> binarySearch2(int arr[], int left, int right, int findVal) {
		
		System.out.println("hello~");
		
		if (left > right) {
			return new ArrayList<Integer>();
		}

		
		int[] reVal = new int[arr.length];
		int mid = (left + right) / 2;
		
		
		if (findVal > arr[mid]) {
			return binarySearch2(arr, mid + 1, right, findVal);

		} else if (findVal < arr[mid]) {

			return binarySearch2(arr, left, mid - 1, findVal);
		} else {

			ArrayList<Integer> reList = new ArrayList<Integer>();

			int tempIndex = mid - 1;

			// 向左掃描
			while (true) {

				if (tempIndex < 0|| arr[tempIndex] != findVal  ) {
					break;
				}
				reList.add(tempIndex);
				tempIndex--;
			}

			// 中間的
			reList.add(mid);

			
			// 向右掃描
			tempIndex = mid + 1;
			while (true) {

				if (tempIndex > arr.length-1||arr[tempIndex] != findVal ) {
					break;
				}
				reList.add(tempIndex);
				tempIndex++;
			}
			
			return reList;

		}

	}

}
