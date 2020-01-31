package datastruct.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

	public static void main(String[] args) {

		int[] arr = { 13, 7, 8, 3, 29, 6, 1 };
		Node root=createHuffmanTree(arr);
		preOrder(root);

	}

	public static void preOrder(Node root) {

		if (root!=null) {
			root.preOrder();
		}else {
			System.out.println("這棵樹是空樹,無法印出");
		}
	}

	/**
	 * @param arr 需創建HuffmanTree的陣列
	 * @return 返回創立好HuffmanTree的節點
	 */
	public static Node createHuffmanTree(int[] arr) {

		List<Node> nodes = new ArrayList<Node>();

		for (int value : arr) {
			nodes.add(new Node(value));
		}

		// 處理1-5循環

		while (nodes.size() > 1) {

			// 排序從小到大
			Collections.sort(nodes);
			System.out.println("nodes:" + nodes);

			// 1.取最小的節點(二叉樹)
			Node leftNode = nodes.get(0);
			// 2.取第二小的節點(二叉樹)
			Node rightNode = nodes.get(1);

			// 3.建立一個新的二叉樹(1+2)
			Node parent = new Node(leftNode.value + rightNode.value);
			parent.left = leftNode;
			parent.right = rightNode;

			// 4.刪除
			nodes.remove(leftNode);
			nodes.remove(rightNode);

			// 5.加入
			nodes.add(parent);

		}

		// 返回root節點即可
		return nodes.get(0);
	}

}

//節點類
class Node implements Comparable<Node> {

	int value; // 節值
	Node left; // 指向左子節點
	Node right;// 指向右子節點

	public void preOrder() {
		System.out.println(this);

		if (this.left != null) {
			this.left.preOrder();
		}

		if (this.right != null) {
			this.right.preOrder();
		}

	}

	public Node(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	@Override
	public int compareTo(Node o) {
		// 從小到大排序
		return this.value - o.value;
	}

}