package com.way.example.StealGold;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Administrator
 * @date 2016-7-15
 * @description 有连续的n个房子，每个房子有一定的金币。当小偷偷取两个连续的房子时会引发报警，请问这个小偷最多能偷多少金币？
 */
public class Algorithm {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		try {
			String num = s.nextLine();
			int i = Integer.parseInt(num);

			// base information
			int[] golds = new int[i];
			Random r = new Random(new Date().getTime());

			// initialize data
			System.out.println();
			System.out.print("datas:[");
			for (int j = 0; j < i; j++) {
				golds[j] = r.nextInt(10);// nextInt()%10;
				System.out.print(golds[j]);
				System.out.print(",");
			}
			System.out.println("]");

			// enumeration situation
			Result res = new Result(i);
			List<Result> resA = new ArrayList<Result>();
			resA.add(res);
			Result.count(0, golds, res, resA);
			res = new Result(i);
			resA.add(res);
			Result.uncount(0, golds, res, resA);

			// best result
			Result best = null;

			// all result
			for (Result rlt : resA) {
				System.out.print(rlt.total + ":	");
				for (boolean b : rlt.counts) {
					System.out.print(b + "	");
				}
				System.out.println();
				if (best == null || best.total < rlt.total) {
					best = rlt;
				}
			}

			System.out.print("the best result:");
			System.out.print(best.total);
			for (boolean b : best.counts) {
				System.out.print(b + "	");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			s.close();
		}
	}
}

class Result {
	int num;
	int total;
	boolean[] counts;

	public Result(int i) {
		num = i;
		total = 0;
		counts = new boolean[i];
	}

	public Result copy(int idx, int total) {
		Result r = new Result(this.num);
		r.num = this.num;
		r.total = total;
		for (int i = 0; i <= idx; i++) {
			r.counts[i] = this.counts[i];
		}
		return r;
	}

	public static void count(int i, int[] golds, Result res, List<Result> resA) {
		if (i < res.num) {
			res.total = res.total + golds[i];
			res.counts[i] = true;
			if (i + 1 < res.num) {
				Result.uncount(i + 1, golds, res, resA);
			}
		}
	}

	public static void uncount(int i, int[] golds, Result res, List<Result> resA) {
		if (i < res.num) {
			res.counts[i] = false;
			int total = res.total;
			if (i + 1 < res.num) {
				Result.count(i + 1, golds, res, resA);
				Result newRes = res.copy(i, total);
				resA.add(newRes);
				Result.uncount(i + 1, golds, newRes, resA);
			}
		}
	}
}
