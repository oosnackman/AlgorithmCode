package datastruct.sort;

import java.util.Arrays;

public class QuickSort {
	public static void main(String[] args) {

//		int[] arr = { -9, 78, 0, 23, -567, 70 };
		
 int[] arr = { -9, -1, 0, 23, -567, 70 };


		quickSort(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));
		


	}

	public static void quickSort(int[] arr, int left, int right) {

		int l = left;
		int r = right;
		int pivot = arr[(left + right) / 2];
		int temp = 0;

		while (l < r) {

			// 中間,左邊找到最大的值
			while (arr[l] < pivot) {
				l+=1;
			}
//			System.out.println("l:"+l);
			// 中間,右邊找到最小的值
			while (arr[r] > pivot) {
				r-=1;
			}
//			System.out.println("r:"+r);
			if (l >= r) {
				break;
			}

			// 交換
			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;

//			System.out.println(arr[l]+"="+pivot);
			if (arr[l] == pivot) {
				r-=1;
//			System.out.println("@r:"+r+":"+l);
			}
			
			if (arr[r] == pivot) {
				l+=1;
			}
//			System.out.println(Arrays.toString(arr));
//			System.out.println("-----------");
			
		
			
			if(l==r) {
				l++;
				r--;
			}
			
			if(left<r) {
				quickSort(arr, left, r);
			}
//			System.out.println("left:"+left+"<:"+r+" pivot:"+pivot);
//			
			if(right >r) {
				quickSort(arr, l, right);
			}
		}

	}
}
