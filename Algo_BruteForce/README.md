# 완전 탐색
* **Brute-foce** 혹은 **Generate-and-test** 기법
* 문제의 해법으로 생각할 수 있는 모든 경우의 수를 나열해보고 확인하는 기법
* 경우의 수가 작을 때 유용
* *조합적 문제*와 연관
  * **순열 (permutation), 조합 (combination), 부분집합 (subset)**

## [Permutation](https://github.com/ljiwoo59/Algorithm_Study/blob/main/Algo_BruteForce/PermutationTest.java)
* **nPr**
  * *nPr = n \* (n - 1) \* (n - 2) \* ... \* (n - r + 1)*
  * *nPn = n!*
* 서로 다른 *n* 개 중 *r* 개를 **순서 있이** 고르는 것
* 시간 복잡도 **O(n!)**
  * 12 개 이상부터 수행 시간이 폭발적으로 늘어남

```java
private static void permutation(int cnt) { // cnt : 현재까지 뽑은 개수
	if (cnt == R) {
		totalCount++;
		System.out.println(Arrays.toString(selected));
		return;
	}
	for (int i = 0; i < N; i++) { // 가지고 있는 수
		if (isSelected[i]) continue; // 이미 고른 수이면 고르지 않는다
		isSelected[i] = true;
		selected[cnt] = nums[i];
		permutation(cnt + 1);
		isSelected[i] = false; // 재귀에서 빠져나오면서 제일 최근 고른 수를 뺀다
	}
}
```
### 비트 연산자 이용
```java
private static void permutation(int cnt, int flag) {
	if (cnt == R) {
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0)
				System.out.println(numbers[i]);
		}
	}
	for (int i = 0; i < N; i++) {
		if ((flag & 1 << i) != 0) continue;
		permutation(cnt + 1, flag | 1 << i);
	}
}
```
* isSelected 배열 대신 비트의 1 값을 배열 처럼 쓴다
* *정수형 32 비트, long 형 64 비트*
* **<<**
	* 우선순위 제일 높음
	* 원소 유무 체크
* **&**
	* 각 비트열을 비교하여 두 비트 모두 1 이면 1, 아니면 0
	* 해당 원소가 이미 존재하는지 확인
* **|**
	* 각 비트열을 비교하여 두 비트 모두 0 이면 0, 아니면 1
	* 해당 원소를 지금 비트열에 추가


### 중복 순열

```java
private static void repeated_permutation(int cnt) { // cnt : 현재까지 뽑은 개수
	if (cnt == R) {
		totalCount++;
		System.out.println(Arrays.toString(selected));
		return;
	}
	for (int i = 0; i < N; i++) { // 가지고 있는 수
		selected[cnt] = nums[i];
		repeated_permutation(cnt + 1);
	}
}
```
* 중복이 가능하므로 보고 있는 수가 선택 되었는지 확인할 필요가 없음

## [NextPermutation](https://github.com/ljiwoo59/Algorithm_Study/blob/main/Algo_BruteForce/NextPermutationTest.java)
* 현 순열에서 오름차 순으로 다음 순열 생성
* 알고리즘
	* *뒤* 부터 탐색하며 교환 위치(*i - 1*) 찾기
		* *i - 1* 이 *i* 보다 작아지는 곳
	* *뒤* 부터 탐색하며 교환 위치(*i - 1*) 와 교환할 처음으로 큰 값 위치(*j*) 찾기
	* 두 위치 값 교환
	* *i* 부터 맨 *뒤*까지 오름차순 정렬

```java
do {
	// 순열 사용
	System.out.println(Arrays.toString(input));	
} while (np(input));

// 다음 큰 순열이 있으면 true, 없으면 false
private static boolean np(int[] numbers) {
	int N = numbers.length;
		
	// step1. 꼭대기(i)를 찾는다. 꼭대기를 통해 교환 위치(i-1) 찾기
	int i = N - 1;
	while(i > 0 && numbers[i - 1] >= numbers[i]) i--;	
	if (i == 0) return false;
		
	// step2. i - 1 위치값과 교환할 i 보다 처음으로 큰 값 찾기
	int j = N - 1;
	while (numbers[i - 1] >= numbers[j]) j--;
		
	// step3. i - 1 위치값과 j 위치값 교환
	swap(numbers, i - 1, j);
		
	// step4. 꼭대기(i)부터 맨뒤 까지 내림차순 형태의 순열을 오름차순으로 처리
	int k = N - 1;
	while (i < k) {
		swap(numbers, i++, k--);
	}
		
	return true;
}
```

---

## [Combination](https://github.com/ljiwoo59/Algorithm_Study/blob/main/Algo_BruteForce/CombinationTest.java)
* **nCr**
  * *nCr = (n-1)C(r-1) + (n-1)Cr*
  
```java
private static void combination(int cnt, int start) { // cnt : 현재 뽑은 숫자 개수, start : 선택 시작 위치
	if (cnt == R) {
		totalCount++;
		System.out.println(Arrays.toString(selected));
		return;
	}
	for (int i = start; i < N; i++) { // 선택 할 수 있는 숫자들
		selected[cnt] = nums[i];
		combination(cnt + 1, i + 1); // 내가 선택 한 수 이후부터 보낸다
	}
		
}
```

### 중복 조합

```java
private static void combination(int cnt, int start) { // cnt : 현재 뽑은 숫자 개수, start : 선택 시작 위치
	if (cnt == R) {
		totalCount++;
		System.out.println(Arrays.toString(selected));
		return;
	}
	for (int i = start; i < N; i++) { // 선택 할 수 있는 숫자들
		selected[cnt] = nums[i];
		combination(cnt + 1, i);
	}
		
}
```

* 중복이 가능하므로 현재 선택한 수 부터 다시 선택할 수 있음

## [NextPermutation 이용](https://github.com/ljiwoo59/Algorithm_Study/blob/main/Algo_BruteForce/NextPermutationComb.java)
* **N** 크기의 *int* 배열을 생성하여 **R** 크기 만큼 뒤에서 1로 초기화
* 알고리즘 수행시마다 1 값의 위치 조합이 만들어짐

---

## Subset
* 집합에 포함된 원소들을 선택하는 것
* 집합의 원소가 *n* 개 일 때, 공집합을 포함한 부분 집합의 수는 *2^n* 개
	* 각 원소를 부분 집합에 포함시키거나, 포함시키지 않는 2가지 경우
* n 이 20 이면 대략 100만, 30 이면 10억번을 연산함	 	

```java
private static void subset(int index) {
	if (index == N) {
		totalCnt++;
		for (int i = 0; i < N; i++) {
			if (isSelected[i])
				System.out.print(input[i] + " ");
		}
		System.out.println();
		return ;
	}
	isSelected[index] = ture; // 현재 원소를 부분 집합에 넣기
	subset(index + 1);
	isSelected[index] = false; // 현재 원소를 부분 집합에 넣지 않기
	subset(index + 1);
}
```
### 비트 연산자 이용
```java
private static void subset(int index, int flag) {
	if (index == N) {
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0)
				System.out.println(numbers[i]);
		}
	}
	subset(index + 1, flag | 1 << index);
	subset(index + 1, flag);
}
```
* isSelected 배열 대신 비트의 1 값을 배열 처럼 쓴다
* *정수형 32 비트, long 형 64 비트*
* **<<**
	* 우선순위 제일 높음
	* 원소 유무 체크
* **&**
	* 각 비트열을 비교하여 두 비트 모두 1 이면 1, 아니면 0
	* 해당 원소가 이미 존재하는지 확인
* **|**
	* 각 비트열을 비교하여 두 비트 모두 0 이면 0, 아니면 1
	* 해당 원소를 지금 비트열에 추가

## Binary Counting 이용
![image](https://user-images.githubusercontent.com/54715744/129200096-6b158467-2e30-4fb3-bdb7-c697682795c4.png)

* 사전적 순서로 생성하기 위한 간단한 방법
* **N** 개의 비트열 이용

```java
int arr[] = {3, 6, 7, 1, 5, 4};
int n = arr.length;

for (int i = 0; i < (1 << n); i++) { // 1 << n : 부분집합의 개수 2^n
	for (int j = 0; j < n; j++) { // 원소 수만큼 비트 비교
		if (i & (1 << j) != 0) // i 의 j 번째 비트가 1 이면 j 번째 원소 출력
			System.out.print(arr[j] + " ");
	}
	System.out.println();
}
```
---

## BFS (Breath First Search)
* 너비 우선 탐색
	* 루트 노드의 자식 노드들을 먼저 모두 차례로 방문한 후, 방문했던 자식 노드들을 기준으로 하여 다시 해당 노드의 자식 노드들을 방문하는 벙봅
* 선입선출 형태의 **큐** 활용

```java
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
```

## DFS (Depth First Search)
* 깊이 우선 탐색
	* 루트 노드에서 출발하여 한 방향으로 갈 수 있는 경로가 있는 곳까지 탐색해 가다가 더이상 갈 곳이 없으면, 가장 마지막에 만났던 갈림길 간선이 있는 노드로 되돌아와 다른 방향의 노드로 탐색을 반복하여 모든 노드를 방문하는 순회방법
* **재귀 호출** 이나 후입선출 형태의 **스택** 활용

```java
private void dfsByPreOrder(int current) { // 전위순회
	if (current > lastIndex)
		return;
		
	// 현재노드 처리
	System.out.print(nodes[current] + " ");
	// 왼쪽 자식 노드 방문
	dfsByPreOrder(current * 2);
	// 오른쪽 자식 노드 방문
	dfsByPreOrder(current * 2 + 1);
}
```



