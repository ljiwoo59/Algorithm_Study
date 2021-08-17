# Divide and Conquer Algorithm
* **Divide** : 해결할 문제를 여러 개의 작은 부분으로 *분할*한다
* **Conquer** : 나눈 작은 문제를 각각 해결한다
* **Combine** : (필요하다면) 해결된 해답을 모은다
* 시간복잡도는 **O(*log n*)**

## Example
### Binary Search
* 자료의 가운데에 있는 항목의 키 값과 비교하여 다음 검색의 위치를 결정하고 검색을 계속 진행하는 방법
    * 목적 키를 찾을 때까지 이진 검색을 순환적으로 반복 수행함으로써 검색 범위를 반으로 줄여가며 빠르게 검색 수행
* 자료가 *정렬*된 상태여야 한다

#### java.util.Arrays.binarySearch
* 이진탐색 API
* int *binarySearch(int[] a, int key)*
* int *binarySearch(int[] a, int fromIndex, int toIndex, int key);
