package datastruct.tree;

import java.util.Arrays;

/**
 * 推排序的方法
 * 
 * @author snack
 *
 */
public class HeapSort {

	public static void main(String[] args) {
		// 生序排序&&大推積
		int arr[] = { 4, 6, 8, 5, 9 };
		heapSort(arr);
	}

	// 推排序的方法
	public static void heapSort(int arr[]) {

//		//分析步驟
//		adjusetHeap(arr, 1, arr.length);
//		System.out.println(Arrays.toString(arr));
//		adjusetHeap(arr, 0, arr.length);
//		System.out.println(Arrays.toString(arr));

	
		//將無序序列,根據需求選擇大推積或小推積
		for (int i = arr.length/2-1; i>=0; i--) {
			adjusetHeap(arr, i, arr.length);
		}
		 
		
		int temp=0;
		for (int j = arr.length-1; j>0; j--) {
			
			temp=arr[j];
			arr[j]=arr[0];
			arr[0]=temp;
			adjusetHeap(arr, 0, j);
		}
		
		System.out.println("有序大推積:"+Arrays.toString(arr));
	}

	// 將一個陣列(二叉樹),調整成一個大推積
	/**
	 * *功能:完成將以i對應的非葉子節點的樹調整成大推積 *舉例{4,6,8,5,9};=>i=1=>adjusetHeap=>得到 {4,9,8,5,6}
	 * 
	 * @param arr    待調整的陣列
	 * @param i      表示非葉子節點的在陣列中的索引
	 * @param lenght 表示對多少個元素繼續調整,lenght是在逐漸的減少
	 */
	public static void adjusetHeap(int arr[], int i, int lenght) {

		int temp = arr[i];

		// k = i * 2 + 1 是i節點的左子節點
		for (int k = i * 2 + 1; k < lenght; k = k * 2 + 1) {
			// 判斷左子節點的值是否小於右子節點的值
			if (k + 1 < lenght && arr[k] < arr[k + 1]) {
				k++; // 就將k指向右子節點

			}

			if (arr[k] > temp) { // 如果子節點大於父節點
				arr[i] = arr[k];
				i = k; // 將i指向左子節點 ,重新循環比較
			} else {
				break;
			}

		}
		arr[i] = temp;

	}

}
