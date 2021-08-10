# Data Structures

## Stack
* ***L**ast-**I**n-**F**irst-**O**ut* (후입선출) 형태의 자료구조
* 스택에 저장된 자료는 선형 구조를 갖는다
  * 자료 간의 관계가 1 대 1 관계
* **top** 과 **bottom**
  * **top** 이 자료의 삽입/삭제가 이루어 지는 곳
* java.util.Stack
* **주요 메소드**
  * *push* : 자료 삽입
  * *pop* : 삽입한 자료의 역순으로 삭제
  * *peek* : 스택의 **top** 에 있는 item 반환
* 활용
  * 프로그램에서의 함수 호출과 복귀에 따른 수행 순서 관리 (function call)

---

## Queue
* ***F**irst-**I**n-**F**irst-**O**ut* (선입선출) 형태의 자료구조
* **front** 와 **rear**
  * **front** 는 삭제, **rear** 은 삽입
* java.util.Queue
* **주요 메소드**
  * *offer* = *enQueue* : 자료 삽입
  * *poll* = *deQueue* : 삽입한 자료 순으로 삭제
* 활용
  * 데이터를 한 곳에서 다른 한 곳으로 전송하는 동안 일시적으로 데이터를 보관하는 메모리의 영역 (버퍼)

### Priority Queue (우선순위 큐)
* **우선순위**를 가진 항목들을 저장하는 큐
* 선입선출 순서가 아니라 **우선순위**가 높은 순서대로 먼저 나가게 된다
* java.util.PriorityQueue
* **Heap** 을 이용한 자료구조
  * 완전 이진 트리로 구현된 자료구조로서, 키 값이 가장 큰 노드나 가장 작은 노드를 찾기에 유용한 자료구조
  * *Max Heap* : 가장 큰 값을 기준으로 먼저 나옴
  * *Min Heap* : 가장 작은 값을 기준으로 먼저 나옴
  * Heap 의 키를 우선순위로 활용하여 우선순위 큐 구현

---

## List
* 순서를 가진 데이터의 집합을 가리키는 추상 자료형
* **순차 리스트**: 배열을 기반으로 구현된 리스트
  * 자료의 삽입/삭제 연산 과정에서 연속적인 메모리 배열을 위해 원소들을 이동시키는 작업 필요
  * 원소의 개수가 많고 삽입/삭제 연산이 빈번할 수록 작업 소요시간 증가
  * 메모리의 정적 할당으로 낭비/초과 문제 발생
* **연결 리스트**: 메모리의 동적할당을 기반으로 구현된 리스트
  * 자료의 논리적인 순서와 메모리 상의 물리적인 순서가 일치하지 않음
  * 개별적으로 위치하고 있는 각 원소를 연결하여 하나의 전체적인 자료구조를 이룸
  * 메모리의 효율적인 사용 가능

### [Linked List (연결 리스트)](https://github.com/ljiwoo59/Algorithm_Study/tree/main/Algo_DataStructure/LinkedList)
```java
Class Node {
  public String data;
  public Node link;
  
  public Node(String data) {
    this.data = data;
  }
  
  public Node(String data, Node link) {
  this(data);
  this.link = link;
  }
}
```

* 구조
  * **Node**
    * 하나의 원소를 표현하는 building block
    * 데이터 필드
      * 원소의 값 저장
    * 링크 필드
      * 다음 노드의 참조값 저장
  * **Head**
    * 연결 리스트의 첫 노드에 대한 참조값 
* 종류
  * **단순 연결 리스트** : 링크를 1개만 유지하는 리스트 (다음 노드 정보)
    * *Node* 가 하나의 링크 필드에 의해 다음 노드와 연결되는 구조
    * *Head* 가 가장 앞의 노드를 가리키고, 링크 필드가 연속적으로 다음 노드를 가리킴
    * 링크 필드가 **NULL** 인 노드가 연결 리스트의 가장 마지막 노드
  * **이중 연결 리스트** : 링크를 2개 유지하는 리스트 (이전 노드와 다음 노드 정보)
    * 양쪽 방향으로 순회할 수 있도록 노드를 연결
  * **원형 연결 리스트** : 리스트의 끝과 처음을 연결

---

## [Tree](https://github.com/ljiwoo59/Algorithm_Study/tree/main/Algo_DataStructure/Tree)
* 원소들 간 *1 : N* 관계를 가지는 **비선형** 자료구조
* **계층형** 자료구조
* 구조
  * **Node**
    * 트리의 원소
    * **Root node** : 최상위 노드
    * *Sibling node* : 같은 부모 노드의 자식 노드들
    * **Leaf node** : 차수가 0 인, 자식 노드가 없는 노드
    * *조상 노드* : 루트 노드까지 이르는 경로에 있는 모든 노드들
    * *자손 노드* : 서브 트리에 있는 하위 레벨 노드들
    * *Subtree* : 부모 노드와 연결된 *edge* 를 끊었을 때 생성되는 트리
  * **Edge**
    * 노드와 노드를 연결하는 선
* **Degree**
  * 노드의 차수 : 노드에 연결된 자식 노드의 수
  * 트리의 차수 : 트리에 있는 노드의 차수 중에서 가장 큰 값
* **Depth**
  * 노드의 높이 : 루트에서 노드에 이르는 간선의 수
  * 트리의 높이 : 트리에 있는 노드의 높이 중에서 가장 큰 값

### Binary Tree (이진 트리)
![image](https://user-images.githubusercontent.com/54715744/128820739-133768e8-0560-4194-b7d2-894d376c8e03.png)

* 차수가 2인 트리
* 각 노드가 자식 노드를 최대한 2개 까지만 가질 수 있음
* 모든 노드들이 최대 2개의 서브트리를 가짐
* 높이 *i* 에서의 노드 최대 개수는 **2^*i*** 개
* **노드 번호의 성질**
  * *i* 노드의 부모 노드 : ***i / 2***
  * *i* 노드의 왼쪽 자식 노드 : ***2 \* i***
  * *i* 노드의 오른쪽 자식 노드 : ***2 \* i + 1***
  * 높이 *n* 의 시작 노드 : ***2^

#### Full Binary Tree
![image](https://user-images.githubusercontent.com/54715744/128820972-2dd1c66b-1858-4281-b53f-475b0d737f69.png)

* 모든 레벨에 노드가 포화 상태로 차 있는 이진 트리
* 높이가 *i* 일때, 노드 최대 개수는 **(2^*(h + 1)* - 1)**

#### Complete Binary Tree
![image](https://user-images.githubusercontent.com/54715744/128821186-cfd5afe9-4a84-4b57-84b0-6ad4c24189dc.png)

* 높이가 *i* 이고 노드 수가 *n* 개 일때, 포화 이진 트리의 노드 1 부터 n 까지 빈 자리가 없는 이진 트리

#### Skewed Binary Tree
![image](https://user-images.githubusercontent.com/54715744/128821354-8605bb60-f357-46c9-a0fc-f906b3ad0f1d.png)

* 높이 *i* 에 대한 최소 개수의 노드를 가지면서 한쪽 방향의 자식 노드 만을 가진 이진 트리

### Heap
* **Complete Binary Tree** 에 있는 노드 중에서 *키 값이 가장 큰 노드나 키 값이 가장 작은 노드*를 찾기 위해서 만든 자료 구조

![image](https://user-images.githubusercontent.com/54715744/128827236-f62a9529-5c03-4d52-b620-9d50b474cb63.png)

* **Max Heap**
  * 키 값이 **가장 큰** 노드를 찾기 위한 *완전 이진 트리*
  * 부모 노드의 키 값 > 자식 노드의 키 값
  * 루트 노드 : 키 값이 가장 큰 노드
* **Min Heap**
  * 키 값이 **가장 작은** 노드를 찾기 위한 *완전 이진 트리*
  * 부모 노드의 키 값 < 자식 노드의 키 값
  * 루트 노드 : 키 값이 가장 작은 노드
* 연산
  * **삽입**
    * 1. *Leaf node* 에 삽입할 자리 확장
    * 2. 확장한 자리에 삽입할 원소 저장
    * 3. 기준에 따라 부모 노드와 자리 바꿈
  * **삭제**
    * *Root node* 의 원소만 삭제 가능
    * 1. 루트 노드의 원소값 삭제
    * 2. 마지막 *leaf node* 삭제 후 원소를 *root node* 자리에 위치
    * 3. 기준에 따라 자식 노드와 자리 바꿈
* 힙 정렬
  * 힙 자료 구조를 이용해서 이진 트리와 유사한 방법으로 수행
  * *N* 개의 노드 삽입 연산 + *N* 개의 노드 삭제 연산 = 전체 정렬 (**O(N log N)**)

#### Priority Queue
* 노드 하나의 삽입/삭제의 시간 복잡도는 **O(log N)**
  * 최대값/최소값을 **O(1)** 에 구할 수 있음
  * 완전 정렬보다 관리 비용 적음
* **java.util.PriorityQueue()**
  * 원소들의 *natural Ordering* 에 따라 Heap 유지
  * 모든 원소는 **Comparable interface** 를 구현해야 함
* **java.util.PriorityQueue(Comparator comparator)**
  * 명시된 **Comparator** 의 구현에 따라 원소들의 순서 유지

