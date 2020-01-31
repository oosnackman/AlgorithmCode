package datastruct.linkedlist;

import java.util.Stack;

/**
 * Description:單鍊錶
 * 
 * @author MarkLin
 * @Date:2019年11月10日下午7:17:48
 * @Remarks:
 */

public class SingLinkedListDemo {

	public static void main(String[] args) {

		HeroNode hero1 = new HeroNode(1, "柯文哲", "無敵蜜蜂");
		HeroNode hero2 = new HeroNode(2, "馬英九", "超級帥哥");
		HeroNode hero3 = new HeroNode(3, "韓國瑜", "辯論高手");
		HeroNode hero4 = new HeroNode(4, "陳水扁", "海角7億");
		HeroNode hero5 = new HeroNode(5, "呂秀蓮", "綠島小夜曲");
		HeroNode hero6 = new HeroNode(6, "朱立倫", "做好做滿");

		SingLinkedList singLinkedList = new SingLinkedList();

		// 加入
//		singLinkedList.add(hero1);
//		singLinkedList.add(hero2);
//		singLinkedList.add(hero3);
//		singLinkedList.add(hero4);

		// 加入安照順序
		singLinkedList.addByOder(hero1);
		singLinkedList.addByOder(hero4);
		singLinkedList.addByOder(hero2);
		singLinkedList.addByOder(hero3);
		singLinkedList.addByOder(hero3);
		singLinkedList.addByOder(hero4);
		singLinkedList.addByOder(hero5);
		singLinkedList.addByOder(hero6);

		singLinkedList.showLineked();

		HeroNode newNero2 = new HeroNode(10, "馬英九2.0", "超級老帥哥");
		singLinkedList.upadate(newNero2);
		System.out.println("修改後的鍊錶情況");
		singLinkedList.showLineked();

		// 刪除一個節點
//		System.out.println("刪除後的鍊錶情況");
//		singLinkedList.delete(1);
//	    singLinkedList.delete(2);
//		singLinkedList.delete(3);
//		singLinkedList.delete(4);
//		singLinkedList.showLineked();

		// 測試一一下 球單鍊錶的有效個數
		System.out.println("測試一一下 球單鍊錶的有效個數:" + getLength(singLinkedList.getHead()));

		// 倒數第K個節點
		System.out.println("倒數第K個節點:" + getLastName(singLinkedList.getHead(), 2));

//		System.out.println("反轉..");
//		reversetList(singLinkedList.getHead());
//		singLinkedList.showLineked();
		
		
		System.out.println("反向打印單列錶");
		reversePrint(singLinkedList.getHead());
	}

	/**
	 * 面試題目練習 有效個數
	 * 
	 * @param heroNode
	 * @return
	 */
	public static int getLength(HeroNode heroNode) {
		if (heroNode.next == null) {
			return 0;
		}

		HeroNode cursor = heroNode.next;

		int sum = 0;
		while (true) {

			if (cursor == null) {
				break;
			}
			sum++;
			cursor = cursor.next;

		}
		return sum;

	}

	/**
	 * 面試題目練習 倒數第幾個元素
	 * 
	 * @param heroNode
	 * @return
	 */
	public static HeroNode getLastName(HeroNode heroNode, int num) {

		if (heroNode.next == null) {
			return null;
		}

		HeroNode cursor = heroNode;

		int temp = 0;

		while (true) {

			if (cursor.next == null) {

				break;
			}

			cursor = cursor.next;
			temp++;
		}

		if (0 >= num || num > temp) {
			return null;
		}

		cursor = heroNode.next;

		int sum = 0;
		while (true) {

			if ((temp - num) == sum) {

				break;
			}
			cursor = cursor.next;
			sum++;
		}

		return cursor;
	}

	/**
	 * 面試題目！很難卡很久.... 反轉
	 * 
	 * @param head
	 */
	public static void reversetList(HeroNode head) {

		if (head.next == null || head.next.next == null) {
			return;
		}

		HeroNode reversHead = new HeroNode(0, "", "");
		HeroNode cursor = head.next;
		HeroNode next = null;

		while (cursor != null) {

			next = cursor.next;

			cursor.next = reversHead.next; // 指針指向尾巴
			reversHead.next = cursor; // 將頭連結起來

			cursor = next;
		}

		head.next = reversHead.next;

	}

	/** 面試題目
	 *  反向印出結點資料
	 * @param head
	 */
	public static void reversePrint(HeroNode head) {

		if (head.next == null) {
			return;
		}

		HeroNode cursor = head.next;
		Stack<HeroNode> stack = new Stack<HeroNode>();

		while (cursor != null) {
			stack.push(cursor);
			cursor = cursor.next;
		}

		while (stack.size() > 0) {
			System.out.println(stack.pop());
		}

	}
}

class SingLinkedList {
	//初始化一個頭節點
	private HeroNode head = new HeroNode(0, "", "");

	public void add(HeroNode heroNode) {

		HeroNode cursor = head;

		while (true) {
			if (cursor.next == null) {
				break;
			}
			cursor = cursor.next;
		}

		cursor.next = heroNode;

	}

	public void addByOder(HeroNode heroNode) {

		HeroNode cursor = head;

		boolean flag = false;

		while (true) {

			if (cursor.next == null) {
				break;
			}
			if (cursor.next.no > heroNode.no) {
				break;
			}

			if (cursor.next.no == heroNode.no) {
				flag = true;
				break;
			}
			cursor = cursor.next;

		}

		if (flag) {
			System.out.printf("準備插入的編號%d,已經存在無法在加入\n", heroNode.no);
		} else {

			heroNode.next = cursor.next;
			cursor.next = heroNode;
		}

	}

	public void upadate(HeroNode heroNode) {

		if (head.next == null) {
			System.out.println("列表為空");
			return;
		}

		HeroNode cursor = head.next;
		boolean flag = false;
		while (true) {

			if (cursor == null) {
				break;
			}
			if (cursor.no == heroNode.no) {
				flag = true;
				break;
			}

			cursor = cursor.next;
		}

		if (flag) {
			cursor.name = heroNode.name;
			cursor.nickname = heroNode.nickname;
		} else {
			System.out.printf("沒有找到編號%d的節點,無法修改\n", heroNode.no);
		}
	}

	public void delete(int no) {

		if (head.next == null) {
			System.out.println("列表為空");
			return;
		}

		HeroNode cursor = head;
		boolean flag = false;

		while (true) {

			if (cursor == null) {
				break;
			}

			if (cursor.next.no == no) {
				flag = true;
				break;
			}

			cursor = cursor.next;
		}

		if (flag) {
			cursor.next = cursor.next.next;

		} else {
			System.out.printf("沒有找到編號%d的節點,無法刪除\n", no);
		}

	}

	public void showLineked() {

		if (head.next == null) {
			System.out.println("鍊錶為空");
			return;
		}

		HeroNode cursor = head.next;

		while (true) {

			if (cursor == null) {
				break;
			}

			System.out.println(cursor);
			cursor = cursor.next;
		}

	}

	public HeroNode getHead() {
		return head;
	}

	public void setHead(HeroNode head) {
		this.head = head;
	}

}

class HeroNode {

	public int no;
	public String name;
	public String nickname;
	public HeroNode next;

	public HeroNode(int no, String name, String nickname) {

		this.no = no;
		this.name = name;
		this.nickname = nickname;

	}

	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}

}