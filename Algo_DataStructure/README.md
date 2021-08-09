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
* ***F**irst-**I**n-***F**irst-**O**ut* (선입선출) 형태의 자료구조
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
* 순차 리스트: 배열을 기반으로 구현된 리스트
  * 자료의 삽입/삭제 연산 과정에서 연속적인 메모리 배열을 위해 원소들을 이동시키는 작업 필요
  * 원소의 개수가 많고 삽입/삭제 연산이 빈번할 수록 작업 소요시간 증가
  * 메모리의 정적 할당으로 낭비/초과 문제 발생
* 연결 리스트: 메모리의 동적할당을 기반으로 구현된 리스트
  * 자료의 논리적인 순서와 메모리 상의 물리적인 순서가 일치하지 않음
  * 개별적으로 위치하고 있는 각 원소를 연결하여 하나의 전체적인 자료구조를 이룸
  * 메모리의 효율적인 사용 가능

### [Linked List (연결 리스트)](https://github.com/ljiwoo59/Algorithm_Study/blob/main/Algo_DataStructure/SingleLinkedList.java)
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
