package kruskal;

import java.io.*;
import java.util.*;

public class MST_KruskalTest {
	
	static class Edge implements Comparable<Edge> {
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	
	static int[] parents; // 부모 원소를 관리 (트리처럼 사용)
	
	private static void make() {
		parents = new int[V];
		// 모든 원소를 자신을 대표자로 만듦
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}
	
	// a 가 속한 집합의 대표자 찾기
	private static int find(int a) {
		if (a==parents[a]) return a; // 자신이 대표자
		return parents[a] = find(parents[a]); // 자신이 속한 집합의 대표자를 자신의 부모로 : path compression
	}
	
	// 두 원소를 하나의 집합으로 합치기 (대표자를 이용해서 합침)
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) return false; // 이미 같은 집합으로 합치지 않음
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	static int V, E;
	static Edge[] edgeList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		// 간선 리스트
		edgeList = new Edge[E];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			// start, end, weight
			edgeList[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(edgeList); // 오름차순 정렬
		
		make(); // 모든 정점을 각각의 집합으로 만들고 출발
		
		// 간선 하나씩 시도하며 트리 만들어감
		int cnt = 0, result = 0;
		for (Edge edge : edgeList) {
			if (union(edge.start, edge.end)) {
				result += edge.weight;
				if (++cnt == V - 1) break; // 신장트리 완성
			}
		}
		
		System.out.println(result);
	}

}
