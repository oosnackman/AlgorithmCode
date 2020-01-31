package Algorithm.floyd;

import java.util.Arrays;

public class FloydAlgorithm {

	public static void main(String[] args) {

		
		char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		//創立鄰接矩陣
		int[][] matrix = new int[vertex.length][vertex.length];
		final int N = 65535;
		matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
		matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
		matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
		matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
		matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
		matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
		matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };
		
		Graph graph = new Graph(vertex.length, matrix, vertex);
		graph.floyd();
		graph.show();
	}

}


//創建圖
class Graph{
	
	char[] vertex; //存放頂點的陣列
	private int[][] dis; //記錄,從個個頂點出發到其他頂點的距離
	private int[][] pre; //記錄,到達目標頂點的前驅頂點
	
	
	public Graph(int lenght,int[][] matrix,char[] vertex) {
		this.vertex=vertex;
		this.dis=matrix;
		this.pre=new int[lenght][lenght];
		
		//初始化
		for (int i = 0; i < lenght; i++) {
			
			Arrays.fill(pre[i], i);
			
		}
	}
	
	//顯示pre&dis 陣列
	public void show() {
		
		char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		for (int i = 0; i < dis.length; i++) {
			for (int j = 0; j < dis.length; j++) {
				System.out.print(vertex[pre[i][j]]+" ");
			}
			
			System.out.println();
			for (int j = 0; j < dis.length; j++) {
				System.out.print("("+vertex[i]+"到"+vertex[j]+"的最短路徑是"+dis[i][j]+") ");
			}
			System.out.println();
			System.out.println();
		}
	}
	
	
	public void floyd() {
		
		int len=0; //變量保存距離
		
		//對中間頂點的遍歷 [A,B,C,D,E,F,G]
		for (int k = 0; k < dis.length; k++) {
			//對出發頂點的遍歷  [A,B,C,D,E,F,G]
			for (int i = 0; i < dis.length; i++) {
				//對終點頂點的遍歷  [A,B,C,D,E,F,G]
				for (int j = 0; j < dis.length; j++) {
					len=dis[i][k]+dis[k][j]; //從i頂點出發,經過k中間節點,到達j頂點距離 例如"[C,A,G]的距離=>從C到A的距離+A到G的距離
					
					//就是判斷len是否小於直連的距離 [C,G] 沒有透過A直接 從C到G的意思
					if(len<dis[i][j]) {
						dis[i][j]=len; //更新最短的距離
						pre[i][j]=pre[k][j];
					}
				}
			}
		}
	}
	
	
}