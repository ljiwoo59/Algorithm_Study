# 완전 탐색
* **Brute-foce** 혹은 **Generate-and-test** 기법
* 문제의 해법으로 생각할 수 있는 모든 경우의 수를 나열해보고 확인하는 기법
* 경우의 수가 작을 때 유용
* *조합적 문제*와 연관
  * **순열 (permutation), 조합 (combination), 부분집합 (subset)**

## Permutation
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
## Combination
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
