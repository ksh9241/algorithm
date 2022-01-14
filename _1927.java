import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
public class Main {
	static int N;
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		for (int i = 0; i < N; i++) {	
			int num = Integer.parseInt(br.readLine());
			if (num == 0) {
				// 리스트 안에 값이 있을 경우 최소값을 출력
				if (pq.size() > 0) {
					System.out.println(pq.poll());
				} 
				// 리스트가 없는데 출력을 하라고 하는 경우
				else {
					System.out.println(0);
				}
			} else {
				pq.add(num);
			}
		}
	}
}