import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] visit;

	static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		visit = new boolean[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		combination(1, 0, new ArrayList<int[]>());
		System.out.println(answer);

	}

	static void combination(int x, int depth, List<int[]> chichen) {
		// 치킨집 개수가 맞으면 탐색
		if(depth == M) {
			quest(chichen);
			return;
		}

		for (int i = x; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 순열로만 하는게 아닌 이유는 치킨집만 담아두고 처리하기 위함
				if (!visit[i][j] && map[i][j] == 2) {
					int[] a = {i,j}; // idx 0 == i, idx 1 == j
					chichen.add(a);
					visit[i][j] = true;
					combination(i, depth + 1, chichen);
					chichen.remove(a);
					visit[i][j] = false;
				}
			}
		}
	}

	static void quest(List<int[]> chichen) {
		int totalDistance = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == 1) {
					totalDistance += minDistance(chichen, i, j);
				}
			}
		}

		answer = Math.min(answer, totalDistance);
	}

	static int minDistance(List<int[]> chichen, int x, int y) {
		int minVal = Integer.MAX_VALUE;
		int calcNum = 0;
		for (int i = 0; i < chichen.size(); i++) {
			int[] val = chichen.get(i);
			calcNum = Math.abs(val[0] - x) + Math.abs(val[1] - y);
			minVal = Math.min(minVal, calcNum);
		}
		return minVal;
	}
}