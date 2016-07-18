package com.way.example.SortAlgorithml;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Random rand = new Random(new Date().getTime());
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		int num = Integer.parseInt(str);
		int data[] = new int[num];
		System.out.println("sort data:");
		for (int i = 0; i < num; i++) {
			data[i] = rand.nextInt();
			System.out.print(data[i]);
			System.out.print(",");
		}
		System.out.println();
		switch (1) {
		case 1:
			new InsertSort().insertSort(data);
			break;
		case 2:
			new ShellSort().shellSort(data);
			break;
		case 3:
			new SelectSort().selectSort(data);
			break;
		case 4:
			new HeapSort().heapSort(data);
			break;
		case 5:
			new BubbleSort().bubbleSort(data);
			break;
		case 6:
			new QuickSort().quickSort(data);
			break;
		case 7:
			new MergingSort().mergingSort(data);
			break;
		case 8:
			new RadixSort().radixSort(data);// 正整数
			break;
		default:

		}
		System.out.println("after sort:");
		for (int i = 0; i < num; i++) {
			System.out.print(data[i]);
			System.out.print(",");
		}
		System.out.println();
		s.close();
	}
}
