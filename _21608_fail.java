import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class Main {
	// l : 최소값, r은 최대값
	static int n, l;
	static int[][] classRoom;
	static int[][] visit;
	static int[][] locationCount;
	static List<List<Integer>> list = new ArrayList<>();
	static final int friends = 4;
	
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
	
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		
		l = (n * n);
		classRoom = new int[n][n];
		
		
		for (int i = 0; i <= l; i++) {
			list.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < friends; j++) {
				list.get(idx).add(Integer.parseInt(st.nextToken()));
			}
			position(idx, list.get(idx));
		}
		System.out.println(answer());
	}
	
	static void position (int idx, List<Integer> friendsList) {
		visit = new int[n][n];
		locationCount = new int[n][n];
		boolean noFriends = true;
		
		for (int k = 0; k < friendsList.size(); k++) {
			int bf = friendsList.get(k);
			
			for (int i = 0; i < classRoom.length; i++) {
				for (int j = 0; j < classRoom[i].length; j++) {
					if (classRoom[i][j] == bf) {
						noFriends = false;
						checkPosition(i, j);
					}
				}
			}
		}
		checkLocation(idx, noFriends);
	}
	
	// 인접한 빈 칸이 최대한 많은 곳에 넣기
	static void checkLocation (int idx, boolean friednsCheck) {
		for (int i = 0; i < classRoom.length; i++) {
			for (int j = 0; j < classRoom[i].length; j++) {
				for (int k = 0; k < 4; k++) {
					int nxtX = i + dx[k];
					int nxtY = j + dy[k];
					
					if (nxtX >= 0 && nxtY >= 0 && nxtX < n && nxtY < n && classRoom[nxtX][nxtY] == 0) {
						if (friednsCheck)	visit[i][j]++;
						else if (classRoom[i][j] == 0)locationCount[i][j]++;
					}
				}
			}
		}
		check(idx);
	}
	
	// 내가 좋아하는 친구의 주변의 빈 자리 찾기
	static void checkPosition (int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nxtX = x + dx[i];
			int nxtY = y + dy[i];
			
			if (nxtX >= 0 && nxtY >= 0 && nxtX < n && nxtY < n) {
				// 빈자리의 경우
				if(classRoom[nxtX][nxtY] == 0) {
					visit[nxtX][nxtY]++;
				}
			}
		}
	}
	
	// 빈자리의 친구의 수가 동일할 때 인접한 빈 칸이 제일 많은 곳으로 지정해야함. (수정 필요) 
	static void check (int idx) {
		int maxNum = 0;
		int maxX = 0;
		int maxY = 0;
		
		boolean checkChg = true;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				
				if (maxNum < visit[i][j]) {
					checkChg = false;
					maxNum = visit[i][j];
					maxX = i;
					maxY = j;
				}
				// 친구의 수가 동일하면 제일 빈칸이 많은 곳으로
				else if (maxNum == visit[i][j]) {
					if (locationCount[maxX][maxY] < locationCount[i][j]) {
						checkChg = false;
						maxX = i;
						maxY = j;
					}
				}
			}
		}
		
		if(checkChg) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (checkChg && classRoom[i][j] == 0) {
						maxX = i;
						maxY = j;
						checkChg = false;
					}
				}
			}
		}
		classRoom[maxX][maxY] = idx;
	}
	
	static int answer() {
		int answer = 0;
		
		for (int i = 0; i < classRoom.length; i++) {
			for (int j = 0; j < classRoom[i].length; j++) {
				int idx = classRoom[i][j];
				int result = 0;
				for (int k = 0; k < list.get(idx).size(); k++) {
					for (int a = 0; a < 4; a++) { 
						int nxtX = i + dx[a];
						int nxtY = j + dy[a];
						
						if (nxtX >= 0 && nxtY >= 0 && nxtX < n && nxtY < n) {
							
							if (classRoom[nxtX][nxtY] == list.get(idx).get(k)) result++;
						}
					}
				}
				
				switch(result) {
				case 0 : answer += 0;
				break;
				case 1 : answer += 1;
				break;
				case 2 : answer += 10;
				break;
				case 3 : answer += 100;
				break;
				case 4 : answer += 1000;
				break;
				}
			}
		}
		return answer;
	}
}