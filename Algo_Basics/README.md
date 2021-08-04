# 입/출력 처리
## 표준 입출력
* **System.in**
* **System.out**
* **System.err**

### 표준 입출력의 대상변경
* **System.setIn()**
* **System.setOut()**
* **System.setErr()**
* 콘솔이 아닌 파일을 입출력으로 받고 싶을 때 사용

## java.util.Scanner
* 파일, 입력 스트림 등에서 데이터를 읽어 구분자로 토큰화 하고 다양한 타입으로 형변환 하여 리턴해주는 클래스
  * **Scanner(File source)**
  * **Scanner(InputStream source)**
  * **Scanner(String source)**
* 데이터 형변환으로 인한 편리함
* 대량의 데이터 처리 시 수행시간이 **비효율적**

### 주요 메소드
|메소드|설명|
|------|----|
|*int* **nextInt()**|유효 문자열 후 *White space* 를 만나면 처리|
|*double* **nextDouble()**|유효 문자열 후 *White space* 를 만나면 처리|
|*String* **next()**|유효 문자열 후 *White space* 만나면 처리|
|*String* **nextLine()**|개행을 만나면 처리|
||**next()** 와 달리 문자열 안에 *White space* 포함 가능|

## java.io.BufferedReader
* 필터 스트림 유형
* *line* 단위로 문자열 처리 기능
  * **readLine()**
* 대량의 데이터 처리 시 수행시간이 **효율적**

## java.util.StringTokenizer
* *String* 을 **delimiter** 기준으로 토큰화

```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
StringTokenizer st = new StringTokenizer(br.readLine(), " ");
int i = Integer.parseInt(st.nextToken());
```
## java.lang.StringBuilder
* 문자열의 조작을 지원하는 클래스
* 상수로 취급되는 문자열을 조작 시마다 새로운 문자열이 생성되는 것을 방지
  * **append()**
  * **toString()**
  * **setLength(*int* len)**

---

# Recursion
* 반복과 재귀는유사한 작업 수행
* 주어진 문제의 해를 구하기 위해 동일하면서 **더 작은 문제의 해**를 이용하는 방법
  * 하나의 큰 문제를 해결할 수 있는 더 작은 문제로 쪼개고 결과들을 결합
* 함수 내부에서 직접 혹은 간접적으로 자기 자신을 호출
* 재귀 호출은 반복적인 스택의 사용으로 메모리 및 속도에서 성능 저하 발생

### Hanoi 탑
```java
public class HanoiTest {

	private static void hanoi(int n, int start, int temp, int end) {
		if (n == 0) return;
		// 자신 위쪽의 n-1 개 원판 들어내기 : 임시기둥으로 옮기기
		hanoi(n - 1, start, end, temp);
		// 자신읜 원판 옮기기 : 목적기둥
		System.out.println(n + ":" + start + ">" + end);
		// 들어냈던 임시기둥의 n-1개 원판 자신위에 쌓기 : 목적기둥
		hanoi(n - 1, temp, start, end);
	}
```
