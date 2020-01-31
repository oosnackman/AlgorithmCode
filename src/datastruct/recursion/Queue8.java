package datastruct.recursion;

/**
 * 八皇后
 * 
 * @author snack
 *
 */
public class Queue8 {

	// 先定義一個max表示共有多少個皇后
	/*
	 * 1. array[]={0,4,7,5,2,6,1,3} =>表示行
	 * 2.       i= 0,1,2,3,4,5,6,7 =>表示列
	 * 1.2表示代表真正的座標位置
	 */
	int max = 8;
	int[] array = new int[max];
	static int count=0;
	static int judgecount=0;

	public static void main(String[] args) {

		
		Queue8 queue8 = new Queue8();
		queue8.check(0);
	System.out.println("多少解法:"+count);
	System.out.println("一共呼叫幾次:"+judgecount);
	}
	
	
	
	
    //放第幾個皇后
	private void check(int n) {
		
		if(n==max) {
			count++;
			print();
			return;
		}
		for(int i=0;i<max;i++) {
			
			//放置第n個皇后放到i列
			array[n]=i;
			
			if(judge(n)) {
				check(n+1);
			}
		}
		
		
	}
	
	// 查看當我們放置第N個皇后,就去檢測該皇后是否和前面已經擺放的黃後衝突
	/**
	 * @param n 表示第n個皇后 ,也代表"第幾行"
	 * @return true:表示沒有衝突　,false:表示有衝突
	 */
	private boolean judge(int n) {
		judgecount++;
		for (int i = 0; i < n; i++) {
			
			//1.array[i]==array[n] 錶是判斷第n個皇后是否和前面n-1個皇后在同一列
			//2. 錶是判斷第n個皇后是否第i個皇后 在同一個斜線(兩點可求斜率)
			if(array[i]==array[n]||Math.abs(n-i)==Math.abs(array[n]-array[i])) {
				return false;
			}

		}
		return true; 
	}

	// 將皇后擺放的位置輸出
	private void print() {

		for (int i = 0; i < array.length; i++) {

			System.out.print(array[i] + " ");
		}
		System.out.println();

	}
}
