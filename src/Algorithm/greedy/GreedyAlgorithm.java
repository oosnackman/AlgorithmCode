package Algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 貪婪算法
 * 
 * @author snack
 *
 */
public class GreedyAlgorithm {

	public static void main(String[] args) {

		// 創立廣播電台
		HashMap<String, HashSet<String>> tvs = new HashMap<String, HashSet<String>>();

		HashSet<String> tv1 = new HashSet<String>();
		tv1.add("北京");
		tv1.add("上海");
		tv1.add("天津");

		HashSet<String> tv2 = new HashSet<String>();
		tv2.add("廣州");
		tv2.add("北京");
		tv2.add("深圳");

		HashSet<String> tv3 = new HashSet<String>();
		tv3.add("成都");
		tv3.add("上海");
		tv3.add("杭州");

		HashSet<String> tv4 = new HashSet<String>();
		tv4.add("上海");
		tv4.add("天津");

		HashSet<String> tv5 = new HashSet<String>();
		tv5.add("杭州");
		tv5.add("大連");

		// 加入集合
		tvs.put("k1", tv1);
		tvs.put("k2", tv2);
		tvs.put("k3", tv3);
		tvs.put("k4", tv4);
		tvs.put("k5", tv5);

		// 儲放所有的地區
		HashSet<String> allAreas = new HashSet<String>();
		allAreas.add("北京");
		allAreas.add("上海");
		allAreas.add("天津");
		allAreas.add("廣州");
		allAreas.add("深圳");
		allAreas.add("成都");
		allAreas.add("杭州");
		allAreas.add("大連");

		// 選到的tv集合
		ArrayList<String> selects = new ArrayList<String>();
		
		//儲放掃瞄過程中每個tv的覆蓋地區(allAreas與tv的交集)
		HashSet<String> tempSet = new HashSet<String>();
		
		String maxKey=null;
		while(allAreas.size()!=0) {
			
			for (String key:tvs.keySet()) {
				tempSet.clear();
				HashSet<String> areas = tvs.get(key);
				
				tempSet.addAll(areas);
				tempSet.retainAll(allAreas);	//allAreas與tempSet 的交集 ,也就是 [A,B,C] x  [A,C]  = [A,C]
				
				
				//這句話是 貪婪算法的特點,每次都選擇最好的
				if(tempSet.size()>0&&( maxKey==null || tempSet.size()> tvs.get(maxKey).size()  )) {
					maxKey=key;
				}
			}
			
			if(maxKey!=null) {
				selects.add(maxKey);
				
				//要清除掉要覆蓋的
				allAreas.removeAll(tvs.get(maxKey));
			}
			
			maxKey=null;
			
		}
		
		System.out.println("最好的="+selects);

	}

}
