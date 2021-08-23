import java.io.*;
import java.util.*;

// 인접행렬과 다르게 입력받은 순서 반대로 넣는다

public class AdjListTest {

	static class Node {
		int vertex;
		Node link;
		
		public Node(int vertex, Node link) {
			this.vertex = vertex;
			this.link = link;
		}
		
	}
	
	static int N; // 정점 개수
	static Node[] adjList; // 인접행렬 (가중치 없음)

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adjList = new Node[N];
		int C = Integer.parseInt(br.readLine()); // 간선 개수
		for (int i = 0; i < C; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}
		System.out.println("======================bfs=======================");
		bfs();
		System.out.println("======================dfs=======================");
		dfs(0, new boolean[N]);
	}
	
	private static void dfs(int current, boolean[] visited) {
		visited[current] = true;
		System.out.println((char) (current + 65));
		
		for (Node temp = adjList[current]; temp != null; temp = temp.link) {
			if (!visited[temp.vertex]) { // 미방문 
				dfs(temp.vertex, visited);
			}
		}
	}
	
	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N];
		
		queue.offer(0);
		visited[0] = true;
		
		while (!queue.isEmpty()) {
			int current = queue.poll();
			System.out.println((char) (current + 65));
			
			for (Node temp = adjList[current]; temp != null; temp = temp.link) {
				if (!visited[temp.vertex]) { // 미방문 
					queue.offer(temp.vertex);
					visited[temp.vertex] = true;
				}
			}
		}
		
	}

}
