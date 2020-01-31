package datastruct.linkedlist;

import java.util.Stack;

/**
 * Description:單鍊錶
 * 
 * @author MarkLin
 * @Date:2019年11月10日下午7:17:48
 * @Remarks:
 */

public class DoubleLinkedListDemo {

	public static void main(String[] args) {

		System.out.println("雙向鍊錶的測試");

		HeroNode2 hero1 = new HeroNode2(1, "柯文哲", "無敵蜜蜂");
		HeroNode2 hero2 = new HeroNode2(2, "馬英九", "超級帥哥");
		HeroNode2 hero3 = new HeroNode2(3, "韓國瑜", "辯論高手");
		HeroNode2 hero4 = new HeroNode2(4, "陳水扁", "海角7億");
		HeroNode2 hero5 = new HeroNode2(5, "呂秀蓮", "綠島小夜曲");
		HeroNode2 hero6 = new HeroNode2(6, "朱立倫", "做好做滿");

		DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

		System.out.println("------------------------------");
		
		doubleLinkedList.add(hero1);
		doubleLinkedList.add(hero2);
		doubleLinkedList.add(hero3);
		doubleLinkedList.add(hero4);
		doubleLinkedList.add(hero5);
		doubleLinkedList.add(hero6);

		doubleLinkedList.showLineked();

		System.out.println("------------------------------");

		HeroNode2 newhero2 = new HeroNode2(2, "馬英九2.0", "超級帥哥2.0");
		doubleLinkedList.upadate(newhero2);
		doubleLinkedList.showLineked();

		System.out.println("------------------------------");
		
		doubleLinkedList.delete(2);
		doubleLinkedList.showLineked();
	}

}

class DoubleLinkedList {

	// 初始化一個頭節點
	private HeroNode2 head = new HeroNode2(0, "", "");

	public HeroNode2 getHead() {
		return head;
	}

	public void setHead(HeroNode2 head) {
		this.head = head;
	}

	// 跑雙向鍊錶
	public void showLineked() {

		if (head.next == null) {
			System.out.println("鍊錶為空");
			return;
		}

		HeroNode2 cursor = head.next;

		while (true) {

			if (cursor == null) {
				break;
			}

			System.out.println(cursor);
			cursor = cursor.next;
		}

	}

	/**
	 * 添加雙向鍊錶的最後
	 * 
	 * @param heroNode
	 */
	public void add(HeroNode2 heroNode) {

		HeroNode2 cursor = head;

		while (true) {
			if (cursor.next == null) {
				break;
			}

			cursor = cursor.next;
		}

		// 雙向鍊錶
		cursor.next = heroNode;
		heroNode.pre = cursor;

	}

	// 單雙向鍊錶修改方式是一樣的
	public void upadate(HeroNode2 heroNode) {

		if (head.next == null) {
			System.out.println("列表為空");
			return;
		}

		HeroNode2 cursor = head.next;
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

		HeroNode2 cursor = head.next;
		boolean flag = false;

		while (true) {

			if (cursor == null) {
				break;
			}

			if (cursor.no == no) {
				flag = true;
				break;
			}

			cursor = cursor.next;
		}

		if (flag) {
			cursor.pre.next = cursor.next;

			// 如刪除最後一個節點,不需要刪除,否怎會出現空指針異常
			if (cursor.next != null) {
				cursor.next.pre = cursor.pre;
			}
		} else {
			System.out.printf("沒有找到編號%d的節點,無法刪除\n", no);
		}

	}
}

class HeroNode2 {

	public int no;
	public String name;
	public String nickname;
	public HeroNode2 next; // 指向下一個節點
	public HeroNode2 pre; // 指向前一個節點

	public HeroNode2(int no, String name, String nickname) {

		this.no = no;
		this.name = name;
		this.nickname = nickname;

	}

	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}

}