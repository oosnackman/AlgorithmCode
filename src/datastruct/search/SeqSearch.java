package datastruct.search;

/**
 * 線性查找
 * 
 * @author snack
 *
 */
public class SeqSearch {

	public static void main(String[] args) {

		int[] arr = { 1, 9, 11, -1, 34, 89 };// 無序
		
		int index=seqSearch(arr,11);
		if (index==-1) {
			System.out.println("查無資料");
		} else {
			System.out.println("找到,下標為="+index);
		}
	}

	public static int seqSearch(int[] arr, int value) {

		// 找到值就返回下標
		for (int i = 0; i < arr.length; i++) {

			if (arr[i] == value) {
				return i;
			}
		}

		return -1;

	}
}
