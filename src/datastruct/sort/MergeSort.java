package datastruct.sort;

import java.util.Arrays;

/**
   * 合併排序-分治概念,先分在合
 * @author snack
 *
 */
public class MergeSort {

	public static int count=0;
	public static void main(String[] args) {

		int[] arr= {8,4,5,7,1,3,6,2};
		int[] temp=new int[arr.length];
		mergeSort(arr, 0, arr.length-1, temp);
		
		
		System.out.println("合併排序:"+Arrays.toString(arr));
	}

	
	// 分+合
	public static void mergeSort(int[] arr, int left, int right, int[] temp) {

		if (left < right) {
			int mid = (left + right) / 2;
			// 先向左遞歸分
			mergeSort(arr, left, mid, temp);

			// 先向右遞歸分
			mergeSort(arr, mid + 1, right, temp);

			merge(arr, left, mid, right, temp);

		}
	}
	/**
	 * 合併的方法(治)
	 * @param arr 排序的資料
	 * @param left 左邊有序序列的初始索引
	 * @param mid 中間有序序列的初始索引
	 * @param right 右邊有序序列的初始索引
	 * @param 中轉的資料
	 */
	public static void merge(int[] arr,int left,int mid,int right,int[] temp) {
		count++;
		System.out.println();
		System.out.println("mergeCcount:"+count);
		int i=left;
		int j=mid+1;
		int t=0;//指向temp陣列(中轉)的索引
		
		//(一)把左右的陣列資料按照規則放到temp陣列裡
		while (i<=mid&&j<=right) {
			
			if(arr[i]<=arr[j]) {
				
				temp[t]=arr[i];
				t++;
				i++;
			}else {
				temp[t]=arr[j];
				t++;
				j++;
			}
		}
		
		//(二)
		//把剩餘數據的一方,依次全部填充到temp
		
		while(i<=mid) {
			temp[t]=arr[i];
			i++;
			t++;
		}
		
		while(j<=right) {
			temp[t]=arr[j];
			j++;
			t++;
		}
		
		//(三)
		//將temp陣列的元素複製到arr
		t=0;
		int tempLeft=left;
		System.out.println("合併的陣列範圍=>tempLeft:"+tempLeft +"right:"+right);
		while(tempLeft<=right) { //第一次合併t0r1,t2r3,t0r4
			
			arr[tempLeft]=temp[t];
			tempLeft++;
			t++;
			
		}
		
		
	}
}
