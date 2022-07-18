import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N,K;
	static int[][] map;
	static Deque<Location> dq = new LinkedList<Main.Location>();
	static int[] direction = new int[10001];
	static Location location = null;
	
	static int snakeDir = 3;
	
	static int[] dx = {-1, 0, 1, 0}; // 상 좌 하 우 순서
	static int[] dy = {0, -1, 0, 1};
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new int[N + 2][N + 2];
		dq.addFirst(new Location(1, 1)); // 처음 뱀 머리, 꼬리
		map[1][1] = 1;
		
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			map[x][y] = 2; // 사과 위치
		}
		
		int l = Integer.parseInt(br.readLine());
		for (int i = 0; i < l; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int idx = Integer.parseInt(st.nextToken());
			String s = st.nextToken();
			
			if ("L".equals(s)) { // 왼쪽으로 90도 -> 1
				direction[idx] = 1;
			} else {
				direction[idx] = 2; // 오른쪽으로 90도 -> 2
			}
		}
		
		int time = 0;
		while (true) {
			time++;
			
			// 머리를 늘려 위치시킬 다음 칸
			int nx = dq.peekFirst().getX() + dx[snakeDir];
			int ny = dq.peekFirst().getY() + dy[snakeDir];
			
			// 벽에 부딪히면
			if ( nx < 1 || ny < 1 || nx > N || ny > N || map[nx][ny] == 1) {
				break;
			}
			
			if (map[nx][ny] == 2) {
				map[nx][ny] = 1;
			} else {
				map[nx][ny] = 1;
				resetLocation();
			}
			dq.addFirst(new Location(nx, ny));
			
			if (direction[time] == 1) {
				snakeDir = (snakeDir + 1) % 4;
			} else if (direction[time] == 2) {
				snakeDir = (snakeDir + 3) % 4;
			}
		}
		System.out.println(time);
	}
	
	static void resetLocation () {
		location = dq.pollLast();
		map[location.getX()][location.getY()] = 0;
	}
	
	public static class Location {
		int x;
		int y;
		
		public Location (int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
	}
}