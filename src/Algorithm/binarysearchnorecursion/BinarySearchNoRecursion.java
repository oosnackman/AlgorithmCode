package Algorithm.binarysearchnorecursion;

/**
 * 2分查找非遞歸
 * 
 * @author snack
 *
 */
public class BinarySearchNoRecursion {

	
	public static void main(String[] args) {

		int[] arr = { 1, 3, 8, 10, 11, 67, 100 };
		int index = binarySearch(arr, 13);

		System.out.println("index=" + index);
	}

	/**
	 * @param arr    待查找的陣列
	 * @param target 需要查找的數
	 * @return 返回-1找不到
	 */
	public static int binarySearch(int[] arr, int target) {

		int left = 0;
		int right = arr.length - 1;

		while (left <= right) {
			System.out.println("搜尋處理中...");
//			int mid = (left + right) / 2;
			int mid = (left + right) >>> 1 ;
			if (arr[mid] == target) {

				return mid;
			} else if (arr[mid] > target) {
				right = mid - 1;
			} else
				left = mid + 1;
			
			System.out.println(left+":"+right);
		}

		return left;

	}

}
