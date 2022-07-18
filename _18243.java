import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][n];
		
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                if (i == j) continue;
                arr[i][j] = 1_000_000_000;
            }
        }
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
            
			arr[a - 1][b - 1] = 1;
			arr[b - 1][a - 1] = 1;
		}
		
		// [2][3] > [2][1] + [1][3]
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (arr[i][j] > arr[i][k] + arr[k][j]) {
						arr[i][j] = arr[i][k] + arr[k][j];
					}
				}
			}
		}
		
		boolean visit = true;
        for (int[] i : arr) {
            for (int j : i) {
                if (j > 6) visit = false;
            }
        }
        
        if (visit) {
            System.out.println("Small World!");
        } else {
            System.out.println("Big World!");
        }
	}
}