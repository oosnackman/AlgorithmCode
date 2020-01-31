package datastruct.stack;

import java.util.Arrays;

public class RadixSort {

	public static void main(String[] args) {

		int arr[]= {53,3,542,748,14,214};
		int[] bucketElementCounts=new int[10];

		
		radixSort2(arr);
		System.out.println("基數排序:" + Arrays.toString(arr));
	}
	
	//概念推倒...
	public static void radixSort(int arr[]) {
		
		
		//第一輪
		
		//定義二維陣列,表示10個桶,每個桶都是一個一維數組 , 基數排序是使用[空間換時間的經典算法...]
		int[][] bucket=new int[10][arr.length];
		
		//記錄每個桶存放多少資料
		int[] bucketElementCounts=new int[10];
		
		//第1輪(針對給個元素的個位進行處理)
		for(int j=0;j<arr.length;j++) {
			
			//取出每個元素的個位數
			int digitOfElement=arr[j]/1%10;
			
			//放入對硬的桶中
//			System.out.println("=>"+digitOfElement+":"+bucket[digitOfElement][bucketElementCounts[digitOfElement]]+":"+bucketElementCounts[digitOfElement]);
			bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[j];
			bucketElementCounts[digitOfElement]++;
		}
		
		
		int index=0;
		for (int k = 0; k < bucketElementCounts.length; k++) {
		
			if(bucketElementCounts[k]!=0) {
				
				for (int i = 0; i < bucketElementCounts[k]; i++) {
					
					arr[index++] = bucket[k][i];
				}
			}
			bucketElementCounts[k]=0;
		}
		
//		方法一
//		int index = 0;
//		for (int i = 0; i < bucket.length; i++) {
//
//			int j = 0;
//			int digitOfElement = i % 10;
//			while (bucket[digitOfElement][j] != 0) {
//
//				arr[index++] = bucket[digitOfElement][j++];
////				index++;
////				j++;
//
//			}
//			bucketElementCounts[i]=0;
//			
//		}
		
		System.out.println("第一輪:"+Arrays.toString(arr));
		
		
		//======================================================

		//第2輪(針對給個元素的個位進行處理)
		for(int j=0;j<arr.length;j++) {
			
			//取出每個元素的個位數
			int digitOfElement=arr[j]/10%10;
			
			//放入對硬的桶中
//			System.out.println("=>"+digitOfElement+":"+bucket[digitOfElement][bucketElementCounts[digitOfElement]]+":"+bucketElementCounts[digitOfElement]);
			bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[j];
			bucketElementCounts[digitOfElement]++;
		}
		
		
		 index=0;
		for (int k = 0; k < bucketElementCounts.length; k++) {
		
			if(bucketElementCounts[k]!=0) {
				
				for (int i = 0; i < bucketElementCounts[k]; i++) {
					
					arr[index++] = bucket[k][i];
				}
			}
			bucketElementCounts[k]=0;
		}

		
		System.out.println("第2輪:"+Arrays.toString(arr));
		
		//======================================================

				//第3輪(針對給個元素的個位進行處理)
				for(int j=0;j<arr.length;j++) {
					
					//取出每個元素的百 位數
					int digitOfElement=arr[j]/100%10;
					
					//放入對硬的桶中
//					System.out.println("=>"+digitOfElement+":"+bucket[digitOfElement][bucketElementCounts[digitOfElement]]+":"+bucketElementCounts[digitOfElement]);
					bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[j];
					bucketElementCounts[digitOfElement]++;
				}
				
				
				 index=0;
				for (int k = 0; k < bucketElementCounts.length; k++) {
				
					if(bucketElementCounts[k]!=0) {
						
						for (int i = 0; i < bucketElementCounts[k]; i++) {
							
							arr[index++] = bucket[k][i];
						}
					}
					bucketElementCounts[k]=0;
				}

				
				System.out.println("第3輪:"+Arrays.toString(arr));
				
		
	}

	
	//重購
	public static void radixSort2(int arr[]) {

		// 1.得到陣列中最大數的位數
		int max = arr[0]; // 假設最大的數值
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		// 2.得到最大數是幾位數
		int maxLength = (max + "").length();

		// 定義二維陣列,表示10個桶,每個桶都是一個一維數組 , 基數排序是使用[空間換時間的經典算法...]
		int[][] bucket = new int[10][arr.length];

		// 記錄每個桶存放多少資料
		int[] bucketElementCounts = new int[10];

		//(針對每個元素對硬的位進行排序處理),第一次個位,第二次十位,第三次是百位..
		for (int l = 0,n=1; l <= maxLength; l++,n*=10) {

			for (int j = 0; j < arr.length; j++) {

				// 取出每個元素的個位數
				int digitOfElement = arr[j] / n % 10;

				// 放入對硬的桶中
				bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
				bucketElementCounts[digitOfElement]++;
			}

			int index = 0;
			for (int k = 0; k < bucketElementCounts.length; k++) {

				if (bucketElementCounts[k] != 0) {

					for (int i = 0; i < bucketElementCounts[k]; i++) {

						arr[index++] = bucket[k][i];
					}
				}
				bucketElementCounts[k] = 0;
			}

			
//			System.out.println("第一輪:" + Arrays.toString(arr));
		}
	}
}
