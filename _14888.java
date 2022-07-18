import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] ARR, cal;
	static boolean [] visit;
	
	static int MAX = Integer.MIN_VALUE;
	static int MIN = Integer.MAX_VALUE;
	
	static List<Integer> list;
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		ARR = new int [N];
		cal = new int [N - 1];
		visit = new boolean [N - 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			ARR[i] = Integer.parseInt(st.nextToken());
		}
		
		
		st = new StringTokenizer(br.readLine());
		list = new ArrayList<Integer>();
		
		for (int i = 0; i < 4; i++) {
			int x = Integer.parseInt(st.nextToken());
			
			while (x-- > 0) {
				list.add(i);
			}
		}
		
		permutation(0);
		System.out.println(MAX);
		System.out.println(MIN);
	}
	
	static void permutation (int depth) {
		if (N - 1 == depth) {
			int num = ARR[0];
			for (int i = 0; i < N - 1; i++) {
				switch(cal[i]) {
				case 0 : num += ARR[i + 1];	break;
				case 1 : num -= ARR[i + 1];	break;
				case 2 : num *= ARR[i + 1];	break;
				case 3 : num /= ARR[i + 1];	break;
				}
			}
			
			MAX = Math.max(MAX, num);
			MIN = Math.min(MIN, num);
			return;
		}
		
		for (int i = 0; i < N - 1; i++) {
			if (!visit[i]) {
				visit[i] = true;
				cal[depth] = list.get(i);
				permutation(depth + 1);
				visit[i] = false;
			}
		}
	}
}