import java.io.*;
import java.util.*;

// 조합(nCr) : n 개 중에 r 개 선택, 순서 없음

public class CombinationTest {
	static int N, R;
	static int[] nums, selected;
	static int totalCount;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 총 숫자 개수
		R = Integer.parseInt(st.nextToken()); // 뽑을 숫자 개수
		nums = new int[N];
		selected = new int[R]; // 뽑은 숫자
		st =  new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		combination(0, 0);
		
		System.out.println(totalCount);
		br.close();
	}

	private static void combination(int cnt, int start) { // cnt: 현재 뽑은 숫자 개수, start: 선택 시작 위치
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

}
