package datastruct.binarysorttree;

/**
 * 二差排序樹
 * 
 * @author snack
 *
 */
public class BinarySortTreeDemo {

	public static void main(String[] args) {

		int[] arr = { 7, 3, 10, 12, 5, 1, 9, 2 };
		BinarySortTree binarySortTree = new BinarySortTree();

		// 自動加入節點
		for (int i = 0; i < arr.length; i++) {
			binarySortTree.add(new Node(arr[i]));
		}

		// 中序遍歷
		System.out.println("中序遍歷");
		binarySortTree.infixOrder();

		// 測試一下刪除葉子節點
//		binarySortTree.delNode(2);
//		System.out.println("刪除節點後");
//		binarySortTree.infixOrder();

		binarySortTree.delNode(2);
		binarySortTree.delNode(5);
		binarySortTree.delNode(9);
		binarySortTree.delNode(12);
		binarySortTree.delNode(7);
		binarySortTree.delNode(3);
		binarySortTree.delNode(10);
		binarySortTree.delNode(1);
		System.out.println("刪除節點後");
		binarySortTree.infixOrder();

	}

}

class BinarySortTree {

	private Node root;

	public void add(Node node) {
		if (root == null) {
			root = node;
		} else {
			root.add(node);
		}
	}

	public void infixOrder() {
		if (root != null) {
			root.infixOrder();
		} else {
			System.out.println("二叉排序樹為空");
		}
	}

	// 要查找的刪除的節點
	public Node search(int value) {
		if (root == null) {
			return null;
		} else {

			return root.search(value);
		}
	}

	// 要查找的父節點
	public Node searchParent(int value) {
		if (root == null) {
			return null;
		} else {

			return root.searchParent(value);
		}
	}

	/**
	 * @param node 傳入的節點,(當作二差排序樹的根集點)
	 * @return 返回的為根節點的二差排序樹的最小節點
	 */
	public int delRightTreeMin(Node node) {
		Node target = node;

		// 一直循環查找佐子節點 就會找到最小值
		while (target.left != null) {
			target = target.left;
		}

		delNode(target.value);
		return target.value;
	}

	// 要刪除的節點
	public void delNode(int value) {

		if (root == null) {
			return;
		} else {

			Node targetNode = search(value);

			// 沒有要刪除的目標節點
			if (targetNode == null) {
				return;
			}

			// 查到二差排序樹只有一顆節點
			if (root.left == null && root.right == null) {
				root = null;
				return;
			}

			Node parentNode = searchParent(value);

			// 總共有三種情況的刪除!
			if (targetNode.left == null && targetNode.right == null) { // 第一種情況:刪除葉子節點,判斷是否是葉子節點

				if (parentNode.left != null && parentNode.left.value == value) {

					parentNode.left = null;
				} else if ((parentNode.right != null && parentNode.right.value == value)) {
					parentNode.right = null;
				}
			} else if (targetNode.left != null && targetNode.right != null) { // 第二種情況:刪除有兩顆子樹的節點

				int minValue = delRightTreeMin(targetNode.right);

				targetNode.value = minValue;

			} else { // 第三種情況:刪除只有一顆子樹的節點

				if (targetNode.left != null) {

					if (parentNode != null) {
						if (parentNode.left == targetNode) {
							parentNode.left = targetNode.left;
						} else {
							parentNode.right = targetNode.left;
						}
					} else {
						root = targetNode.left;
					}
				} else if (targetNode.right != null) {
					
					if (parentNode != null) {
						if (parentNode.left == targetNode) {
							parentNode.left = targetNode.right;
						} else {
							parentNode.right = targetNode.right;
						}
					} else {
						root = targetNode.right;
					}
				}

			}

		}

	}

	public Node getRoot() {
		return root;
	}

}

class Node {

	int value;
	Node left;
	Node right;

	public Node(int value) {
		this.value = value;
	}

	// 查找要刪除的節點
	/**
	 * @param value 希望刪除節點的值
	 * @return 如我找到返回該節點 ,否則返回null
	 */
	public Node search(int value) {
		if (this.value == value) {
			return this;
		} else if (value < this.value) {

			if (this.left == null) {
				return null;
			}

			return this.left.search(value);

		} else {

			if (this.right == null) {
				return null;
			}

			return this.right.search(value);
		}

	}

	// 查找節點的父節點
	public Node searchParent(int value) {

		// 滿足此條件就是找到父節點
		if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
			return this;
		} else {
			if (this.left != null && value < this.value) {
				return this.left.searchParent(value);
			} else if (this.right != null && value >= this.value) { // 以防有相同的值
				return this.right.searchParent(value);
			} else {
				return null;// 沒有父節點
			}
		}

	}

	public void add(Node node) {

		if (node == null) {
			return;
		}

		if (node.value < this.value) {
			if (this.left == null) {
				this.left = node;
			} else {
				// 向左子樹遞歸
				this.left.add(node);
			}

		} else {

			if (this.right == null) {
				this.right = node;
			} else {
				// 向右子樹遞歸
				this.right.add(node);
			}

		}
	}

	public void infixOrder() {

		if (this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if (this.right != null) {
			this.right.infixOrder();
		}

	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}
}