import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int N;
	static int COUNT = 0;
	static int[][] map;
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 2][N + 2]; // IndexOutOfBoundException 방지하기 위해서 1칸 크게 설정
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		recursion(new Location(1,1), new Location(1,2));
		
		System.out.println(COUNT);
	}
	
	static void recursion (Location head, Location tail){
		int headX = head.x;
		int headY = head.y;
		int tailX = tail.x;
		int tailY = tail.y;
		
		// 목적지에 무사도착 카운트 증가
		if (tailX == N && tailY == N) {
			COUNT++;
			return;
		}
		
		// 파이프 상태가 가로일 경우
		if (headX == tailX) {
			// 가로로 이동할 공간이 있을 경우
			if (checkSpace("right", tailX, tailY)) {
				recursion(new Location(headX, headY + 1), new Location(tailX, tailY + 1));
			}
			if (checkSpace("diagonal", tailX, tailY)) {
				recursion(new Location(headX, headY + 1), new Location(tailX + 1, tailY + 1));
			}
		}
		// 파이프 상태가 세로일 경우
		else if (headY == tailY) {
			if (checkSpace("bottom", tailX, tailY)) {
				recursion(new Location(headX + 1, headY), new Location(tailX + 1, tailY));
			}
			
			if (checkSpace("diagonal", tailX, tailY)) {
				recursion(new Location(headX + 1, headY), new Location(tailX + 1, tailY + 1));
			}
		}
		// 파이프 상태가 대각일 경우
		else {
			if (checkSpace("right", tailX, tailY)) {
				recursion(new Location(headX + 1, headY + 1), new Location(tailX, tailY + 1));
			}
			
			if (checkSpace("bottom", tailX, tailY)) {
				recursion(new Location(headX + 1, headY + 1), new Location(tailX + 1, tailY));
			}
			
			if (checkSpace("diagonal", tailX, tailY)) {
				recursion(new Location(headX + 1, headY + 1), new Location(tailX + 1, tailY + 1));
			}
		}
	}
	
	static boolean checkSpace(String type, int tailX, int tailY) {
		boolean result = false;
		
		switch(type) {
		case "right" : 
			if (tailY + 1 <= N && map[tailX][tailY + 1] == 0) result = true;
			break;
		case "bottom" :
			if (tailX + 1 <= N && map[tailX + 1][tailY] == 0) result = true;
			break;
		case "diagonal" :
			if (tailX + 1 <= N && tailY + 1 <= N
				&& map[tailX + 1][tailY] == 0 && map[tailX][tailY + 1] == 0
				&& map[tailX + 1][tailY + 1] == 0) {
				result = true;
			}
			break;
		}
		
		return result;
	}
	
	static class Location {
		int x;
		int y;
		
		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}