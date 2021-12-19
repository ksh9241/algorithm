import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int ARR[];
	static boolean VISIT[];
	static int N, K, head, tail;
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		ARR = new int[N * 2];
		VISIT = new boolean[N];
		st = new StringTokenizer(br.readLine());
		
		// 컨베이어 벨트 내구도 초기화
		for (int i = 0; i < ARR.length; i++) {
			ARR[i] = Integer.parseInt(st.nextToken());
		}
		
		head = 0;
		tail = N;
		
		int answer = 0;
		while (K > 0) {
			answer++;
			movingBelt();
			movingRobot();
			newRobot();
		}
		
		System.out.println(answer);
	}
	
	// 로봇이 한칸 전진할 수 있는지 확인 ( 로봇이 스스로 이동하는 경우 )
	static void movingRobot () {
		for (int i = N - 2; i >= 0; i--) {
			if (VISIT[i]) {
				int next = head + i + 1;
				if (next >= N * 2) next -= N * 2;
				
				if (!VISIT[i + 1] && ARR[next] >= 1) {
					VISIT[i] = false;
					
					if (i + 1 < N - 1) {
						VISIT[i + 1] = true;
					}
						ARR[next]--;
					
					if (ARR[next] == 0) K--;
				}
			}
		}
	}
	
	// 벨트 위치 이동
	static void movingBelt () {
		int nxtH = head - 1 < 0 ? (N * 2) - 1: head - 1;
		int nxtT = tail - 1 < 0 ? (N * 2) - 1 : tail - 1;
		head = nxtH;
		tail = nxtT;
		
		for (int i = N - 2; i >= 0; i--) {
			if (VISIT[i]) {
				VISIT[i] = false;
				
				if (i + 1 < N - 1)	VISIT[i + 1] = true;
			}
		}
	}
	
	static void newRobot() {
		if (ARR[head] > 0 && !VISIT[0]) {
			VISIT[0] = true;
			ARR[head]--;
			if (ARR[head] == 0) K--;
		}
	}
	
}