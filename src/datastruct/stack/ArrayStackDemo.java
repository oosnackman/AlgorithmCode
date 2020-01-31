package datastruct.stack;

import java.util.Scanner;

public class ArrayStackDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayStack stack = new ArrayStack(4);

		String key = "";
		boolean loop = true;
		Scanner scanner = new Scanner(System.in);

		while (loop) {

			System.out.println("show:顯示Stack資料");
			System.out.println("exit:退出程式");
			System.out.println("push:表示添加資料到Stack");
			System.out.println("pop:表示從Stack取出資料");

			key = scanner.next();
			switch (key) {
			case "show":
				stack.list();
				break;
			case "push":
				System.out.println("請輸入一個數");
				int reValue = scanner.nextInt();
				stack.push(reValue);
				break;
			case "pop":
				try {
					int result=stack.pop();
					System.out.println("從Stack取出的數值為:"+result);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "exit":
				scanner.close();
				loop = false;
				break;
			default:
				break;
			}

		}

		System.out.println("程式退出成功!!!");

	}

}

class ArrayStack {

	private int maxSize;
	private int[] stack;
	private int top = -1; // 初始化為-1

	public ArrayStack(int maxSize) {

		this.maxSize = maxSize;
		stack = new int[this.maxSize];
	}

	// Stack是否滿
	public boolean isFull() {
		return (this.maxSize - 1) == top;
	}

	// Stack是否為空值
	public boolean isEmpty() {

		return top == -1;
	}

	// 入Stack
	public void push(int value) {

		if (isFull()) {
			System.out.println("Stack值滿");
			return;
		}
		top++;
		stack[top] = value;

	}

	// 出Stack
	public int pop() {

		if (isEmpty()) {
			throw new RuntimeException("Stack為空值,沒有資料");
		}
		int reValue = stack[top];
		top--;

		return reValue;
	}

	public void list() {

		if (isEmpty()) {
			System.out.println("Stack為空值,沒有資料");
			return;
		}

		for (int i = top; i >= 0; i--) {
			System.out.printf("stack[%d]=%d\n", i, stack[i]);
		}

	}

}