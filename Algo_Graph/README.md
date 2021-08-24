# Graph

## [Adjacent Matirx](https://github.com/ljiwoo59/Algorithm_Study/blob/main/Algo_Graph/AdjMatrixTest.java)
```java
private static void dfs(int current, boolean[] visited) {
	visited[current] = true;
	System.out.println((char) (current + 65));
		
	for (int i = 0; i < N; i++) {
		if (!visited[i] && adjMatrix[current][i]) { // 미방문 && 인접정점
			dfs(i, visited);
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
			
		for (int i = 0; i < N; i++) {
			if (!visited[i] && adjMatrix[current][i]) { // 미방문 && 인접정점
				queue.offer(i);
				visited[i] = true;
			}
		}
	}
}
```

## [Adjacent List](https://github.com/ljiwoo59/Algorithm_Study/blob/main/Algo_Graph/AdjListTest.java)
```java
static class Node {
	int vertex;
	Node link;
		
	public Node(int vertex, Node link) {
		this.vertex = vertex;
		this.link = link;
	}
		
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
```

---

## [Disjoin-set (서로소 집합)](https://github.com/ljiwoo59/Algorithm_Study/blob/main/Algo_Graph/DisjointSetTest.java)
* 서로 중복 포함된 원소가 없는 집합
* 집합에 속한 하나의 특정 멤버 (**대표자**) 를 통해 각 집합들을 구분
* *연결리스트* 와 *트리* 를 이용해 표현
   * **연결리스트**
      *  같은 집합의 원소들은 하나의 연결리스트로 관리
      *  연결리스트의 맨 앞의 원소를 집합의 대표 원소로 삼는다
      *  각 원소는 집합의 대표원소를 가리키는 *링크*를 갖는다
   * **트리**
      *  하나의 집합을 하나의 트리로 표현
      *  자식 노드가 부모 노드를 가리키며 루트 노드가 *대표자*가 된다
* **Make-Set(x)** : 유일한 멤버 *x* 를 포함하는 새로운 집합을 생성하는 연산
* **Find-Set(x)** : *x* 를 포함하는 집합을 찾는 연산
* **Union(x, y)** : *x* 와 *y* 를 포함하는 두 집합을 통합하는 연산

### Rank 를 이용한 Union
* 각 노드는 자신을 루트로 하는 subtree 의 높이를 *Rank* 로 저장
* 두 집합을 합칠 때 *Rank* 가 **낮은** 집합을 *Rank* 가 **높은** 집합에 붙인다

### Path Compression
* *Find-Set* 을 행하는 과정에서 만나는 모든 노드들이 직접 *root* 를 가리키도록 포인터를 바꾸어 준다
  * *Find-Set* 연산은 특정 노드에서 루트까지의 경로를 찾아 가며 노드의 부모 정보를 갱신한다

### Code
```java
static int N; // 원소 개수
static int[] parents; // 부모 원소를 관리 (트리처럼 사용)
	
private static void make() {
	// 모든 원소를 자신을 대표자로 만듦
	for (int i = 0; i < N; i++) {
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
```

* ***make*** 로 각 정점의 *최상단 부모*를 **자기 자신으로 초기화** 해준다
* 간선 정보를 ***union*** 으로 두 간선이 **같은 집합이 아니면** (같은 최상단 부모를 가지고 있지 않으면) *b* 정점의 *최상단 부모*의 부모를 *a* 정점의 *최상단 부모*로 바꾸어 준다
	* 이로서 a 정점과 b 정점은 같은 *최상단 부모*를 가지게 된다
	* 내부에 ***find*** 함수가 재귀적으로 *최상단 부모* 가 자기 자신을 리턴 할때 까지 반복된다
* 모든 간선 정보에 대해 ***union*** 이 끝나면 주어진 간선 정보대로 트리는 구현이 되지만 *parents* 배열은 모두 *최상단 부모*로 업데이트가 되어있지 않기 때문에 ***find*** 를 한번 더 전체 정점에 대해 불러주면서 *최상단 부모* 배열을 만들 수 있다

---

## Minimum Spanning Tree (최소 신장 트리)
* **무향 가중치 그래프**에서 *신장 트리*를 구성하는 간선들의 가중치의 합이 **최소**인 신장 트리
	* **모든 정점을 연결하는 간선들의 가중치의 합이 최소**가 되는 트리
		* **두 정점 사이**의 최소 비용의 경로 찾기
	* *신장 트리* : *n* 개의 정점으로 이루어진 **무향 그래프**에서 *n* 개의 정점과 *n - 1* 개의 간선으로 이루어진 트리

## [Kruskal Algorithm](https://github.com/ljiwoo59/Algorithm_Study/blob/main/Algo_Graph/MST_KruskalTest.java)
* 최초에 모든 간선을 **가중치**에 따라 ***오름차순***으로 정렬 (*PriorityQueue 와 Comparable* 을 이용하여 시간 복잡도 줄이기)
* **가중치가 가장 낮은 간선**부터 선택하면서 트리를 증가시킴
	* 사이클이 존재하면 다음으로 가중치가 낮은 간선 선택
* ***n - 1*** 개의 간선이 선택될 때까지 반복

```java
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
```

## [Prim Algorithm]()
* 하나의 정점에서 연결된 간선들 중에 하나씩 선택하면서 ***MST***를 만들어 가는 방식
	* 임의 정점을 하나 선택해서 시작
	* 선택한 정점과 **인접하는 정점들 중의 *최소 비용의 간선*이 존재하는 정점**을 선택
	* 모든 정점이 선택될 때 까지 반복
* ***서로소***인 **2개의 집합 정보**를 유지
	* 트리 정점들 : *MST* 를 만들기 위해 선택된 정점들
	* 비트리 정점들 : 선택되지 않은 정점들 

```java
int result = 0; // 최소 신장 트리 비용
Arrays.fill(minEdge, INFINITY); // 초기에 max 값으로 초기화
minEdge[0] = 0; // 임의의 시작점 0의 간선비용을 0으로 세팅
		
for (int i = 0; i < N; i++) {
	// 1. 신장트리에 포함되지 않은 정점 중 최소 간선비용의 정점 찾기
	int min = Integer.MAX_VALUE;
	int minVertex = -1; // 최소 간선비용의 정점 번호
	for (int j = 0; j < N; j++) {
		if (!visited[j] && min > minEdge[j]) {
			min = minEdge[j];
			minVertex = j;
		}
	}
			
	visited[minVertex] = true; // 신장트리에 포함시킴
	result += min; // 간선비용 누적
			
	// 2. 선택된 정점 기준으로 신장트리에 연결되지 않은 타 정점과의 간선 비용 최소로 업데이트
	for (int j = 0; j < N; j++) {
		if (!visited[j] && adjMatrix[minVertex][j] != 0 && minEdge[j] > adjMatrix[minVertex][j]) {
			minEdge[j] = adjMatrix[minVertex][j];
		}
	}	
}
```

---

## 최단 경로
* 간선의 **가중치가 있는** 그래프에서 **두 정점 사이의 경로들 중에 간선의 가중치의 합이 최소**인 경로
* **하나의 시작 정점에서 끝 정점까지의 최단 경로**
	* ***Dijkstra Algorithm***
		* 음의 가중치를 허용하지 않음
	* ***Bellman-Ford Algorithm***
		* 음의 가중치 허용
* **모든 정점들에 대한 최단 경로**
	* ***Floyd-Warshall Algorithm***

## [Dijkstra Algorithm](https://github.com/ljiwoo59/Algorithm_Study/blob/main/Algo_Graph/DijkstraTest.java)
* 시작 정점에서 **거리가 최소인 정점**을 선택해 나가면서 최단 경로를 구하는 방식
	* 단, 현재 거리가 최소인 정점을 통해 다음 정점까지의 거리가 현재 다른 정점을 통해 가는 거리보다 클 수가 있으므로 **간선 가중치 배열을 누적시키며 최소 거리로 업데이트** 해준다

```java
Arrays.fill(distance, INFINITY); // 초기에 시작 정점에서 index 정점 까지의 최소 거리를 max 값으로 초기화
distance[start] = 0; // 시작 정점의 거리는 0
		
int min, current = 0;
for (int i = 0; i < V; i++) {
	min = INFINITY;
	// 현재 방문한 정점들 까지의 거리 중 최소 거리를 main 길로 만든다
	for (int j = 0; j < V; j++) {
		if (!visited[j] && min > distance[j]) {
			min = distance[j];
			current = j;
		}
	}
	// main 길로 판명된 마지막 정점은 방문 표시를 해준다
	visited[current] = true;
	if (current == end) break; // 도착정점 도착 시 종료
			
	// 현재 main 길의 마지막 정점과 방문하지 않은 인접한 정점을 찾고
	// 인접한 정점 까지의 거리가 현재까지 계산한 최소 거리보다 작으면 업데이트
	for (int j = 0; j < V; j++) {
		if (!visited[j] && matrix[current][j] != 0 && distance[j] > min + matrix[current][j]) {
			distance[j] = min + matrix[current][j];
		}
	}
			
}
```
