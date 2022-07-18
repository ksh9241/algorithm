import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class Main {
	private static String S = "";
	private static String T = "";
	private static int ANSWER = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		T = br.readLine();
		
		dfs(S,T);
			
		System.out.println(ANSWER);
	}
	
	public static void dfs (String s, String t) {
		if (s.length() == t.length()) {
			if (s.equals(t)) {
				ANSWER = 1;
			} 
			return;
		}
		

		// B...B : 뒤집은 뒤 제일 뒤에 B 제거
		// B...A : 뒤집은 뒤 맨 뒤 비값 제거, 맨뒤 에이값제거
		// A...A : 맨 뒤 A값 제거
		// A...B : X
		
		if (t.charAt(0) == 'B') {
			String result = new StringBuffer(t).reverse().substring(0, t.length()-1);
			dfs(s, result);
		}
		
		if (t.charAt(t.length()-1) == 'A') {
			String result = t.substring(0, t.length()-1);
			dfs(s, result);
		}
		
		return;
	}
}
