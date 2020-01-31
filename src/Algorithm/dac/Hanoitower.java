package Algorithm.dac;

/**河內塔
 * @author snack
 *
 */
public class Hanoitower {

	public static void main(String[] args) {

		hanoiTower(10, 'A', 'B', 'C');
	}

	
	public static void hanoiTower(int num,char a, char b, char c) {
	
		if(num==1) {
			System.out.println("第1個盤從 "+a+"->"+c);
		}else {
			
			//n>=2情況 會看成兩個盤,1.最底的一個盤 2.上面的所有盤
			//1先把 最上面的所有盤從A->B,移動過程會使用到c
			hanoiTower(num-1, a, c, b);
			//2.把最下面的盤A->C
			System.out.println("第"+num+"個盤從 "+a+"->"+c);
			
			//3.把B盤所有盤從B->C,移動過程會使用到a
			hanoiTower(num-1, b, a, c);
			
			
		}
	}
}
