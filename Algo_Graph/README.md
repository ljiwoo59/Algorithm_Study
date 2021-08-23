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

## Disjoin-set (서로소 집합)
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
