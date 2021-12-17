import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int ARR[];
	static boolean VISIT[];
	static int N,K;
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		ARR = new int[N * 2];
		VISIT = new boolean[N * 2];
		st = new StringTokenizer(br.readLine());
		
		// 컨베이어 벨트 내구도 초기화
		for (int i = 0; i < ARR.length; i++) {
			ARR[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		int head = 0;
		int tail = N - 1;
		while (K > 0) {
			
			// 컨베이어 벨트에 로봇이 없으며, 내구도가 0보다 클 경우
			if (ARR[head] > 0 && !VISIT[head]) {
				// 로봇올리고
				VISIT[head] = true;
				// 내구도 1감소
				ARR[head]--;
			}
			
			// 컨베이어 벨트 끝에 로봇이 있다면 로봇 내리기 ( 위쪽에 있는 컨베이어벨트 맨 끝 [tail - N] )
			if (VISIT[tail]) VISIT[tail] = false;
			
			
			
			// 로봇이 한칸 전진할 수 있는지 확인
			for (int i = 1; i < N; i++) {
				int idx = tail - i < 0 ? (N * 2) - 1 : tail - i;
				
				if (VISIT[idx]) {
					int chk = idx + 1 >= (N * 2) ? 0 : idx + 1;
					if (!VISIT[chk] && ARR[chk] != 0) {
						VISIT[idx] = false;
						VISIT[chk] = true;
						ARR[chk]--;
					}
				}
			}
			
			// 전체 로봇이 한칸씩 전진 (수정 필요)
			for (int i = 1; i < N; i++) {
				
			}
			
			// 앞에 컨베이어 벨트 이동 방향
			int nxtH = head - 1 < 0 ? (N * 2) - 1: head - 1;
			int nxtT = tail - 1 < 0 ? (N * 2) - 1 : tail - 1;
			
			head = nxtH;
			tail = nxtT;
			
			answer++;
			
			int check = 0;
			for (int i = 0; i < ARR.length; i++) {
				if (ARR[i] == 0) check++;
			}
			
			if (K <= check) {
				K = 0;
			}
		}
		
		System.out.println(answer);
	}
}