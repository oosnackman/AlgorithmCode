package datastruct.tree.threadedbinarytree;

/**
 * Description: 中序線索化
 * 
 * @author MarkLin
 * @Date:2019年11月24日下午9:55:57
 * @Remarks:
 */

public class ThreadedBinaryTreeDemo {

	public static void main(String[] args) {

		// 測試
		HeroNode root = new HeroNode(1, "吳用");
		HeroNode node2 = new HeroNode(3, "林沖");
		HeroNode node3 = new HeroNode(6, "盧俊毅");
		HeroNode node4 = new HeroNode(8, "宋江");
		HeroNode node5 = new HeroNode(10, "關聖");
		HeroNode node6 = new HeroNode(14, "關聖");

		// 手動創建,之後會自動創建
		root.setLeft(node2);
		root.setRight(node3);

		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);

		ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
		threadedBinaryTree.setRoot(root);
		threadedBinaryTree.threadedNodes();

		// 測試10節點

		// 取得前繼節點
		System.out.println("取得10號節點的[前繼:]" + node5.getLeft());
		System.out.println("取得10號節點的[後繼]:" + node5.getRight());
		
		
		threadedBinaryTree.threadedList();
	}

}

class ThreadedBinaryTree {

	private HeroNode root;

	private HeroNode preCursor = null;

	public void setRoot(HeroNode root) {
		this.root = root;
	}

	public void threadedNodes() {
		threadedBinaryTree(this.root);
	}

	// 印出二叉樹轉中序線索化方法
	public void threadedList() {

		HeroNode cursor = root;

		while (cursor != null) {

			// 尋找leftType==1的這個節點
			while (cursor.getLeftType() == 0) {

				cursor = cursor.getLeft();
			}
			
			System.out.println(cursor);

			while (cursor.getRightType() == 1) {
				cursor = cursor.getRight();
				System.out.println(cursor);
			}

			cursor = cursor.getRight();
		}

	}

	// 二叉樹轉中序線索化方法
	public void threadedBinaryTree(HeroNode node) {

		if (node == null) {
			return;
		}
		// 1.先線索化左子樹
		threadedBinaryTree(node.getLeft());

		// 2.先線索化當前節點

		// 前驅處理 , 因第一個左葉子節點的前驅一定是null
		if (node.getLeft() == null) {

			node.setLeft(preCursor);
			node.setLeftType(1);
		}

		// 後繼處理
		if (preCursor != null && preCursor.getRight() == null) {

			preCursor.setRight(node);
			preCursor.setRightType(1);
		}

		preCursor = node;

		// 3.先線索化右子樹
		threadedBinaryTree(node.getRight());
	}

	// 刪除
	public void deleteNode(int no) {
		if (root != null) {

			if (root.getNo() == no) {
				root = null;
			} else {
				root.deleteNode(no);
			}

		} else {
			System.out.println("二叉樹為空,無法遍例");
		}
	}

	// 編寫前序遍例的方法父節點在前面)
	public void preOrder() {

		if (root != null) {
			this.root.preOrder();
		} else {
			System.out.println("二叉樹為空,無法遍例");
		}
	}

	// 編寫中序遍例的方法(父節點在中間)
	public void infixOrder() {
		if (root != null) {
			this.root.infixOrder();
		} else {
			System.out.println("二叉樹為空,無法遍例");
		}
	}

	// 編寫後序遍例的方法(父節點在後面)
	public void postrder() {
		if (root != null) {
			this.root.postrder();
		} else {
			System.out.println("二叉樹為空,無法遍例");
		}
	}

	// 編寫前序遍例的查找
	public HeroNode preOrderSearch(int no) {

		if (root != null) {
			return this.root.preOrderSearch(no);
		} else {
			return null;
		}
	}

	// 編寫中序遍例的查找
	public HeroNode infixOrderSearch(int no) {

		if (root != null) {
			return this.root.inFixOrderSearch(no);
		} else {
			return null;
		}
	}

	// 編寫後序遍例的查找
	public HeroNode postrderSearch(int no) {

		if (root != null) {
			return this.root.postrderSearch(no);
		} else {
			return null;
		}
	}

}

class HeroNode {

	private int no;
	private String name;
	private HeroNode left;
	private HeroNode right;

	// leftType 如果==0 表示指向左子樹 , ==1表示指向前驅節點
	// rightType 如果==0 表示指向右子樹 , ==1表示指向後繼節點
	private int leftType;
	private int rightType;

	public HeroNode(int no, String name) {
		this.no = no;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HeroNode getLeft() {
		return left;
	}

	public void setLeft(HeroNode left) {
		this.left = left;
	}

	public HeroNode getRight() {
		return right;
	}

	public void setRight(HeroNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + "]";
	}

	public void deleteNode(int no) {

		if (this.left != null && this.left.no == no) {
			this.left = null;
			return;
		}
		if (this.right != null && this.right.no == no) {
			this.right = null;
			return;
		}

		if (this.left != null) {
			this.left.deleteNode(no);
		}

		if (this.right != null) {
			this.right.deleteNode(no);
		}

	}

	// 編寫前序遍例的方法父節點在前面)
	public void preOrder() {
		System.out.println(this); // 先輸出父節點

		// 向左子樹遞歸
		if (getLeft() != null) {
			getLeft().preOrder();
		}

		// 向右子樹遞歸
		if (getRight() != null) {
			getRight().preOrder();
		}
	}

	// 編寫前序遍例的查找方法
	public HeroNode preOrderSearch(int no) {
		System.out.println("前序查找*");
		if (this.no == no) {

			return this;
		}

		HeroNode resNode = null;
		// 向左子樹遞歸
		if (this.left != null) {
			resNode = this.left.preOrderSearch(no);
		}

		if (resNode != null) {
			return resNode;
		}

		// 向右子樹遞歸
		if (this.right != null) {
			resNode = this.right.preOrderSearch(no);
		}

		return resNode;

	}

	// 編寫中序遍例的方法(父節點在中間)
	public void infixOrder() {

		// 向左子樹遞歸
		if (getLeft() != null) {
			getLeft().infixOrder();
		}

		System.out.println(this); // 先輸出父節點

		// 向右子樹遞歸
		if (getRight() != null) {
			getRight().infixOrder();
		}

	}

	// 編寫中序遍例的查找方法
	public HeroNode inFixOrderSearch(int no) {

		HeroNode resNode = null;

		// 向左子樹遞歸
		if (this.left != null) {
			resNode = this.left.inFixOrderSearch(no);
		}

		if (resNode != null) {
			return resNode;
		}

		System.out.println("中序查找*");
		if (this.no == no) {

			return this;
		}

		// 向右子樹遞歸
		if (this.right != null) {
			resNode = this.right.inFixOrderSearch(no);
		}

		return resNode;

	}

	// 編寫後序遍例的方法(父節點在後面)
	public void postrder() {

		// 向左子樹遞歸
		if (getLeft() != null) {
			getLeft().postrder();
		}
		// 向右子樹遞歸
		if (getRight() != null) {
			getRight().postrder();
		}
		System.out.println(this); // 先輸出父節點

	}

	// 編寫後序遍例的查找方法
	public HeroNode postrderSearch(int no) {

		HeroNode resNode = null;

		// 向左子樹遞歸
		if (this.left != null) {
			resNode = this.left.postrderSearch(no);
		}

		if (resNode != null) {
			return resNode;
		}

		// 向右子樹遞歸
		if (this.right != null) {
			resNode = this.right.postrderSearch(no);
		}

		if (resNode != null) {
			return resNode;
		}
		System.out.println("後序查找*");
		if (this.no == no) {

			return this;
		}

		return resNode;

	}

	public int getLeftType() {
		return leftType;
	}

	public void setLeftType(int leftType) {
		this.leftType = leftType;
	}

	public int getRightType() {
		return rightType;
	}

	public void setRightType(int rightType) {
		this.rightType = rightType;
	}
}