import java.util.Scanner;

public class SubsetSum {
	static int N, totalCnt, S;
	static int[] input;
	static boolean[] isSelected;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = sc.nextInt(); // 부분집합의 합
		input = new int[N];
		isSelected = new boolean[N];
		totalCnt = 0;
		
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		
		generateSubset(0, 0);
		System.out.println("경우의 수 : " + totalCnt);
		sc.close();
	}
	private static void generateSubset(int cnt, int sum) { // cnt : 부분집합을 처리하기 위해 고려된 원소수, sum : 기존까지 부분집합 구성원소들의 합
		if (sum == S) {
			totalCnt++;
			for (int i = 0; i < N; i++) {
				if (isSelected[i])
					System.out.print(input[i] + " ");
			}
			System.out.println();
			return ;
		}
		if (sum > S || cnt == N) return;
		
		// 현재 원소를 부분집합에 넣기
		isSelected[cnt] = true;
		generateSubset(cnt + 1, sum + input[cnt]);
		// 현재 원소를 부분집합에 넣지 않기
		isSelected[cnt] = false;
		generateSubset(cnt + 1, sum);
		
	}

}
