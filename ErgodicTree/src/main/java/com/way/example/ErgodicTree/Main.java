package com.way.example.ErgodicTree;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		Random rand = new Random();
		// init
		Node parent = new Node();
		parent.val(0, rand);

		parent.ergodic();
	}
}

class Node {
	int value;
	Node left;
	Node right;

	//initialize
	public void val(int depth, Random rand) {
		this.value = rand.nextInt();
		if (depth == 3) {
			return;
		}
		this.left = new Node();
		this.left.val(depth + 1, rand);
		this.right = new Node();
		this.right.val(depth + 1, rand);
	}

	//ergodic
	public void ergodic() {
		System.out.println(this.value);
		if (this.left != null)
			this.left.ergodic();
		if (this.right != null)
			this.right.ergodic();
	}
}