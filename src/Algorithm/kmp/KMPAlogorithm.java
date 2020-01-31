package Algorithm.kmp;

import java.util.Arrays;

/**
 * KMP算法
 * 
 * @author snack
 *
 */
public class KMPAlogorithm {

	public static void main(String[] args) {

		String str1 = "BBC ABCDAB ABCDABCDABDE";
		String str2 = "ABCDABD";
//		String str2 = "BBC";

		int[] next = kmpNext("ABCDABD");
//		System.out.println("next=" + Arrays.toString(next));

		int index = kmpSearch(str1, str2, next);
		System.out.println("index=" + index);

	}

	// 獲取到一個字符串的"部分匹配值"
	public static int[] kmpNext(String dest) {

		int[] next = new int[dest.length()];

		next[0] = 0;// 第一個 部分匹配值為0

		for (int i = 1, j = 0; i < dest.length(); i++) {

			//核心點...
			while (j > 0 && dest.charAt(i) != dest.charAt(j)) {

				j = next[j - 1];
			}

			if (dest.charAt(i) == dest.charAt(j)) {

				j++;
			}
			next[i] = j;

		}
		return next;
	}

	/**
	 * @param str1 字符串
	 * @param str2 需查找字串
	 * @param next 部分匹配表
	 * @return
	 */
	public static int kmpSearch(String str1, String str2, int[] next) {
		
		for (int i = 0, j = 0; i < str1.length(); i++) {

			
			
			//核心點...
			while (j > 0 && str1.charAt(i) != str2.charAt(j)) {

				j = next[j - 1];
			}

			if (str1.charAt(i) == str2.charAt(j)) {
				j++;

			}
			if (j == str2.length()) {
				return i - j + 1;
			}
		}
		return -1;
	}
}
