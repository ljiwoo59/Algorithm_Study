package dijkstra;

import java.io.*;
import java.util.*;

public class DijkstraTest {
	static int V, start, end, INFINITY = Integer.MAX_VALUE; // 출발 정점은 0, 도착 정점은 제일 마지막 정점 이라 하자
	static int[][] matrix; // 인접 행렬 (가중치)
	static int[] distance; // 0 에서 부터의 최소 거리 비용
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		start = 0;
		end = V - 1;
		matrix = new int[V][V];
		distance = new int[V];
		visited = new boolean[V];
		
		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < V; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Arrays.fill(distance, INFINITY);
		distance[start] = 0; // 시작 정점의 거리는 0
		
		int min, current = 0;
		for (int i = 0; i < V; i++) {
			min = INFINITY;
			// 현재 방문한 정점들 까지의 거리 중 최소 거리를 main 길로 만든다
			for (int j = 0; j < V; j++) {
				if (!visited[j] && distance[j] < min) {
					min = distance[j];
					current = j;
				}
			}
			// main 길로 판명된 마지막 정점은 방문 표시를 해준다
			visited[current] = true;
			if (current == end) break; // 도착정점 도착 시 종료
			
			// 현재 main 길의 마지막 정점과 방문하지 않은 인접한 정점을 찾고 인접한 정점 까지의 거리가 현재까지 계산한 최소 거리보다 작으면 업데이트
			for (int j = 0; j < V; j++) {
				if (!visited[j] && matrix[current][j] != 0 && distance[j] > min + matrix[current][j]) {
					distance[j] = min + matrix[current][j];
				}
			}
			
		}
		System.out.println(distance[end]);
		br.close();
	}

}
