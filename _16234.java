import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class Main {
	// l : 최소값, r은 최대값
	static int n, l, r;
	static int[][] arr;
	static boolean[][] visit;
	static int answer = 0;
	
	// 오 -> 아 -> 왼 -> 위
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	static List<location> list;
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		arr = new int[n][n];
		visit = new boolean[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (true) {
			visit = new boolean[n][n];
			if (!check()) {
				answer++;
			} else {
				break;
			}
		}
		System.out.println(answer);
	}
	
	static boolean check () {
		boolean check = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visit[i][j]) {
					list = new ArrayList<location>();
					list.add(new location(i, j));
					int sum = checkPerson(i, j, 0);
					if (list.size() > 1) {
						change(sum);
						check = false;
					}
				}
			}
		}
		return check;
	}
	
	static int checkPerson(int x, int y, int sum) {
		visit[x][y] = true;
		sum = arr[x][y];
		
		for (int i = 0; i < 4; i++) {
			int nxtX = x + dx[i];
			int nxtY = y + dy[i];
			
			if (nxtX >= 0 && nxtX < n && nxtY >=0 && nxtY < n && !visit[nxtX][nxtY]) {
				int checkNum = Math.abs(arr[x][y] - arr[nxtX][nxtY]);
				if (checkNum >= l && checkNum <= r) {
					list.add(new location(nxtX, nxtY));
					sum += checkPerson(nxtX, nxtY, sum);
				}
			}
		}
		return sum;
	}
	
	static void change (int sum) {
		int avg = sum / list.size();
		for (location l : list) {
			arr[l.x][l.y] = avg;
		}
	}
	
	static class location {
		int x;
		int y;
		
		public location(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return this.x + " " + this.y;
		}
	}
}