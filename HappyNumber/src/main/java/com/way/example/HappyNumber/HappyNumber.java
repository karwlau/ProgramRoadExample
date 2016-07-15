package com.way.example.HappyNumber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author Administrator
 * @description 对一个数字，我们可以把它的每一位数字的平方求和，从而得到一个新的数字。不断的进行这样的变换，最终会变成1。称为happy
 *              number，因为它最终回到了起点。
 * @date 2016-7-15
 */
public class HappyNumber {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		int num = Integer.parseInt(str);
		Map<Integer, Boolean> his = new HashMap<Integer, Boolean>();
		his.put(0, false);
		his.put(1, true);

		List<Integer> road = null;
		road = new ArrayList<Integer>();
		check(road, his, num);
		s.close();
	}

	public static void check(List<Integer> road, Map<Integer, Boolean> his, int chk) {
		if (his.containsKey(chk)) {
			setHis(road, his, his.get(chk));
			return;
		}
		if (road.contains(chk)) {
			setHis(road, his, false);
			return;
		}
		road.add(chk);
		int temp = pow(chk);
		check(road, his, temp);
	}

	// result
	public static void setHis(List<Integer> road, Map<Integer, Boolean> his, boolean flag) {
		for (int i : road) {
			his.put(i, flag);
		}
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
