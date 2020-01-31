package datastruct.tree;

/**
 * Description:
 * 
 * @author MarkLin
 * @Date:2019年11月23日下午2:26:09
 * @Remarks:
 */

public class BinaryTreeDemo {

	public static void main(String[] args) {

		// 二叉樹是單向的

		BinaryTree binaryTree = new BinaryTree();

		HeroNode root = new HeroNode(1, "吳用");
		HeroNode node2 = new HeroNode(2, "林沖");
		HeroNode node3 = new HeroNode(3, "盧俊毅");
		HeroNode node4 = new HeroNode(4, "宋江");
		HeroNode node5 = new HeroNode(5, "關聖");

		// 手動建立
		root.setLeft(node2);
		root.setRight(node3);
		node3.setRight(node4);
		node3.setLeft(node5);
		binaryTree.setRoot(root);

//		// 測試前序遍例
//		System.out.println("測試前序遍例"); // 1.2.3.5.4
//		binaryTree.preOrder();
//		System.out.println("測試中序遍例"); // 2.1.5.3.4
//		// 測試中序遍例
//		binaryTree.infixOrder();
//		System.out.println("測試後序遍例"); // 2.5.4.3.1
//		// 測試後序遍例
//		binaryTree.postrder();

//		//查詢次數4
//		System.out.println("測試前序遍例-查找------------------------------------------"); 
//		HeroNode resNode = binaryTree.preOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("找到了,id=%d,name=%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("沒找到,id=%d", 5);
//		}
//		System.out.println();
//		
//		//查詢次數3
//		System.out.println("測試中序遍例-查找------------------------------------------");
//		resNode=null;
//		resNode  = binaryTree.infixOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("找到了,id=%d,name=%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("沒找到,id=%d", 5);
//		}
//		System.out.println();
//		
//		//查詢次數2
//		System.out.println("測試後序遍例-查找------------------------------------------");
//		resNode=null;
//		 resNode = binaryTree.postrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("找到了,id=%d,name=%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("沒找到,id=%d", 5);
//		}

		// 刪除的規定
		// 1.如果是葉子節點,則刪除該節點
		// 2.如果是非葉子節點,則刪除該子樹
		// 思路:指針需要指向前一個來判斷
		System.out.println("刪除前--");
		binaryTree.preOrder();
		System.out.println("刪除後--");
//		binaryTree.deleteNode(5);
		binaryTree.deleteNode(3);
		binaryTree.preOrder();
	}

}

class BinaryTree {

	private HeroNode root;

	public void setRoot(HeroNode root) {
		this.root = root;
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

}