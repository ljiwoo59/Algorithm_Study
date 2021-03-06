# Dynamic Programming
* 먼저 **작은 부분 문제들의 해**를 구하고 이들을 이용하여 보다 큰 크기의 부분 문제들을 해결하여, **최종적으로 원래 주어진 문제**를 해결하는 알고리즘 설계 기법
* **최적화 문제**를 해결하는 알고리즘
### 분할 정복 vs. 동적계획법
* **분할정복**
  * 연관 없는 부분 문제로 분할
  * 부분 문제를 재귀적으로 해결
  * 부분 문제의 해를 결합
  * 하향식 접근
* **DP**
  * 부분 문제들이 연관이 없으면 적용 불가
  * 부분 문제들은 더 작은 부분 문제들을 공유
  * 모든 부분 문제를 한번만 계산하고 결과를 저장 및 재사용
  * 상향식 접근

## 중복 부분문제 구조 (Overlapping subproblems)
* *순환적인 관계*를 명시적으로 표현하기 위해 **점화식** 사용
* 작은 문제의 해를 **메모이제이션**하여 중복된 계산 방지
### Memoization
* 이전에 계산한 값을 메모리에 저장해서 중복 계산을 방지하는 기술
* 추가적인 메모리 공간 필요


## 최적 부분문제 구조 (Optimal substructure)
* 어떤 문제에 대한 해가 최적일 때, 그 해를 구성하는 작은 문제들의 해 역시 최적이어야 한다
* *최장경로 (Longest Path) 문제*는 최적의 원칙이 적용되지 않는다
  
## DP 적용 접근 방법
* 최적해 구조의 특성 파악
  * 문제를 부분 문제로 나눈다
* 최적해의 값을 재귀적으로 정의
  * 부분 문제의 최적해 값에 기반하여 문제의 최적해 값 정의
* 상향식 방법으로 최적해 값 계산
  * 가장 작은 부분 문제부터 해를 구하여 테이블에 저장
  * 테이블의 해를 이용해 점차적으로 상위 부분 문제의 최적해를 구한다
  
### 피보나치 수
```java
private static int fibo_dp(n) {
  f[0] = 0;
  f[1] = 1;
  
  for (int i = 2; i <= n; i++)
    f[i] = f[i - 1] + f[i - 2];
  
  return f[n];
}
```
* 반복문을 사용하기에 함수 호출이 발생하지 않는다
* 중복 계산이 없다
* **O(n)**

---

# 응용
## 최장 증가 부분 수열
* **LIS (Longest Incresing Subsequence)**
* *3, 2, 6, 4, 5, 1* 의 최장 증가 부분 수열은 *2, 4, 5*

### O(n<sup>2</sup>) DP 접근
```java
int max = 0;
for (int i = 0; i < N; i++) { // 현재 바라보는 수
  d[i] = 1;
  for (int j = 0; j < i; j++) { // 현재 위치까지의 수들 확인
    // 현재 바라보는 수보다 작은 수 && 현재 위치까지의 LIS 길이가 해당 바라보는 수까지의 LIS 길이 + 1 보다 작으면
    if (nums[j] < nums[i] && d[i] < d[j] + 1)
      d[i] = d[j] + 1; // 현재 바라보는 수까지의 LIS 길이를 업데이트
  }
  if (max < d[i]) max = d[i];
}
```

### O(nlogn) 이진 탐색 접근
* **Arrays.binarySearch(LIS 배열, fromIndex, toIndex, 찾을 값)**
* **LIS\[i] = 길이 i 인 증가하는 부분 수열을 만들 수 있는 마지막 원소 중 가장 작은 값**

```java
int size = 0; // LIS 에 채워진 원소 수
for (int i = 0; i < N; i++) {
  // 배열이 비어있거나, 배열의 마지막 원소보다 현재 보는 수보다 작으면 배열의 마지막에 넣어준다
  if (size == 0 || LIS[size - 1] < nums[i]) LIS[size++] = nums[i];
  else {
    // 0 번째 부터 LIS 에 채워진 맨끝 원소까지 탐색
    int temp = Arrays.binarySearch(LIS, 0, size, nums[i]);
    if (temp < 0) temp = -temp - 1; // 탐색 실패: 음수값 => 삽입 위치로 환산
    LIS[temp] = nums[i];
  }
}
```

### 역추적
* **마지막 배열에 들어있는 원소들이 LIS 의 구성요소가 아님**
* 역추적 배열 *P* 를 생성하여 *P\[i]* 에 수열의 i 번째 원소가 **LIS** 내에서 위치하는 인덱스 저장 *(1<= x <= size)*
* *P* 를 거꾸로 순회하며 가장 처음 나타나는 *인덱스--* 가 **구성 원소**를 가리킴

```java
private static void backtrace(int idx, int num) { // 최초엔 수열 총 길이, LIS 길이
  // 원소를 모두 찾았으면 종료
  if (idx == 0) return;
  
  // 뒤에서 가장 처음 만난 P 의 값이 찾는 인덱스 값일 때
  if (P[idx] == num) {
    backtrace(idx - 1, num - 1); 다음 인덱스 값 찾기
    result[num - 1] = nums[idx - 1];
  }
  else backtrace(idx - 1, num); // 찾을 때 까지 순회
}
```

---

## Floyd-Warshall 알고리즘
* **모든 쌍 최단 경로**
* 각 점을 시작점으로 *Dijkstra* 구현시 복잡하고 비효율적
* **플로이드 알고리즘** 는 **O(n<sup>3</sup>)** 지만 간단하여 효율적
* *정점 i 에서 정점 j 로 직접 가는 경로 vs. 다른 정점들을 경유하는 경로*
* **인접 행렬** 사용

```java
dist = new int[N][N];

for (int i = 0; i < N; i++) {
  for (int j = 0; j < N; j++) {
    dist[i][j] = sc.nextInt();
    // 자기 자신으로의 인접 행렬이 아니고 인접해 있지 않다면 MAX 로 치환
    // 자기 자신으로는 0
    if (i != j && dist[i][j] == 0) dist[i][j] = MAX; // 계산해야 함으로 Integer.MAX_VALUE 는 쓰지 않는다
  }
}

// 경유지 -> 출발지 -> 도착지
for (int k = 0; k < N; k++) { // 경유지
  for (int i = 0; i < N; i++) { // 출발지
    if (k == i) continue;
    for (int j = 0; j < N; j++) { // 도착지
      if (i == j || k == j) continue;
      // 직접 경로 vs. 경유 경로
      if (dist[i][j] > dist[i][k] + dist[k][j]) dist[i][j] = dist[i][k] + dist[k][j];
    }
  }
}
```
