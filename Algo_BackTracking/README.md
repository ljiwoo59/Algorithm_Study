# BackTracking
* **퇴각검색**
  * *완전 탐색*을 하면 가능성이 전혀 없는 선택지들도 모두 검색해야 하므로 비효율적
* 어떤 노드의 **유망성**을 점검한 후에 유망하지 않다고 결정되면 그 노드의 부모로 돌아가 다음 자식 노드로 간다
  * 상태 공간트리의 **깊이 우선 검색** 실시
  * 특정 조건을 만족하는 경우만 살펴본다

## BackTracking vs. DFS
* 어떤 노드에서 출발하는 경로가 해결책으로 이어질 것 같지 않으면 더 이상 그 경로를 따라가지 않음
  * **Pruning**
* *완전 탐색*이 모든 경로를 추적하는데 비해 *백트래킹*은 불필요한 경로를 조기에 차단한다
* 일반적으로 경우의 수가 줄어들지만 *최악의 경우*에는 여전히 지수함수 시간(Exponential Time)을 요한다

## Example
### [N-Queen](https://github.com/ljiwoo59/Algorithm_Java/blob/master/Baekjoon/gold/B9663.java)
* *1차원 배열* 을 이용하여 각 *col index* 당 *row index*를 저장
  * 한 행에 하나의 퀸만 놓는다
* 1 부터 N 까지의 *row index* 를 시도해 봄으로서 유망성 체크
  * 이전 퀸들과 행, 열이 같거나 대각선이면 종료

### [부분집합의 합](https://github.com/ljiwoo59/Algorithm_Study/blob/main/Algo_BackTracking/SubsetSum.java)
* *완전 탐색* 이용 시, 집합의 모든 부분집합을 생성한 후 각 부분집합의 합을 계산
* *sum* 매개변수를 이용하여 조건 만족 시 더 이상 부분집합을 고려하지 않고 종료하는 조건 추가
  * *sum* == *S* 이면 return
  * *sum* > *S* || *cnt* == *N* 이면 return
