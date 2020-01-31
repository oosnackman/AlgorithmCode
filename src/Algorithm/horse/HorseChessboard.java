package Algorithm.horse;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;

import org.junit.runner.manipulation.Sortable;

public class HorseChessboard {

	private static int X; //棋盤的列數
	private static int Y; //棋盤的行數
	
	//各個棋盤的位置是否被訪問過
	private static boolean visited[];
	
	//標記是否期盤的所有位置都被訪問過
	private static boolean finished;
	
	public static void main(String[] args) {

		
		//測試
		X=8;
		Y=8;
		int row=2;
		int column=4;
		int[][] chessboard = new int[X][Y];
		visited = new boolean[X * Y]; //初始值都是false;
		
		
		long start = System.currentTimeMillis();
		traversalChessboard(chessboard, row - 1, column - 1, 1);
		long end = System.currentTimeMillis();
		System.out.println("共耗時:  " + (end - start) + " 毫秒");
		
		
		for(int[] rows : chessboard) {
			for(int step: rows) {
				System.out.print(step + "\t");
			}
			System.out.println();
		}
		
	}
	
	/**
	 * 
	 *   完成騎士周遊問題的算法
	 * @param chessboard 棋盤
	 * @param row 馬兒當前的位置的列
	 * @param column 馬兒當前的位置的行
	 * @param step 是第幾步,初始位置就是第一步
	 */
	public static void traversalChessboard(int[][] chessboard,int row,int column,int step) {
		chessboard[row][column]=step;
		visited[row*X+column]=true; //公式,可以看圖即可驗證
		
		ArrayList<Point> ps = next(new Point(column, row));
		
		//貪心算法
		sort(ps);
		
		while (!ps.isEmpty()) {
			Point p = ps.remove(0); //取出下一個可以走的位置
			
			//判斷該點是否訪問過
			if(!visited[p.y*X+p.x]) {
				
				traversalChessboard(chessboard, p.y, p.x, step+1);
			}
		}
		
		
		//判斷馬兒是否完成了任務,使用step和應該走的步數比較
		//如還沒達到數量,則表示沒有玩成任務,將整個棋盤置0
		if(step<X*Y && !finished) {
			chessboard[row][column]=0;
			visited[row*X+column]=false;
		}else {
			finished=true;
		}
	}
	
	
	/**
	 * 
	 *  功能:計算馬兒還能走哪些位置,並放入到一個集合中,最多有八個位置
	 * @param curPoint
	 * @return
	 */
	public static ArrayList<Point> next(Point curPoint){
		
		ArrayList<Point> ps = new ArrayList<Point>();
		
		Point p1=new Point();
		if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y -1) >= 0) {
			ps.add(new Point(p1));
		}
		if((p1.x = curPoint.x - 1) >=0 && (p1.y=curPoint.y-2)>=0) {
			ps.add(new Point(p1));
		}
		if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
			ps.add(new Point(p1));
		}
		if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
			ps.add(new Point(p1));
		}
		if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
			ps.add(new Point(p1));
		}
		if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
			ps.add(new Point(p1));
		}
		if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
			ps.add(new Point(p1));
		}
		if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
			ps.add(new Point(p1));
		}
		return ps;
	}
	
	//根據當前這一步的所有的下一步的選擇位置,進行非遞減排序
	public static void sort(ArrayList<Point> ps) {
		
		ps.sort(new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {

				int count1=next(o1).size();
				int count2=next(o2).size();
				
				if(count1<count2) {
					return -1;
				}else if(count1==count2) {
					return 0;
				}else {
					return 1;
				}
				
			}
			
			
		});
	}

}
