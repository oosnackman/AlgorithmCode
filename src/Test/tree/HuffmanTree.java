package Test.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * Description:
 * 
 * @author MarkLin
 * @Date:2019年12月23日下午11:23:34
 * @Remarks:
 */

public class HuffmanTree {

	public static void main(String[] args) {
		int[] arr = { 13, 7, 8, 3, 29, 6, 1 };
		Node root=createHuffmanTree(arr);
		preOrder(root);
	}

	public static void preOrder(Node root) {

		if (root != null) {
			root.preOrder();
		} else {
			System.out.println("這棵樹是空樹,無法印出");
		}
	}

	public static Node createHuffmanTree(int[] arr) {

		List<Node> nodes = new ArrayList<Node>();

		for (int value : arr) {
			nodes.add(new Node(value));
		}

		while (nodes.size() > 1) {
			Collections.sort(nodes);
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);

			Node parent = new Node(leftNode.value + rightNode.value);

			parent.left = leftNode;
			parent.right = rightNode;

			nodes.remove(leftNode);
			nodes.remove(rightNode);

			nodes.add(parent);
		}

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