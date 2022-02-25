import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int T,N,idx;
	static int[] preorderArr, inorderArr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		// 테스트케이스 개수
		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			
			preorderArr = new int[N + 1];
			inorderArr = new int[N + 1];
			
			StringTokenizer pre = new StringTokenizer(br.readLine());
			StringTokenizer in = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				preorderArr[j] = Integer.parseInt(pre.nextToken());
				inorderArr[j] = Integer.parseInt(in.nextToken());
			}
			
			traversal(0,0,N);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void traversal(int root, int s, int e) {
		int rootIdx = preorderArr[root];
		for (int i = s; i < e; i++) {
			if (inorderArr[i] == rootIdx) {
				traversal(root + 1, s, i);
				traversal(root + 1 + i - s, i + 1, e);
				sb.append(rootIdx + " ");
			}
		}
	}
}