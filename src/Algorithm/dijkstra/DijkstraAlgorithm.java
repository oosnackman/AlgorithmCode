package Algorithm.dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm {
	public static void main(String[] args) {

		// 建立資料
		char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		int[][] matrix = new int[vertex.length][vertex.length];
		final int N = 65535; // 表示不可以通
		matrix[0] = new int[] { N, 5, 7, N, N, N, 2 };
		matrix[1] = new int[] { 5, N, N, 9, N, N, 3 };
		matrix[2] = new int[] { 7, N, N, N, 8, N, N };
		matrix[3] = new int[] { N, 9, N, N, N, 4, N };
		matrix[4] = new int[] { N, N, 8, N, N, 5, 4 };
		matrix[5] = new int[] { N, N, N, 4, 5, N, 6 };
		matrix[6] = new int[] { 2, 3, N, N, 4, 6, N };

		Graph graph = new Graph(vertex, matrix);
		graph.showGraph();

		graph.dsj(6);
		graph.showDijkstra();
		
		graph.showGraph();

	}
}

class Graph {

	private char[] vertex; // 頂點
	private int[][] matrix; // 鄰接頂點
	private VisitedVertex vv = null;

	public Graph(char[] vertex, int[][] matrix) {
		this.vertex = vertex;
		this.matrix = matrix;
	}

	// 顯示圖
	public void showGraph() {

		for (int[] ele : matrix) {
			System.out.println(Arrays.toString(ele));
		}
	}

	//顯示最後結果
	public void showDijkstra() {
		vv.show();
	}
	/**
	 * 
	 * @param index 表示出發頂點對應的下標
	 */
	public void dsj(int index) {

		vv = new VisitedVertex(this.vertex.length, index);
		update(index);// 更新index頂點到周圍頂點的距離和前驅頂點
		
		
		for(int j = 1; j <vertex.length; j++) {
			index = vv.upadteArr();
			update(index); 
		} 
		
	}

	// 更新index下標頂點到周圍頂點的距離和周圍頂點的前驅頂點
	private void update(int index) {

		int len = 0;
		for (int i = 0; i < matrix[index].length; i++) {

			// 出發頂點到index頂點的距離+從index頂點到i頂點的距離的和
			len = vv.getDis(index) + matrix[index][i];

			// 如果i頂點沒有被訪問過,並且len 小於出發頂點到i頂點的距離,則需要更新
			if (!vv.in(i) && len < vv.getDis(i)) {
				vv.updatePre(i, index);
				vv.updateDis(i, len);
			}

		}

	}
}

class VisitedVertex {
	public int[] already_arr; // 記錄頂點是否訪問過 , 1訪問 0未訪問
	public int[] pre_visited; // 每個下標對應值為前一個頂點的下標 , 會動態更新
	public int[] dis; // 記錄出發到所有點頂的距離 ,比如G為出發頂點,就會紀錄G到其他頂點的距離，會動態更新，求的最短距離放到dis

	/**
	 * @param lenght : 表示頂點的個數
	 * @param index  :出發頂點對應的下標
	 */
	public VisitedVertex(int lenght, int index) {
		this.already_arr = new int[lenght];
		this.pre_visited = new int[lenght];
		this.dis = new int[lenght];

		// 初始化
		Arrays.fill(dis, 65535);
		this.already_arr[index] = 1;
		this.dis[index] = 0;

	}

	/**
	 * *功能:判斷index頂點是否被訪問過
	 * 
	 * @param index
	 * @return 如果反問過返回true,反則flase
	 */
	public boolean in(int index) {
		return this.already_arr[index] == 1;
	}

	/**
	 * *功能:更新出發頂點到index頂點的距離
	 * 
	 * @param index 更新的下標
	 * @param len   距離
	 */
	public void updateDis(int index, int len) {
		dis[index] = len;
	}

	/**
	 * *功能:更新pre這個頂點的前驅為index頂膽
	 * 
	 * @param pre
	 * @param index
	 */
	public void updatePre(int pre, int index) {
		pre_visited[pre] = index;
	}

	/**
	 * *功能:返回出發頂點到index頂點的距離
	 * 
	 * @param index
	 * @return
	 */
	public int getDis(int index) {
		return dis[index];
	}

	//將三個陣列的情況輸出
	public void show() {

		System.out.println("==========================");
		for (int i : already_arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		for (int i : pre_visited) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		for (int i : dis) {
			System.out.print(i + " ");
		}
		
		System.out.println();
		char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		int count = 0;
		for (int i : dis) {
			if (i != 65535) {
				System.out.print(vertex[count] + "(" + i + ") ");
			} else {
				System.out.println("N ");
			}
			count++;
		}
		System.out.println();

	}
	
	/**
	 * 
	 *  繼續選擇並返回新的訪問頂點,比如G完後,就是A做為新的訪問頂點( 類似一層一層去找最小的)
	 * @return
	 */
	public int upadteArr() {
		
		int min=65535,index=0;
		for (int i = 0; i < already_arr.length; i++) {
			
			//找出最小且還沒訪問過距離的 "下標"
			if(already_arr[i]==0 && dis[i]<min ) {
				
				min=dis[i];
				index=i;
			}
		}
		//更新index頂點被訪問過
		already_arr[index]=1;
		return index;
	}

}