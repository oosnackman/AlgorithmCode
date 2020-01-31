package datastruct.avl;


/**
 * 平衡二插樹 AVL樹
 * 
 * @author snack
 *
 */
public class AVLTreeDemo {

	public static void main(String[] args) {


		
//		int[] arr = { 4, 3, 6, 5, 7, 8 };
//		int[] arr = { 10, 12 ,8, 9, 7, 6 };
		int[] arr = { 10, 11 ,8, 9, 7, 6 };
		AVLTree avlTree = new AVLTree();
		
		// 自動加入節點
		for (int i = 0; i < arr.length; i++) {
			avlTree.add(new Node(arr[i]));
		}
		
		System.out.println("中序");
		avlTree.infixOrder();
		
		
		System.out.println("在平衡處理後情況");
		System.out.println("樹高:"+avlTree.getRoot().height());
		System.out.println("樹左子樹:"+avlTree.getRoot().leftHeight());
		System.out.println("樹右子樹:"+avlTree.getRoot().rightHeight());
		System.out.println("當前的根節點="+avlTree.getRoot());
		System.out.println("根節點的左子節點="+avlTree.getRoot().left);
	}

}

class AVLTree {

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

	// 返回左子樹的高度
	public int leftHeight() {
		if (this.left == null) {
			return 0;
		}
		return this.left.height();
	}

	// 返回右子樹的高度
	public int rightHeight() {
		if (this.right == null) {
			return 0;
		}
		return this.right.height();
	}

	// 返回以該節點為根節點的這個樹的高度
	public int height() {
		return Math.max(this.left == null ? 0 : this.left.height(), this.right == null ? 0 : this.right.height()) + 1;
	}

	
	//左旋轉的方法
	private void leftRotata() {
		
		//1.創立新的節點
		Node newNode=new Node(this.value);
		
		//2.把新的節點左子樹,設置當前節點的左子樹
		newNode.left=this.left;
		
		//3.把新的節點右子樹,設置當前節點的右子樹的左子樹
		newNode.right=this.right.left;
		
		//4.把當前節點的值改為右子節點的值
		this.value=this.right.value;
		
		//5.把當前節點的右子樹設置成右子樹的右子樹
		this.right=this.right.right;
		
		//6.把當前節點的左子樹設置為新節點
		this.left=newNode;
		
	}
	
	//右旋轉的方法
	private void  rightRotata() {
		
		//1.創立新的節點
		Node newNode=new Node(this.value);
		
		newNode.right=this.right;
		
		newNode.left=this.left.right;
		
		this.value=this.left.value;
		
		this.left=this.left.left;
		
		this.right=newNode;
		
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
		
		// 當添加完一個結點後,如果(右子樹的高度-左子樹的高度)>1,就要左旋轉即可
		if (rightHeight() - leftHeight() > 1) {

			// 如果他的右子樹的左子樹大於他的右子樹高度
			if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
				// 先對當前節點的右子樹->右旋轉
				this.right.rightRotata();
				// 在對當前節點的左旋轉
				leftRotata();
			} else {
				leftRotata();
			}
			
			return ;
		}

		// 向右旋轉
		if (leftHeight() - rightHeight() > 1) {

			// 如果他的左子樹的右子樹大於他的左子樹高度
			if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
				// 先對當前節點的左子樹->左旋轉
				this.left.leftRotata();
				// 在對當前節點的右旋轉
				rightRotata();
			} else {
				rightRotata();
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