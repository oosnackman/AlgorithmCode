package datastruct.tree;

/**
 * Description:
 * 
 * @author MarkLin
 * @Date:2019年11月24日下午4:58:37
 * @Remarks:
 */

public class ArrayBinaryTreeDemo {

	public static void main(String[] args) {
		int arr[] = { 1, 2, 3, 4, 5, 6, 7 };

		ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
		arrayBinaryTree.preOrder(); // 1.2.4.5.3.6.7
	}

}

class ArrayBinaryTree {

	private int[] arr = null;

	public ArrayBinaryTree(int[] arr) {
		this.arr = arr;
	}

	public void preOrder() {
		this.preOrder(0);
	}
	/**
	 * Description:
	 * 
	 * @author MarkLin
	 * @Date:2019年11月24日下午5:12:10
	 * @param: index 陣列的下標
	 * @Remarks:
	 */
	public void preOrder(int index) {

		if (arr == null || arr.length == 0) {
			System.out.println("陣列資列為空");

		}

		System.out.println(arr[index]);

		if (2 * index + 1 < arr.length && arr[2 * index + 1] != 0) {
			preOrder(2 * index + 1);
		}
		if (2 * index + 1 < arr.length && arr[2 * index + 2] != 0) {
			preOrder(2 * index + 2);
		}

	}

}