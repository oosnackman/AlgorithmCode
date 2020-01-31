package datastruct.recursion;

/**
 * 地回迷宮
 * 
 * @author snack
 *
 */
public class MiGong {

	public static void main(String[] args) {

		// 使用[1]表示為牆壁

		int[][] map = new int[8][7];

		// 上下左右牆壁
		for (int i = 0; i < 7; i++) {
			map[0][i] = 1;
			map[7][i] = 1;
		}
		for (int i = 0; i < 8; i++) {
			map[i][0] = 1;
			map[i][6] = 1;
		}
		map[3][1] = 1;
		map[3][2] = 1;
		//檔板開關-打開的話 ,會沒有路走 ,會出現3
//		map[1][2] = 1;
//		map[2][2] = 1;

		System.out.println("-----------------------地圖的情況-----------------------");
		for (int i = 0; i < 8; i++) {

			for (int j = 0; j < 7; j++) {

				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
			System.out.println();
		}

		setWay(map, 1, 1);
		System.out.println("-----------------------地圖的情況-----------------------");
		for (int i = 0; i < 8; i++) {

			for (int j = 0; j < 7; j++) {

				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
			System.out.println();
		}
	}

	// 使用遞迴來給小球找路
	/*
	 * 概念 1.i,j 錶是從地圖位置出發點(1.1) 2.如果小球能到[6][5]位置,則找到終點位置
	 * 3.走迷宮時候,需要確定一個策略(走法)下->右->上->左
	 * 約定:當map[i][j]為0表示該點沒有走過,當為1表示為牆;2表示道路可以走;3表示該點已經走過(不能在走了)
	 * 
	 * 
	 */

	/**
	 * @param map 表示地圖
	 * @param i   從哪個位置開始找
	 * @param j
	 * @return 如果找到路找到道路就返回true ,否則返回false
	 */
	public static boolean setWay(int[][] map, int i, int j) {
		if (map[6][5] == 2) {//找到路了
			return true; 
		} else if (map[i][j] == 0) {
			
			map[i][j] = 2; //假定可以走通
			//下->右->上->左
			if (setWay(map, i + 1, j)) {
				return true;
			} else if (setWay(map, i, j + 1)) {
				return true;
			} else if (setWay(map, i - 1, j)) {
				return true;
			} else if (setWay(map, i, j - 1)) {
				return true;
			}else {
				//上下左右都無法的話
				
				map[i][j] = 3;
				return false;
			}
		} else {
			return false;
		}
	}
}
