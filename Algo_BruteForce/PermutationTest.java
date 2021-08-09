import java.io.*;
import java.util.*;

public class PermutationTest {
	static int N, R;
	static int[] nums, selected;
	static boolean[] isSelected;
	static int totalCount;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 총 숫자 개수
		R = Integer.parseInt(st.nextToken()); // 뽑을 숫자 개수
		nums = new int[N];
		selected = new int[R]; // 뽑은 숫자
		isSelected = new boolean[N]; // 수가 선택 되었는지 안되었는지
		st =  new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		permutation(0);
		
		System.out.println(totalCount);
		br.close();

	}
	private static void permutation(int cnt) {
		if (cnt == R) {
			totalCount++;
			System.out.println(Arrays.toString(selected));
			return;
		}
		for (int i = 0; i < N; i++) {
			if (isSelected[i]) continue;
			isSelected[i] = true;
			selected[cnt] = nums[i];
			permutation(cnt + 1);
			isSelected[i] = false;
		}
	}

}
