package com.way.example.HappyNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author Administrator
 * @description 对一个数字，我们可以把它的每一位数字的平方求和，从而得到一个新的数字。不断的进行这样的变换，最终会变成1。称为happy
 *              number，因为它最终回到了起点。
 * @date 2016-7-15
 * @version 1000 with path output
 */
public class HappyNumber2 {
	public static void main(String[] args) {
		Map<Integer, Happy> his = new HashMap<Integer, Happy>();
		his.put(1, new Happy(1, true, Arrays.asList(1)));

		int root = 2;
		List<Integer> road = null;
		while (root < 1001) {// 所有数最终一定被拖回1000以内数字
			road = new ArrayList<Integer>();
			check(road, his, root);
			root++;
		}
		for (Entry<Integer, Happy> e : his.entrySet()) {
			System.out.print(e.getKey() + "path : ");
			for (int path : e.getValue().road) {
				System.out.print(path + "->");
			}
			System.out.println();
		}
	}

	public static void check(List<Integer> road, Map<Integer, Happy> his, int chk) {
		// check history
		if (his.containsKey(chk)) {
			setHis(road, his, his.get(chk));
			return;
		}
		if (road.contains(chk)) {
			setHisF(road, his);
			return;
		}
		road.add(chk);
		int temp = pow(chk);
		check(road, his, temp);
	}

	// result
	public static void setHis(List<Integer> road, Map<Integer, Happy> his, Happy hisTag) {
		Map<Integer, List<Integer>> roadMap = new HashMap<Integer, List<Integer>>();
		List<Integer> iRoad = null;
		for (int i = 0; i < road.size(); i++) {
			iRoad = new ArrayList<Integer>();
			roadMap.put(road.get(i), iRoad);
			for (Entry<Integer, List<Integer>> e : roadMap.entrySet()) {
				e.getValue().add(road.get(i));
			}
		}
		for (int i = 0; i < road.size(); i++) {
			iRoad = roadMap.get(road.get(i));
			iRoad.addAll(hisTag.road);
			his.put(road.get(i), new Happy(road.get(i), hisTag.isHappy, iRoad));
		}
	}

	public static void setHisF(List<Integer> road, Map<Integer, Happy> his) {
		for (int i = 0; i < road.size(); i++) {
			List<Integer> iRoad = getiRoadF(road.get(i), road);
			his.put(road.get(i), new Happy(road.get(i), false, iRoad));
		}
	}

	public static List<Integer> getiRoadF(int i, List<Integer> road) {
		boolean start = false;
		boolean end = false;
		List<Integer> iRoad = new ArrayList<Integer>();
		int j = 0;
		while (!end) {
			if (start) {
				if (road.get(j) == i) {
					end = true;
				}
				iRoad.add(road.get(j));
			} else if (road.get(j) == i) {
				start = true;
				iRoad.add(road.get(j));
			}
			j = (j + 1) % road.size();
		}
		return iRoad;
	}

	// pow
	public static int pow(int num) {
		int total = 0;
		do {
			int temp = num % 10;
			total = total + new Double(Math.pow(temp, 2)).intValue();
			num = num / 10;
		} while (num > 0);
		return total;
	}

}

class Happy {
	int root;
	List<Integer> road;
	Boolean isHappy;

	public Happy(int root, boolean isHappy, List<Integer> road) {
		this.root = root;
		this.isHappy = isHappy;
		this.road = new ArrayList<Integer>();
		this.road.addAll(road);
	}
}
