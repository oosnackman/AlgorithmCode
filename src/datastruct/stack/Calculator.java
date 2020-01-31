package datastruct.stack;

/**前序表達式
 * @author snack
 *
 */
public class Calculator {

	public static void main(String[] args) {

		String expression = "70+2*6-4";

		// 需要符號Stack,數字Stack 01 12 23
		ArrayStack2 numberStack = new ArrayStack2(10);
		ArrayStack2 operStack = new ArrayStack2(10);

		int index = 0; // 用於掃描
		int num1 = 0;
		int num2 = 0;
		int oper = 0;
		int res = 0;
		char ch = ' ';
		String keeNum = "";

		while (true) {

			ch = expression.substring(index, index + 1).charAt(0);

			// 判斷是否符號Stack
			if (operStack.isOper(ch)) {

				if (!operStack.isEmpty()) {

					// 比較優先級
					if (operStack.priority(ch) > operStack.priority(operStack.peek())) {
						operStack.push(ch);
					} else {
						num1 = numberStack.pop();
						num2 = numberStack.pop();
						oper = operStack.pop();
						res = numberStack.cal(num1, num2, oper);

						numberStack.push(res);
						operStack.push(ch);
					}

				} else {
					operStack.push(ch);
				}

			} else {
				// 如果是數字,則直接入數Stack
				// numberStack.push(ch-48); //ASCII碼
				keeNum += ch;

				if (index == expression.length() - 1) {
					numberStack.push(Integer.parseInt(keeNum));
				} else {
					if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
						numberStack.push(Integer.parseInt(keeNum));
						keeNum = "";
					}
				}

			}

			index++;
			if (index >= expression.length()) {
				break;
			}

		}

		while (true) {

			if (operStack.isEmpty()) {
				break;
			}
			num1 = numberStack.pop();
			num2 = numberStack.pop();
			oper = operStack.pop();
			res = operStack.cal(num1, num2, oper);
			numberStack.push(res);
		}

		System.out.printf("表達式%s=%d", expression, numberStack.pop());

	}

}

class ArrayStack2 {

	private int maxSize;
	private int[] stack;
	private int top = -1; // 初始化為-1

	public ArrayStack2(int maxSize) {

		this.maxSize = maxSize;
		stack = new int[this.maxSize];
	}

	// 偷看當前Stack頂的值,但沒pop
	public int peek() {

		return stack[top];
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

	// 返回運算服的優先級,由我們自定 ,由數字類表示
	// 假設目前的表達式只有+,-,*,/
	public int priority(int oper) {
		if (oper == '*' || oper == '/') {
			return 1;
		} else if (oper == '+' || oper == '-') {
			return 0;
		} else {
			return -1;
		}
	}

	// 判斷是不是一個運算符號
	public boolean isOper(char val) {
		return val == '*' || val == '/' || val == '+' || val == '-';
	}

	// 計算方法 注意:int,char可以互相比較使用
	public int cal(int num1, int num2, int oper) {
		int reValue = 0;

		switch (oper) {
		case '*':
			reValue = num2 * num1;
			break;
		case '/':
			reValue = num2 / num1;
			break;
		case '+':
			reValue = num2 + num1;
			break;
		case '-':
			reValue = num2 - num1;
			break;

		default:
			break;
		}

		return reValue;

	}
}