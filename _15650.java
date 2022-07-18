import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int N, M;
	static boolean visit[];
	static int arr[];
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visit = new boolean[N + 1];
		arr = new int[M];
		
		permutation(0, 1);
		
	}
	
	static void permutation(int depth, int num) {
		if (M == depth) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i = num; i <= N; i++) {
			if (!visit[i]) {
				visit[i] = true;
				arr[depth] = i;
				permutation(depth + 1, i + 1);
				visit[i] = false;
			}
		}
	}
}