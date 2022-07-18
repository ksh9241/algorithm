import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split("\\-");
		
		int answer = 0;
		for (int i = 0; i < str.length; i++) {
			
			String[] plus = str[i].split("\\+");
			int num = 0;
			for (int j = 0; j < plus.length; j++) {
				num += Integer.parseInt(plus[j]);
			}
			
			if (i == 0) {
				answer += num;
			} else {
				answer -= num;
			}
		}
		
		System.out.println(answer);
		
	}
}