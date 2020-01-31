package prim;

import java.util.Arrays;

public class PrimAlgonrithm {

	public static void main(String[] args) {

		char[] data = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		int verxs = data.length;

		// 10000表示兩點不通
		int[][] weight = new int[][] { { 10000, 5, 7, 10000, 10000, 10000, 2 }, { 5, 10000, 10000, 9, 10000, 10000, 3 },
				{ 7, 10000, 10000, 10000, 8, 10000, 10000 }, { 10000, 9, 10000, 10000, 10000, 4, 10000 },
				{ 10000, 10000, 8, 10000, 10000, 5, 4 }, { 10000, 10000, 10000, 4, 5, 10000, 6 },
				{ 2, 3, 10000, 10000, 4, 6, 10000 }, };

		MGraph mGraph = new MGraph(verxs);

		MinTree minTree = new MinTree();
		minTree.createGraph(mGraph, verxs, data, weight);
		minTree.showGraph(mGraph);
		
		minTree.prim(mGraph, 0);
		
	}

}

//創建最小成本擴樹

class MinTree {

	public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {

		for (int i = 0; i < verxs; i++) {

			graph.data[i] = data[i];

			for (int j = 0; j < verxs; j++) {
				graph.weight[i][j] = weight[i][j];
			}
		}
	}

	public void showGraph(MGraph graph) {
		for (int[] link : graph.weight) {
			System.out.println(Arrays.toString(link));
		}
	}

	/**
	 * @param graph
	 * @param v     表示從第幾個頂點開始生成 'A'->0
	 */
	public void prim(MGraph graph, int v) {

		int[] visisted = new int[graph.verxs];

		visisted[0] = 1;
		int cursor1 = -1;
		int cursor2 = -1;
		int minWeight = 10000;

		for (int k = 1; k < graph.verxs; k++) {

			
			for (int i = 0; i < visisted.length; i++) {
				for (int j = 0; j < visisted.length; j++) {

					if (visisted[i] == 1 && visisted[j] == 0 && graph.weight[i][j] < minWeight) {
						minWeight = graph.weight[i][j];
						cursor1 = i;
						cursor2 = j;

					}

				}
			}

			System.out.println("邊<" + graph.data[cursor1] + "," + graph.data[cursor2] + "> 權值:" + minWeight);
			
			visisted[cursor2]=1;
			minWeight=10000;
			
		}

	}

}

class MGraph {

	int verxs; // 表示圖的節點個數
	char[] data; // 表示圖的節點個數
	int[][] weight;// 鄰接矩陣

	public MGraph(int verxs) {
		this.verxs = verxs;
		data = new char[verxs];
		weight = new int[verxs][verxs];
	}

}