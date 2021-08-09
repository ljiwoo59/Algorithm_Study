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


