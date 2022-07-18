import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
	static int N,M,X,Y,K;
	static int[][] map;
	static int[][] dice = new int[2][4];
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());	// 세로크기
		M = Integer.parseInt(st.nextToken());	// 가로크기
		X = Integer.parseInt(st.nextToken());	// 주사위 x좌표
		Y = Integer.parseInt(st.nextToken());	// 주사위 y좌표
		
		// 1 : 동쪽, 2 : 서쪽, 3 : 북쪽, 4 : 남쪽
		K = Integer.parseInt(st.nextToken());	// 명령 수
		
		map = new int[N][M];
		
		// map 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		while (st.hasMoreTokens()) {
			int commend = Integer.parseInt(st.nextToken());
			
			boolean check = moving(commend);
			if (check) {
				changeValue();
				printInfo();
			}
		}
	}
	
	static void changeValue () {
		if (map[X][Y] == 0) {
			map[X][Y] = dice[0][3]; // 주사위 바닥면에 있는 값을 지도에 복사한다.
		}
		else {
			dice[0][3] = map[X][Y];
			dice[1][3] = map[X][Y];
			
			map[X][Y] = 0;
		}
	}
	
	static void printInfo () {
		System.out.println(dice[0][1]);
	}
	
	static boolean moving (int commend) {
		int temp = 0;
		boolean movingCheck = false;
		switch (commend) {
		case 1 : 
			if (Y + 1 < M) {
				temp = dice[1][3];
				
				for (int i = 2; i >= 0; i--) {
					dice[1][i + 1] = dice[1][i];
				}
				dice[1][0] = temp;
				
				dice[0][1] = dice[1][1];
				dice[0][3] = dice[1][3];
				
				Y++; // 주사위 위치 이동
				movingCheck = true;
			}
			break;
			
		case 2 :
			if (Y - 1 >= 0) {
				temp = dice[1][0];
				
				for (int i = 1; i < 4; i++) {
					dice[1][i - 1] = dice[1][i];
				}
				dice[1][3] = temp;
				
				dice[0][1] = dice[1][1];
				dice[0][3] = dice[1][3];
				
				Y--; // 주사위 위치 이동
				movingCheck = true;
			}
			break;
			
		case 3 :
			if (X - 1 >= 0) {
				temp = dice[0][0];
				
				for (int i = 1; i < 4; i++) {
					dice[0][i - 1] = dice[0][i];
				}
				dice[0][3] = temp;
				
				dice[1][1] = dice[0][1];
				dice[1][3] = dice[0][3];
				
				X--;  // 주사위 위치 이동
				movingCheck = true;
			}
			break;
			
		case 4 :
			if (X + 1 < N) {
				temp = dice[0][3];
				
				for (int i = 2; i >= 0; i--) {
					dice[0][i + 1] = dice[0][i];
				}
				dice[0][0] = temp;
				
				dice[1][1] = dice[0][1];
				dice[1][3] = dice[0][3];
				
				X++; // 주사위 위치 이동
				movingCheck = true;
			}
			break;
		}
		
		return movingCheck;
	}
}