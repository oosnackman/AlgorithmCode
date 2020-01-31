package datastruct.sort;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Shell排序
 * 
 * @author snack
 *
 */
public class ShellSort {

	public static void main(String[] args) {
		int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };

		//shellSort(arr);//氣泡
		shellSort3(arr);//插入

		System.out.println(Arrays.toString(arr));

	}

	// 使用逐步推倒的方式...Shell排序(氣泡)
	public static void shellSort(int[] arr) {

		// 第一輪排序
		// 10個資料分五組
		int temp = 0;
		// 因第一輪排序,是將10個資料分了5組
		for (int i = 5; i < arr.length; i++) {
//			System.out.printf("外圈%d\n", i);

			// 這兩個迴圈這樣跑才能(0,5)(1,6)(2,7)(3,8)(4,9)兩兩一對去比較 ;步長為5
			for (int j = i - 5; j >= 0; j -= 5) {
//				System.out.println("參考資料:" + Arrays.toString(arr));
//				System.out.printf("改變前:%d >%d:\n",arr[j],arr[j + 2]);
				if (arr[j] > arr[j + 5]) {
					temp = arr[j];
					arr[j] = arr[j + 5];
					arr[j + 5] = temp;
//					System.out.printf("改變後:%d >%d:\n",arr[j],arr[j + 2]);
				}
			}
//			System.out.println("--------");
		}

//	System.out.println("第1輪排序:" + Arrays.toString(arr));

		// 第一輪排序
		// 10個資料分五組
		// 因第2一輪排序,是將10個資料分了5/2組=2組
		for (int i = 2; i < arr.length; i++) {
			System.out.printf("外圈%d\n", i);
			for (int j = i - 2; j >= 0; j = j - 2) {
				System.out.println("改變前資料:" + Arrays.toString(arr));
				System.out.printf("改變前:%d >%d:\n", arr[j], arr[j + 2]);
				if (arr[j] > arr[j + 2]) {
					temp = arr[j];
					arr[j] = arr[j + 2];
					arr[j + 2] = temp;
					System.out.printf("改變後:%d >%d:\n", arr[j], arr[j + 2]);
					System.out.println("改變後資料:" + Arrays.toString(arr));
				}
			}
			System.out.println("--------");
		}

		System.out.println("第2輪排序:" + Arrays.toString(arr));

		// 第一輪排序
		// 10個資料分五組
		// 因第2一輪排序,是將10個資料分了2/2組=1組
		for (int i = 1; i < arr.length; i++) {

			for (int j = i - 1; j >= 0; j = j - 1) {
				// System.out.printf("外圈%d,內圈%d:\n", i, j);
				if (arr[j] > arr[j + 1]) {
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
			// System.out.println("--------");
		}

		// System.out.println("第3輪排序:" + Arrays.toString(arr));

	}

	// 使用逐步推倒的方式...Shell排序(氣泡)
	public static void shellSort2(int[] arr) {
		int temp = 0;
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {

				for (int j = i - gap; j >= 0; j = j - gap) {

					if (arr[j] > arr[j + gap]) {
						temp = arr[j];
						arr[j] = arr[j + gap];
						arr[j + gap] = temp;
					}
				}
			}
		}

	}

	// 優化..Shell排序(插入)
	public static void shellSort3(int[] arr) {

		//{ 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			//從第gap個元素,逐個對所載的群組直接進行插入排序
			
			//System.out.println("群組GAP:" +gap);
			for (int i = gap; i < arr.length; i++) {
				
				int j = i;
				int temp = arr[j];
				//System.out.println("新的-----:"+arr[j]+"<" + arr[j -gap]);
				if(arr[j]<arr[j -gap]) {
					//System.out.println(arr[j]+"<" + arr[j -gap]);
					while(j -gap>=0&&temp<arr[j -gap]) {
						arr[j]=arr[j-gap];
						j-=gap;
						//System.out.println("往後移的資料:" + Arrays.toString(arr));
					}
					arr[j]=temp;
					//System.out.println("----------");
				}

			}
		}
		
		//System.out.println(Arrays.toString(arr));

	}

}
