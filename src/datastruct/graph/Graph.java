package datastruct.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

	private ArrayList<String> vertexList; // 存儲頂點的集合
	private int[][] edges; // 存儲對應的矩陣
	private int numOfEdeges;// 表示邊的數目
	private boolean[] isVisited; // 定義一個boolean[],記錄每個頂點是否被訪問過

	public static void main(String[] args) {

	int n = 5; // 頂點的數目
//		int n = 8; // 頂點的數目
String vertexValue[] = { "A", "B", "C", "D", "E" };
//		String vertexValue[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
		Graph graph = new Graph(n);

		for (String ele : vertexValue) {
			graph.insertVertex(ele);
		}
//		測試 1
		graph.insertEdge(0, 1, 1);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 2, 1);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(1, 4, 1);
		
//		graph.insertEdge(0, 1, 1);
//		graph.insertEdge(0, 2, 1);
//		graph.insertEdge(1, 3, 1);
//		graph.insertEdge(1, 4, 1);
//		graph.insertEdge(3, 7, 1);
//		graph.insertEdge(4, 7, 1);
//		graph.insertEdge(2, 5, 1);
//		graph.insertEdge(2, 6, 1);
//		graph.insertEdge(5, 6, 1);
		graph.showGraph();

		// 測試dfs
		System.out.println("深度遍歷");
		graph.dfs();
System.out.println();
		// 測試bfs
		System.out.println("廣度優先遍歷");
		graph.bfs();
	}

	// 多少個頂點
	public Graph(int n) {
		edges = new int[n][n];
		vertexList = new ArrayList<String>(n);
		numOfEdeges = 0;
		isVisited = new boolean[n];
	}

	// 插入頂點
	public void insertVertex(String vertex) {
		vertexList.add(vertex);
	}

	// 添加邊
	/**
	 * @param v1     表示點的下標,第幾個頂點 "A"-"B" ;"A"->0 "B"->1
	 * @param v2     表示點的下標,第幾個頂點
	 * @param weight 1表示有通,0表示無通
	 */
	public void insertEdge(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		numOfEdeges++;
	}

	// 得到第一個鄰接頂點下標 (也就是判斷A->B 是否有通 B-C是否有通 ,有通的話返回B,C的下標)
	public int getFirstNeighbor(int index) {

		for (int i = 0; i < vertexList.size(); i++) {

			if (edges[index][i] > 0) {
				return i;
			}
		}

		return -1; // 沒找到
	}

	// 根據前一個鄰接頂點的下標來獲取,下一個鄰接頂點
	/**
	 * @param v1 前一個頂點
	 * @param v2
	 * @return
	 *
	 */
	public int getNextNeighbor(int v1, int v2) {

		for (int i = v2 + 1; i < vertexList.size(); i++) {

			if (edges[v1][i] > 0) {
				return i;
			}
		}
		return -1; // 沒找到
	}

	// 深度優先算法||縱向算法DFS

	/**
	 * @param isVisit
	 * @param i       訪問第一個頂點 也就是第一次從0下標開始使
	 */
	private void dfs(boolean[] isVisit, int i) {

		System.out.println(getValueByIndex(i) + "-->");
		isVisit[i] = true;

		// 準備下一個
		int w = getFirstNeighbor(i);
		while (w != -1) {
			if (!isVisit[w]) {
				dfs(isVisit, w);
			}

			// 如果w结点已经被访问过
			w = getNextNeighbor(i, w);

		}

	}

	// 遍歷所有節點
	public void dfs() {
//		dfs(isVisited, 0);
		isVisited = new boolean[vertexList.size()];
		for (int i = 0; i < getNumOfVertex(); i++) {
	
			if(!isVisited[i]) {
				dfs(isVisited,i);
			}
			
		}
	}

	// 對一個廣度優先bfs收循
	private void bfs(boolean[] isVisited, int i) {
		int u; // 對列頭節點對應的下標
		int w; // 鄰接點w
		// 對列,記錄點訪問的順序
		LinkedList queue = new LinkedList();

		System.out.print(getValueByIndex(i) + "=>");
		// 標記已訪問
		isVisited[i] = true;
		// 將節點加入對列
		queue.addLast(i);

		while (!queue.isEmpty()) {
			// 取出對列頭節點的下標
			u = (Integer) queue.removeFirst();
			// 得到第一個鄰接點的下標 w
			w = getFirstNeighbor(u);
			while (w != -1) {// 找到

				if (!isVisited[w]) {
					System.out.print(getValueByIndex(w) + "=>");
					isVisited[w] = true;
					// 入對
					queue.addLast(w);
				}
				// 以U為前驅點,找w後面的下一個鄰接點
				w = getNextNeighbor(u, w);
			}
		}

	}

	public void bfs() {
		isVisited = new boolean[vertexList.size()];
		for (int i = 0; i < getNumOfVertex(); i++) {
			if (!isVisited[i]) {
				bfs(isVisited, i);
			}
		}
	}

	// 以下為常用的方法~~

	// 返回節點的個數
	public int getNumOfVertex() {
		return vertexList.size();

	}

	// 得到邊的數目
	public int getNumOfEdges() {
		return numOfEdeges;
	}

	// 返回節點(下標)對應的資料 0->"A" , 1->"B"...
	public String getValueByIndex(int i) {
		return vertexList.get(i);
	}

	// 返回v1,v2的權值(是否通)
	public int getWeight(int v1, int v2) {
		return edges[v1][v2];

	}

	// 顯示圖對應的舉陣
	public void showGraph() {

		for (int[] line : edges) {
			System.out.println(Arrays.toString(line));
		}
	}

}
