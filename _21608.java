import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class Main {
	// l : 최소값, r은 최대값
	static int n, l;
	static int[][] classRoom;
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
			position(idx);
		}
		
		System.out.println(answer());
	}

	
	static void position (int idx) {
		checkFriend friend = new checkFriend();
		boolean checkZero = false;
		
		int x = 0;
		int y = 0;
		for (int i = 0; i < classRoom.length; i++) {
			for (int j = 0; j < classRoom[i].length; j++) {
				
				// 빈 공간 찾기
				if (classRoom[i][j] == 0) {
					if (!checkZero) {
						checkZero = true;
						x = i;
						y = j;
					}
					
					checkFriend result = checkFriends(i, j, idx);
					if (friend.firendCount < result.firendCount || (friend.firendCount == result.firendCount && friend.locationCount < result.locationCount)) {
						friend = result;
						x = i;
						y = j;
					}
				}
			}
		}
		classRoom[x][y] = idx;
	}
	
	// 빈 공간에서 인접한 친구 및 공간 찾기
	static checkFriend checkFriends (int x, int y, int idx) {
		List<Integer> myFriends = list.get(idx);
		boolean [][] visit = new boolean[n][n];
		
		int friendsCount = 0;
		int locationCount = 0;
		
		// 인접한 공간 체크
		for (int i = 0; i < 4; i++) {
			int nxtX = x + dx[i];
			int nxtY = y + dy[i];
			
			if (nxtX >= 0 && nxtY >= 0 && nxtX < n && nxtY < n) {
				
				// 내가 원하는 친구들 주변에 확인
				for (int j = 0; j < myFriends.size(); j++) {
					
					// 원하는 친구가 있을경우 친구 수 증가
					if (classRoom[nxtX][nxtY] == myFriends.get(j)) {
						friendsCount++;
					}
					// 친구가 없고 빈공간 증가한 곳이 아닌 경우 빈 공간 수 증가
					else if (classRoom[nxtX][nxtY] == 0 && !visit[nxtX][nxtY]){
						visit[nxtX][nxtY] = true;
						locationCount++;
					}
				}
			}
		}
		
		return new checkFriend(friendsCount, locationCount);
	}
	
	static void endLoop (int idx) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (classRoom[i][j] == 0) {
					classRoom[i][j] = idx;
				}
			}
		}
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
	
	static class checkFriend {
		int firendCount;
		int locationCount;
		
		public checkFriend () {};
		
		public checkFriend (int firendCheck , int locationCount) {
			this.firendCount = firendCheck;
			this.locationCount = locationCount;
		}
		
	}
}