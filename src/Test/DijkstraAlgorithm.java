package Test;

import java.util.Arrays;


/**
 * Description:
 * 
 * @author MarkLin
 * @Date:2019年12月28日下午3:40:12
 * @Remarks:
 */

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
		graph.dsj(6);
		graph.showDijkstra();
		
	}

}

class Graph {
	private char[] vertex; // 頂點
	private int[][] matrix; // 鄰接頂點
	VisitedVertex v=null;
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
		v.show();
	}
	
	public void dsj(int index) {
		
		v=new VisitedVertex(vertex.length, index);
		update(index);// 更新index頂點到周圍頂點的距離和前驅頂點
		
		for (int i = 1; i < matrix.length; i++) {
			int vIndex = v.upadteArr();
			update(vIndex);
		}
		
	}
	
	private void update(int index) {
		
		int len=0;
		for (int i = 0; i < matrix[index].length; i++) {
			
			len=v.getDis(index)+ matrix[index][i];
			if(!v.in(i) && len < v.getDis(i)) {
				v.updatePre(i, index);
				v.updateDis(i, len);
			}
		}
	}
}

class VisitedVertex {

	private int aleady_arr[]; // 已經拜訪過
	private int dis[]; // 距離
	private int pre_visited[]; // 前驅節點

	public VisitedVertex(int vertex, int index) {
		this.aleady_arr = new int[vertex];
		this.dis = new int[vertex];
		this.pre_visited = new int[vertex];

		Arrays.fill(dis, 65535);
		this.aleady_arr[index] = 1;
		this.dis[index] = 0;

	}
	

	//將三個陣列的情況輸出
	public void show() {

		System.out.println("==========================");
		for (int i : aleady_arr) {
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

	public void updateDis(int index, int len) {
		this.dis[index] = len;
	}

	public void updatePre(int pre, int index) {
		this.pre_visited[pre] = index;
	}

	// 判斷是否被訪問過
	public boolean in(int index) {
		return aleady_arr[index] == 1;

	}

	// 返回出發頂點到index頂點的距離
	public int getDis(int index) {
		return this.dis[index];

	}
	
	/**
	 * 
	 *  繼續選擇並返回新的訪問頂點,比如G完後,就是A做為新的訪問頂點( 類似一層一層去找最小的)
	 * @return
	 */
	public int upadteArr() {
		
		int min=65535,index=0;
		
		for (int i = 0; i < aleady_arr.length; i++) {
			
			if(aleady_arr[i]==0 && dis[i]< min) {
				min=dis[i];
				index=i;
			}
			
		}
		
		aleady_arr[index]=1;
		return index;
		
	}

}