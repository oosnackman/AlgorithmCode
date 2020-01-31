package datastruct.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 後序表達式
 * 
 * @author snack
 *
 */
public class PolandNotation {

	public static void main(String[] args) {
		
		// [後序表達式的計算]
		// 先訂一個逆波蘭表達式
		// (3+4)x5-6 => 3 4 + 5 x 6 -
		// (30+4)x5-6 => 30 4 + 5 x 6 -
		// 為了方便 ,使用空格格開
		String suffixExpression = "30 4 + 5 x 6 -";
		List<String> list = getListString(suffixExpression);
		int res = calculate(list);
		//System.out.println("計算結果:" + res);
		
		System.out.println("-------------------------");
		// [中序轉後序表達式]
		// 概念
		// 目標:1+((2+3)×4)-5 轉為 1 2 3 + 4 × + 5 –"
		// 1.將中序表達式轉成list => [1, +, (, (, 2, +, 3, ), ×, 4, ), -, 5]
		// 2.將list[1, +, (, (, 2, +, 3, ), x, 4, ), -, 5] =>[1,2,3,+,4,×,+,5,–]

		String expression = "1+((2+3)x4)-5";
		list=parseSuffixExpressionList(toInfixExpressionList(expression));
		System.out.println("第一種方法,中序轉後序表達式:"+list);
		System.out.println("第一種方法,使用後序表達式的計算:"+calculate(list));
	
		System.out.println("-------------------------");
		
		
		list=parseSuffixExpressionList2(toInfixExpressionList(expression));
		System.out.println("重購!第一種方法,中序轉後序表達式:"+list);
		System.out.println("重購!第一種方法,使用後序表達式的計算:"+calculate(list));

	
	}

	/**
	 * 將中序轉list 1+((2+3)×4)-5 ->[1, +, (, (, 2, +, 3, ), ×, 4, ), -, 5]
	 * 
	 * @param exp
	 * @return
	 */
	public static List<String> toInfixExpressionList(String exp) {

		List<String> list = new ArrayList<String>();

		int index = 0;
		String str = "";
		String res = "";
		char c;

		do {

			if ((c = exp.charAt(index)) < 48 || (c = exp.charAt(index)) > 57) {
				list.add("" + c);
				index++;
			} else {

				str = "";
				while (index < exp.length() && (c = exp.charAt(index)) >= 48 && (c = exp.charAt(index)) <= 57) {
					str += c;
					index++;
				}
				list.add(str);
			}

		} while (index < exp.length());

		return list;
	}

	
	
	
	
	//方法1
	public static List<String> parseSuffixExpressionList(List<String> ls) {

		Stack<String> s1 = new Stack<String>();
		Stack<String> s2 = new Stack<String>();
		String str = "";
		boolean loop = true;
		List<String> relist = new ArrayList<String>();
		for (String ele : ls) {

			if (ele.matches("\\d+")) { // 匹配多位數
				s2.push(ele);
			} else {

				loop = true;
				while (loop) {
					
						if (s1.isEmpty() || ele.equals("(") ) {
							s1.push(ele);
							loop = false;
						}else if(ele.equals(")")) {

							while (true) {
								str = s1.pop();
								if (str.equals("(")) {
									break;
								}
								s2.push(str);
							}
							loop = false;
						}
						else if(( priority2(ele.charAt(0)) > priority2( s1.peek().charAt(0) ) )){
							s1.push(ele);
							loop = false;
						}else {
							
							s2.push(s1.pop());
						}
				}
			}

	
		}


		while (s1.size()!=0) {
			s2.push(s1.pop());
		}

		return  s2; 
	}

	
	
	
	//方法1...重購方法2
	public static List<String> parseSuffixExpressionList2(List<String> ls) {
		
		//push方法插入stack的顶端，而add方法是插入在vector的底端

		Stack<String> s1 = new Stack<String>();
		Stack<String> s2 = new Stack<String>();
		
		for (String item : ls) {
			
			if (item.matches("\\d+")) {
				
				s2.add(item);
			}else if(item.equals("(")){
				
				s1.push(item);
			}else if(item.equals(")")) {
				
				
				while(!s1.peek().equals("(")) {
					
					s2.add(s1.pop());
					
				}
				s1.pop();
			}else {
				
				while(s1.size()!=0&&priority2(item.charAt(0)) <= priority2( s1.peek().charAt(0) )  ) {
					
					s2.add(s1.pop());
				}
				s1.push(item);
			}
			
			
		}
		
		while(s1.size()!=0) {
			s2.add(s1.pop());
		}
		return s2;

	}

	
	
	private static int priority2(char c) {
		return c == 'x' || c == '/' ? 1 : c == '+' || c == '-' ? 0 : -1;
	}

	// 返回運算服的優先級,由我們自定 ,由數字類表示
	// 假設目前的表達式只有+,-,*,/
	public static int priority(int oper) {
		if (oper == 'x' || oper == '/') {
			return 1;
		} else if (oper == '+' || oper == '-') {
			return 0;
		} else {
			return -1;
		}
	}

	public static List<String> getListString(String suffixExpression) {

		String[] split = suffixExpression.split(" ");

		List<String> list = new ArrayList<String>();

		for (String ele : split) {
			list.add(ele);
		}
		return list;
	}

	// (3+4)x5-6 => 3 4 + 5 x 6 -
	public static int calculate(List<String> list) {

		Stack<String> stack = new Stack<String>();

		for (String item : list) {

			if (item.matches("\\d+")) { // 匹配多位數
				stack.push(item);
			} else {
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				int res = 0;

				switch (item) {
				case "+":
					res = num1 + num2;
					break;
				case "-":
					res = num1 - num2;
					break;
				case "x":
					res = num1 * num2;
					break;
				case "/":
					res = num1 / num2;
					break;
				default:
					throw new RuntimeException("[" + item + "]運算符有誤");
				}

				stack.push("" + res);
			}
		}
		return Integer.parseInt(stack.pop());

	}

}
