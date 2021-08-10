package tree;

import java.util.*;

public class CompleteBinaryTree {

	private char[] nodes;
	private final int SIZE;
	private int lastIndex; // 마지막에 추가된 노드의 인덱스
	
	public CompleteBinaryTree(int size) {
		this.SIZE = size;
		nodes = new char[size + 1];
	}
	
	public void add(char c) {
		if (lastIndex < SIZE)
			nodes[++lastIndex] = c;
	}
	
	public void bfs() {
		// 탐색을 기다리는 노드들이 저장됨
		// 배열로 관리하므로 탐색할 노드를 관리하는 인덱스
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(1); // 루트노드 인덱스 저장
		
		int current = 0;
		while(!queue.isEmpty()) {
			current = queue.poll();
			System.out.println(nodes[current]);
			if (current * 2 <= lastIndex) // 왼쪽 자식이 있다면
				queue.offer(current * 2);
			if (current * 2 + 1 <= lastIndex) // 오른쪽 자식이 있다면
				queue.offer(current * 2 + 1);
		}
	}
	
	public void bfs2() {
		// 탐색을 기다리는 노드들이 저장됨
		// 배열로 관리하므로 탐색할 노드를 관리하는 인덱스
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(1); // 루트노드 인덱스 저장
		
		int current = 0, level = 0, size = 0;
		while (!queue.isEmpty()) {
			size = queue.size();
			
			System.out.print("level " + level + " : ");
			while (--size >= 0) {
				current = queue.poll();
				System.out.print(nodes[current] + " ");
				if (current * 2 <= lastIndex) // 왼쪽 자식이 있다면
					queue.offer(current * 2);
				if (current * 2 + 1 <= lastIndex) // 오른쪽 자식이 있다면
					queue.offer(current * 2 + 1);
			}
			System.out.println();
			++level;
		}
	}
	
	public void dfsByPreOrder() {
		System.out.print("Preorder : ");
		dfsByPreOrder(1);
		System.out.println();
	}
	
	private void dfsByPreOrder(int current) {
		if (current > lastIndex)
			return;
		// 현재노드 처리
		System.out.print(nodes[current] + " ");
		// 왼쪽 자식 노드 방문
		dfsByPreOrder(current * 2);
		// 오른쪽 자식 노드 방문
		dfsByPreOrder(current * 2 + 1);
	}
	
	public void dfsByInOrder() {
		System.out.print("Inorder : ");
		dfsByInOrder(1);
		System.out.println();
	}
	
	private void dfsByInOrder(int current) {
		if (current > lastIndex)
			return;
		
		// 왼쪽 자식 노드 방문
		dfsByInOrder(current * 2);
		// 현재노드 처리
				System.out.print(nodes[current] + " ");
		// 오른쪽 자식 노드 방문
		dfsByInOrder(current * 2 + 1);
	}
	
	public void dfsByPostOrder() {
		System.out.print("Postorder : ");
		dfsByPostOrder(1);
		System.out.println();
	}
	
	private void dfsByPostOrder(int current) {
		if (current > lastIndex)
			return;
		
		// 왼쪽 자식 노드 방문
		dfsByPostOrder(current * 2);
		// 오른쪽 자식 노드 방문
		dfsByPostOrder(current * 2 + 1);
		// 현재노드 처리
		System.out.print(nodes[current] + " ");
	}
}
