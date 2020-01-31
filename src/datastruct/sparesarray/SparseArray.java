package datastruct.sparesarray;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 稀疏矩陣
 * 
 * @author MarkLin
 * @Date:2019年11月7日下午9:41:58
 * @Remarks:
 */

public class SparseArray {

	public static void main(String[] args) {

		int[][] chessArray = new int[11][11];

		chessArray[1][2] = 1;
		chessArray[2][3] = 2;
		chessArray[4][5] = 2;
		chessArray[6][4] = 2;
		System.out.println("--------------原始數據--------------");

		for (int[] rows : chessArray) {

			for (int data : rows) {
				System.out.print("\t" + data);
			}
			System.out.println();
		}

		System.out.println("--------------壓縮--------------");

		int sumValue = 0;
		for (int[] rows : chessArray) {
			for (int data : rows) {
				if (data != 0) {
					sumValue++;
				}
			}
		}

		System.out.println("總數:" + sumValue);

		// 創立SparseArray資料
		int[][] pressData = new int[sumValue + 1][3];

		pressData[0][0] = chessArray.length;
		pressData[0][1] = chessArray[1].length;
		pressData[0][2] = sumValue;

		int count=0;
		for(int row=0;row< chessArray.length;row++) {
			
			for(int col=0;col<chessArray.length;col++) {
		
				if(chessArray[row][col]!=0) {
					//System.out.println("chessArray[row][col]"+row+" , "+col);
					count++;
					pressData[count][0]=row;
					pressData[count][1]=col;
					pressData[count][2]=chessArray[row][col];
				}
			}
		}

		System.out.println("--------------壓縮後--------------");


		for(int i=0;i<pressData.length;i++) {
			
			System.out.printf("%d\t%d\t%d\t\n",pressData[i][0],pressData[i][1],pressData[i][2]);
			
		}
		

		System.out.println("--------------讀取--------------");
		
		
		int[][]  loadArray=new int[pressData[0][0]][pressData[0][1]];
		
		
		for(int i=1;i<pressData.length;i++) {
			
			loadArray[pressData[i][0]][pressData[i][1]]=pressData[i][2];
		
		}
		
		System.out.println("--------------還原後--------------");

		for (int[] rows : loadArray) {

			for (int data : rows) {
				System.out.print("\t" + data);
			}
			System.out.println();
		}
		
	}

}
