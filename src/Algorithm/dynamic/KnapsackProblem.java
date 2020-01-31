package Algorithm.dynamic;

import org.junit.Test;

public class KnapsackProblem {

	// 重購
	public static void main(String[] args) {

		int[] weight = { 1, 4, 3 }; // 物品的重量
		int[] value = { 1500, 3000, 2000 }; // 物品的價值
		int m = 4; // 背包容量
		int n = value.length; // 物品的個數
		// 背包的狀況
		int[][] v = new int[n + 1][m + 1];

		// 為了紀錄放入商品的情況,定義一個陣列
		int[][] path = new int[n + 1][m + 1];
		
		System.out.println(v.length);
		for (int i = 1; i < v.length; i++) { // 不處理第一行
			for (int j = 1; j < v[0].length; j++) { // 不處理第一列

				// 如果當前的重量大於"當前背包的容量"就直接使用上一個最好的策略
				if (weight[i - 1] > j) {
					v[i][j] = v[i - 1][j];
				} else {
//					v[i][j] = Math.max(v[i - 1][j], value[i - 1] + v[i - 1][j - weight[i - 1]]);
					if(v[i - 1][j]< value[i - 1] + v[i - 1][j - weight[i - 1]]) {
						v[i][j] =value[i - 1] + v[i - 1][j - weight[i - 1]];
						//記錄當前的情況
						path[i][j]=1;
					}else {
						v[i][j]=v[i - 1][j];
					}
					
				}

			}
		}

		//輸出看一下
		for (int i = 0; i < v.length; i++) {

			for (int j = 0; j < v[i].length; j++) {

				System.out.print(v[i][j] + " ");
			}
			System.out.println();
		}
	
		
		
		for (int i = 0; i < path.length; i++) {

			for (int j = 0; j < path[i].length; j++) {

				System.out.print(path[i][j] + " ");
			}
			System.out.println();
		}
		
		int i = path.length - 1; 
		int j = path[0].length - 1;  
		while(i > 0 && j > 0 ) { 
			if(path[i][j] == 1) {
				System.out.printf("第%d商品放入到背包\n", i); 
				System.out.println("j:"+j+" weight[i-1]:"+weight[i-1]);
				j -= weight[i-1]; 
				
			
			}
			i--;
		}
		
	
		
	}

	// 原始方法只會顯式陣列
	@Test
	public void reKn() {

		int[] weight = { 1, 4, 3 }; // 物品的重量
		int[] value = { 1500, 3000, 2000 }; // 物品的價值
		int m = 4; // 背包容量
		int n = value.length; // 物品的個數
		// 背包的狀況
		int[][] v = new int[n + 1][m + 1];

		System.out.println(v.length);
		for (int i = 1; i < v.length; i++) { // 不處理第一行
			for (int j = 1; j < v[0].length; j++) { // 不處理第一列

				// 如果當前的重量大於"當前背包的容量"就直接使用上一個最好的策略
				if (weight[i - 1] > j) {
					v[i][j] = v[i - 1][j];
				} else {
					v[i][j] = Math.max(v[i - 1][j], value[i - 1] + v[i - 1][j - weight[i - 1]]);
				}

			}
		}

		for (int i = 0; i < v.length; i++) {

			for (int j = 0; j < v[i].length; j++) {

				System.out.print(v[i][j] + " ");
			}
			System.out.println();
		}
	}

}
