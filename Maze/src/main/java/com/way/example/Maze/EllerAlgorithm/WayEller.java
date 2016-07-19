package com.way.example.Maze.EllerAlgorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author Administrator
 * @date 2016-7-19
 * @description 这里我简单介绍下这个算法，算法前提认为每个房间都属于一个独立的Set。
 *              1、取下一行房间，每2个属于不同Set的房间都有一定概率（通常为50%）打通，即将这2个房间合并入同一个Set。
 *              2、检查这一行，如果是最后一行跳转至4。否则，每个房间均有一定概率（通常50%）向下打通，
 *              同时保证这一行的每一个Set至少有一个房间向下打通。 3、 重复1。 4、 将这一行所有不同Set的房间全部打通，迷宫生成完毕。
 * @version 0.1beta
 */
public class WayEller {
	List<Line> map;
	static Random rand = new Random(new Date().getTime());

	public static void main(String[] args) {
		WayEller we = new WayEller();
		we.gen(10, 10);
		we.print();
	}

	public void gen(int width, int height) {
		this.map = new ArrayList<Line>();
		for (int i = 1; i <= height; i++) {
			Line line = new Line();
			line.line = new ArrayList<Element>();
			line.lineNum = i;
			while (line.line.size() < 1 || !(line.check() || i == height)) {
				line.line.clear();
				for (int j = 1; j <= width; j++) {
					line.line.add(new Element(j, i, width, height));
				}
			}
			map.add(line);
		}
	}

	public void print() {
		for (Line line : map) {
			for (Element e : line.line) {
				if (e.bottom)
					System.out.print(" ");
				else
					System.out.print("_");
				if (e.right)
					System.out.print(" ");
				else
					System.out.print("|");
			}
			System.out.println();
		}
	}

	static boolean isOpen() {
		int i = rand.nextInt();
		return i % 2 == 0;
	}

	class Line {
		List<Element> line;
		int lineNum;

		List<List<Element>> build() {
			// build set
			List<List<Element>> checkList = new ArrayList<List<Element>>();
			boolean flag = false;
			List<Element> els = null;
			for (Element e : line) {
				if (!flag) {
					els = new ArrayList<Element>();
				}
				els.add(e);
				if (e.right) {
					flag = true;
				} else {
					checkList.add(els);
					flag = false;
				}
			}
			return checkList;
		}

		boolean check() {
			List<List<Element>> checkList = build();
			for (List<Element> set : checkList) {
				boolean flag = true;
				for (Element e : set) {
					if (e.bottom) {
						flag = false;
						break;
					}
				}
				if (flag) {
					return false;
				}
			}
			return true;
		}
	}

	class Element {
		boolean top = true;
		boolean bottom = true;
		boolean left = true;
		boolean right = true;

		public Element(int wIdx, int hIdx, int w, int h) {
			if (hIdx == 1) {
				top = false;
			}
			if (wIdx == 1) {
				left = false;
			}
			right = isOpen();
			bottom = isOpen();
			if (hIdx == h) {
				bottom = false;
				right = true;
			}
			if (wIdx == w) {
				right = false;
			}
		}

	}

}
