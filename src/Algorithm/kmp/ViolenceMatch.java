package Algorithm.kmp;

public class ViolenceMatch {
	public static void main(String[] args) {

		System.out.println(violenceMatch("中華名A國我愛你知道嗎", "A"));
	}
	
	//暴力匹配
	public static int violenceMatch(String str1,String str2) {
		
		char[] s1=str1.toCharArray();
		char[] s2=str2.toCharArray();
		
		int s1Len=s1.length;
		int s2Len=s2.length;

		
		 
		int i=0; //指向s1
		int j=0; //指向s2
		
	
		while(i<s1Len&&j<s2Len) {
			
			
			if(s1[i]==s2[j]) {
				i++;
				j++;
			}else {
				
				i=i-(j-1);//i-j+1
				j=0;
			}

		}
		
		if(j==s2Len) {
			return i-j;
		}
		
		return -1;
		
	}
}
