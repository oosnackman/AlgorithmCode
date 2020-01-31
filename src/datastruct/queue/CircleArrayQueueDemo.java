package datastruct.queue;

import java.util.Scanner;

/**
 * Description:
 * 
 * @author MarkLin
 * @Date:2019年11月10日下午3:46:59
 * @Remarks:
 */

public class CircleArrayQueueDemo {

	public static void main(String[] args) {
	
		CircleArrayQueue queue = new CircleArrayQueue(4);

		char key = ' ';
		Scanner scanner = new Scanner(System.in);

		boolean loop = true;

		while (loop) {

			System.out.println("s(show): 顯示對列");
			System.out.println("e(exit): 離開程式");
			System.out.println("a(add): 添加資料到對列");
			System.out.println("g(get): 獲取對列的資料");
			System.out.println("h(head): 查看對列頭的資料");
			key = scanner.next().charAt(0);

			switch (key) {

			case 's':
				queue.showQueue();
				break;
			case 'a':
				System.out.println("輸入資料");
				int value = scanner.nextInt();
				queue.addQueue(value);
				break;
			case 'g':
				try {
					int resValue = queue.getQueue();
					System.out.printf("取出的資料%d", resValue);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'h':

				try {
					int resValue = queue.headQueue();
					;
					System.out.printf("對列頭的資料:%d", resValue);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'e':
				scanner.close();
				loop = false;
				break;
			default:
				break;
			}

		}

		System.out.println("程式退出");
	}
}

//陣列模擬對列
class CircleArrayQueue {

	private int maxSize; // 表示對列的最大容量
	private int rear;
	private int font;
	private int[] arr; // 模擬對列,用於存儲資料

	public CircleArrayQueue(int maxSize) {
		this.maxSize = maxSize;
		this.arr = new int[maxSize];

	}

	// 判斷對列是否為滿
	public boolean isFull() {

		return (rear + 1) % maxSize == font;
	}

	// 判斷對列是否為空
	public boolean isEmpty() {
		return this.rear == this.font;
	}

	// 增加資料到對列
	public void addQueue(int num) {

		if (isFull()) {
			System.out.println("資料已滿,無法在增加");
			return;
		}

		arr[rear] = num;

		rear = (rear + 1) % maxSize;
	}

	// 獲取對列的數據
	public int getQueue() {

		if (isEmpty()) {
			throw new RuntimeException("對列為空不能取資料");
		}

		int reValue = arr[font];

		font = (font + 1) % maxSize;
		return reValue;
	}

	// 顯示所有對列的資料
	public void showQueue() {

		if (isEmpty()) {
			System.out.println("對列為空,沒有資料~");
			return;
		}

		for (int i = font; i < font + size(); i++) {

			System.out.printf("att[%d]=%d\n", i % maxSize, arr[i % maxSize]);
		}
		System.out.println();

	}

	private int size() {
		return (rear + maxSize - font) % maxSize;
	}

	public int headQueue() {
		if (isEmpty()) {
			throw new RuntimeException("對列為空,沒有資料~");
		}
		return arr[font];

	}
}