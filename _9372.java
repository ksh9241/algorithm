import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		
		for (int k = 0; k < t; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			List<List<Integer>> list = new ArrayList<>();
			for (int i = 0; i <= n; i++) {
				list.add(new ArrayList<Integer>());
			}
			
			// 나라 별 간선 등록
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				list.get(a).add(b);
				list.get(b).add(a);
			}
			boolean visit[] = new boolean[n + 1];
			
			Stack<Integer> s = new Stack<Integer>();
			s.push(list.get(1).get(0));
			int answer = 0;
			visit[list.get(1).get(0)] = true;
			
			while (!s.empty()) {
				int num = s.pop();
				
				for (int j = 0; j < list.get(num).size(); j++) {
					int listNum = list.get(num).get(j);
					if (!visit[listNum]) {
						s.push(listNum);
						visit[listNum] = true;
						answer ++;
					}
				}
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}