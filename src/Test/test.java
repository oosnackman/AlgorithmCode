package Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class test {

	public static void main(String[] args) {

		System.out.println(getPermutation(3, 4));
	}

	public static String getPermutation(int n, int k) {
		List<String> table = new LinkedList<>(map());
		if (n == 1)
			return table.get(0);
		k--;
		String ans = "";
	
		while(n>0) {
			int kth= kth(n--);
			int num= k/kth;
			
			k%=kth;
			ans+=table.get(num);
			table.remove(num);
		}

		return ans;
	}

	public static List<String> map() {
		String s = "1,2,3,4,5,6,7,8,9";
		List<String> list = Arrays.asList(s.split(","));
		return list;
	}

	//皆乘
	public static int kth(int n) {
		int t = 1;
		for (int i = 1; i < n; i++) {
			t *= i;
		}
		return t;
	}

}