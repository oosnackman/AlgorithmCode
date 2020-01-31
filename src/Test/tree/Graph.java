package Test.tree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Description:
 * 
 * @author MarkLin
 * @Date:2019年12月24日下午3:39:23
 * @Remarks:
 */

public class Graph {

	private ArrayList<String> vertexList; // 多少個頂點
	private int[][] edges;// 存儲對應的矩陣
	private int numOfEdeges; // 邊的總數

	private boolean[] isVisited; 
	
	
	public static void main(String[] args) {

		int n = 5; // 頂點的數目
		String vertexValue[] = { "A", "B", "C", "D", "E" };
		Graph graph = new Graph(n);
		
		for (String ele : vertexValue) {
			graph.insertVertex(ele);
		}
		graph.insertEdge(0, 1, 1);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 2, 1);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(1, 4, 1);
		graph.showGraph();
		
	}

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

	// 插入邊
	public void insertEdge(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		numOfEdeges++;
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
