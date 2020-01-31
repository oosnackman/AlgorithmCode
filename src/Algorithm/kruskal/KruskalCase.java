package Algorithm.kruskal;



public class KruskalCase {

	private int edgeNum; // 邊的個數
	private char[] vertexs; // 頂點的陣列
	private int[][] matrix;// 鄰接矩陣
	private static final int INF = Integer.MAX_VALUE; // 表示兩個點不通

	public static void main(String[] args) {

		char[] vertexs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		int matrix[][] = {
				/* A *//* B *//* C *//* D *//* E *//* F *//* G */
				/* A */ { 0, 12, INF, INF, INF, 16, 14 },
				/* B */ { 12, 0, 10, INF, INF, 7, INF },
				/* C */ { INF, 10, 0, 3, 5, 6, INF }, 
				/* D */ { INF, INF, 3, 0, 4, INF, INF },
				/* E */ { INF, INF, 5, 4, 0, 2, 8 },
				/* F */ { 16, 7, 6, INF, 2, 0, 9 },
				/* G */ { 14, INF, INF, INF, 8, 9, 0 } };

		KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
		System.out.println("資料矩陣=");
		kruskalCase.print();
		
		Edata[] edgEdatas = kruskalCase.getEdges();
		kruskalCase.sortEdges(edgEdatas);
		System.out.println("排序前=");
		for (Edata ele:edgEdatas) {
			System.out.println(ele);
		}
		
		System.out.println("排序後=");
		for (Edata ele:edgEdatas) {
			System.out.println(ele);
		}
		
		System.out.println("最小生成樹序後=");
		kruskalCase.kruskal();


	}

	public KruskalCase(char[] vertexs, int[][] matrix) {

		int vlen = vertexs.length;

		// 初始化頂點
		this.vertexs = new char[vlen];
		for (int i = 0; i < vertexs.length; i++) {
			this.vertexs[i] = vertexs[i];
		}

		// 初始化
		this.matrix = new int[vlen][vlen];
		for (int i = 0; i < vlen; i++) {
			for (int j = 0; j < vlen; j++) {
				this.matrix[i][j] = matrix[i][j];
			}
		}

		for (int i = 0; i < vlen; i++) {
			for (int j = i + 1; j < vlen; j++) {
				if (this.matrix[i][j] != INF) {
					edgeNum++;
				}
			}
		}


	}
	
	public void kruskal(){
		
		int index=0; //最後結果陣列的索引
		int[] ends=new int[edgeNum];//用於保存"已有最小花費樹"中的每個頂點 在"最小花費樹"的終點
		
		//創建保存最後的最小花費樹
		Edata[] rets=new Edata[edgeNum];
		
		//獲取圖中 所有的邊的集合 共有12個邊
		Edata[] edges=getEdges();
		
		//按照權值從小到到排序
		sortEdges(edges);
		
		//將邊添加到最小花費樹中時,判斷是否準備加入的邊是否形成了迴路,如果沒有就加入rets,否則不能加入
		for (int i = 0; i < edgeNum; i++) {
			
			int p1=getPosition(edges[i].stat); //獲取i條邊的第一個頂點(起點)   EX:p1=4
			int p2=getPosition(edges[i].end); //獲取i條邊的第二個頂點   EX:p2=5
			
			
			//獲取p1這個頂點在"已有最小花費樹中"的終點是哪一個
			int m=getEnd(ends, p1); //EX:m=4
			
			//獲取p2這個頂點在"已有最小花費樹中"的終點是哪一個
			int n=getEnd(ends, p2); //EX:n=5
			
			if(m!=n) {									
				ends[m]=n; // 設置m "在已有最小花費樹"中的終點, <E,F> 也就是把E的終點設置為F[0,0,0,0,5,0,0,0,0,0,0,0,]
				rets[index++]=edges[i];
			}
			
		}
		
		for(int i = 0; i < index; i++) {
			System.out.println(rets[i]);
		}
	}
	
	/**
	 *  功能:獲取下標為i頂點的終點
	 * @param ends
	 * @param i
	 * @return
	 */
	private int getEnd(int[] ends, int i) {

		while (ends[i] != 0) {
			i=ends[i];
		}
		return i;
	}
	

	public void print() {
		for (int i = 0; i < vertexs.length; i++) {
			for (int j = 0; j < vertexs.length; j++) {
				System.out.printf("%12d ,", matrix[i][j]);
			}
			System.out.println();
		}
	}

	/** 功能:對邊進行排序處理,氣泡排序
	 * @param edges 邊的集合
	 */
	private void sortEdges(Edata[] edges) {
		for (int i = 0; i < edges.length - 1; i++) {
			for (int j = 0; j < edges.length - 1; j++) {

				if (edges[j].wegiht > edges[j + 1].wegiht) {

					Edata tempEdata = edges[j];
					edges[j] = edges[j + 1];
					edges[j + 1] = tempEdata;

				}

			}

		}
	}
	
	/**
	 * @param ch 頂點的值
	 * @return 返回ch頂點對應的下標,如找不到-1
	 */
	private int getPosition(char ch) {
		
		for (int i = 0; i < vertexs.length; i++) {
			if (vertexs[i]==ch) {
				return i;
			}
		}
		
		return -1;
	}
	
	/** 功能:獲取圖中的邊,放到Edata[]陣列中
	 * @return 
	 */
	private Edata[] getEdges() {
		Edata[] edatas=new Edata[edgeNum];
		int index=0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i+1; j < matrix.length; j++) {
				
				if(matrix[i][j]!=this.INF) {
					
					edatas[index++]=new Edata(vertexs[i], vertexs[j], matrix[i][j]);
				}
			}
		}
		
		return edatas; 
	}
	

}

/**
 * @author snack
 *
 */
class Edata { // 他的對象實例就表示一條邊

	char stat;// 邊的一個"起點"
	char end;// 邊的另外一個"終點"
	int wegiht;// 邊的權值

	public Edata(char stat, char end, int wegiht) {
		this.stat = stat;
		this.end = end;
		this.wegiht = wegiht;
	}

	@Override
	public String toString() {
		return "Edata [stat=" + stat + ", end=" + end + ", wegiht=" + wegiht + "]";
	}

}
