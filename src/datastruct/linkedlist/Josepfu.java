package datastruct.linkedlist;

public class Josepfu {

	public static void main(String[] args) {

		CircleSingleLinekedList circleSingleLinekedList = new CircleSingleLinekedList();

		circleSingleLinekedList.addBoy(5);
		System.out.println("-------------");
		circleSingleLinekedList.showBoys();
		System.out.println("-------------");

		circleSingleLinekedList.countBoy(1, 2, 5);

	}
}

///創建一個環形的單項鍊錶
class CircleSingleLinekedList {

	private Boy frist = null;

	// 添加小孩的結點數量,構成一個環形鍊錶
	public void addBoy(int nums) {

		if (nums < 1) {
			System.out.println("nums的值不正確");
		}

		Boy cursor = null;

		for (int i = 1; i <= nums; i++) {

			Boy boy = new Boy(i);

			// 第一個小孩.需自循環
			if (i == 1) {
				frist = boy;
				frist.setNext(frist);
				cursor = frist;
			} else {
				cursor.setNext(boy);
				boy.setNext(frist);
				cursor = boy;
			}
		}
	}

	public void showBoys() {

		if (frist == null) {
			System.out.println("鍊錶為空~");

			return;
		}

		Boy cursor = frist;

		while (true) {

			System.out.printf("第%d小孩\n", cursor.getNo());
			if (cursor.getNext() == frist) {
				break;
			}
			cursor = cursor.getNext();
		}
	}

	/**
	 * @param startNo  表示第幾個小孩開始數
	 * @param countNum 表示數幾下
	 * @param nums     表示多少小孩在圈中
	 */
	public void countBoy(int startNo, int countNum, int nums) {

		if (frist == null || startNo < 1 || countNum > nums) {
			System.out.println("參數輸入有誤, 請重新輸入");
			return;
		}

		Boy cursorfrist = frist;
		Boy cursor = frist;

		// 將輔助指針移動到最後一個
		while (true) {

			if (cursor.getNext() == frist) {
				break;
			}
			cursor = cursor.getNext();
		}

		// 將輔助指針移動到startNo位置
		for (int move = 0; move < (startNo - 1); move++) {

			cursorfrist = cursorfrist.getNext();
			cursor = cursor.getNext();
		}
		while (true) {

			if (cursor == cursorfrist) {
				break;
			}

			// 將輔助指針動到countNum位置
			for (int move = 0; move < (countNum - 1); move++) {
				cursorfrist = cursorfrist.getNext();
				cursor = cursor.getNext();
			}

			System.out.printf("出圈的第%d小孩\n", cursorfrist.getNo());

			cursorfrist = cursorfrist.getNext();
			cursor.setNext(cursorfrist);
		}

		System.out.printf("留在圈內的第%d小孩\n", cursorfrist.getNo());

	}
}

class Boy {

	private int no;
	private Boy next; // 指向下一個節點

	public Boy(int no) {
		this.no = no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Boy getNext() {
		return next;
	}

	public void setNext(Boy next) {
		this.next = next;
	}

}