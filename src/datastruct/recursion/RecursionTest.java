package datastruct.recursion;

public class RecursionTest {

	public static void main(String[] args) {
	
		test(5);
		//System.out.println("\n"+factorial(5));
	}

	//觀念1
	public static void test(int n) {
		System.out.println("nn:"+n);
		if(n>2) {
			test(n-1);
		}
		System.out.println("n:"+n);
	}

	//觀念2
	public static int  factorial(int n) {
		
		if(n==1) {
			return 1;
		}else {
			System.out.printf("(%d)x%d",(n-1),n);
			return factorial(n-1)*n;
		}
		
		
	}



}
