package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MeetingRoomTest {
	
	static class Meeting implements Comparable<Meeting> {
		int start, end;
		
		public Meeting(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) { // 기본은 오름차순
			int value = this.end - o.end; // 음수, 0 일시 그대로, 양수일시 교환	
			if (value != 0) return value;
			return this.start - o.start; // 종료 시간이 같다면 시작 시간이 빠른 순으로
		}

		@Override
		public String toString() {
			return "Meeting [start=" + start + ", end=" + end + "]";
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 회의 개수
		
		Meeting[] meetings = new Meeting[N];
		for (int i = 0; i < N; i++) {
			meetings[i] = new Meeting(sc.nextInt(), sc.nextInt());
		}
		
		for (Meeting meeting : getSchedule(meetings)) {
			System.out.println(meeting);
		}
		sc.close();
	}
	
	static ArrayList<Meeting> getSchedule(Meeting[] meetings) {
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		Arrays.sort(meetings); // 종료시간 기준 오름차순 정렬
		list.add(meetings[0]); // 첫 회의 추가
		
		for (int i = 1, size = meetings.length; i < size; i++) {
			if (list.get(list.size() - 1).end <= meetings[i].start)
				list.add(meetings[i]);
		}
		return list;
	}
}
