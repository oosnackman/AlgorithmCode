package Test.tree;
/**
 * Description:
 * @author MarkLin
 * @Date:2019年12月24日下午1:47:04
 * @Remarks:
 */

public class KnapsackProblem {

	public static void main(String[] args) {

		int w[]= {1,4,3}; //物品的重量
		int val[]= {1500,3000,2000}; //物品的價值
		int m=4;//背包的重量
		int n=val.length;//物品的個數
		
		// 為了紀錄放入商品的情況,定義一個陣列
		int[][] path = new int[n + 1][m + 1];
		
		int[][] v=new int[n+1][m+1];
		
		for (int i = 1; i < v.length; i++) {
			for (int j =1; j < v[0].length; j++) {
				
				if(w[i-1]>j) {
					v[i][j]=v[i-1][j];
				}else {
//					v[i][j]=Math.max(v[i-1][j], val[i-1]+(v[i-1][j-w[i-1]]));
					
					if(v[i-1][j]<val[i-1]+(v[i-1][j-w[i-1]])) {
						v[i][j]=val[i-1]+(v[i-1][j-w[i-1]]);
						path[i][j]=1;
						
					}else {
						v[i][j]=v[i-1][j];
					}
				}
				
			}
		}
		
		
		
		for (int i = 0; i < v.length; i++) {
			for (int j = 0; j < v[i].length; j++) {
				System.out.print(v[i][j]+"   ");
			}
			System.out.println();
		}
		
		
//		for (int i = 0; i < path.length; i++) {
//
//			for (int j = 0; j < path[i].length; j++) {
//
//				System.out.print(path[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		
		int i = path.length - 1; 
		int j = path[0].length - 1;  
		
		
		while(i>0&&j>0) {
			if(path[i][j] == 1) {
				System.out.printf("第%d商品放入到背包\n", i); 
				
				j-=w[i-1];
			}
			
			i--;
		}
	}

}
