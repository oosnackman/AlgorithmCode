package datastruct.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;

public class FibonacciSearch {

	public static int maxSize = 20;

	public static void main(String[] args) {
		
		int[] arr = { 1,8,10,89,1000,1234};
		System.out.println(fibSearch(arr, 1000));
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(1, 101);
	}

	// mid=low+F(k-1)-1,公式 
	public static int[] fib() {
		int[] f = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < maxSize; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f;
	}

	// 使用非遞歸方式編寫算法
	/**
	 * @param arr 陣列
	 * @param key 需要查找的關鍵碼(值)
	 * @return 返回對應的下標,如果沒有-1
	 */
	public static int fibSearch(int[] arr, int key) {

		int low = 0;
		int high = arr.length - 1;
		int k = 0;// 表示fib分割數值得下標
		int mid = 0;
		int f[] = fib();// 獲取fib陣列

		// 前製作用處理-----------------------
		// 為了使用得到fib黃金的分割的下標
		while (high > f[k] - 1) {
			k++;
		}

		// 得到跟fib黃金的分割的長度
		int[] temp = Arrays.copyOf(arr, f[k]);

		// temp = {1, 8, 10, 89, 1000, 1234,0,0,0} =>{=1, 8, 10, 89, 1000,
		// 1234,1234,1234,1234}
		for (int i = high + 1; i < temp.length; i++) {
			temp[i] = arr[high];
		}

		// 前製作用開始使用-----------------------
		// 開始使用while+fib黃金分割來找資料

		while (low <= high) {

			mid = low + f[k - 1] - 1; // 黃金分割公式

			if (key < temp[mid]) { // 要向左查找
				high = mid - 1;
				k--;
			} else if (key > temp[mid]) {
				//1.全部元素=前面的元素+後面的元素
				//2.f[k]=k[k-1]+f[k-2]
				
				low = mid + 1;
				k -= 2;
			} else {
				
				if (mid <= high) {
					return mid;
				} else {
					return high;
				}
				
			}

		}

		return -1;

	}
}
