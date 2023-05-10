import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N; // 물건 개수
    static int K; // 버틸 수 있는 무게
    static int[] dp = new int[100_001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken()); // 물건의 무게
            int v = Integer.parseInt(st.nextToken()); // 가치
            if (i == 0) {
                dp[w] = v;
                continue;
            }

            dp(w, v);
        }

        int answer = 0;
        for (int i = 0; i <= K; i++) {
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }

    static void dp(int w, int v) {
        for (int i = dp.length - 1; i >= 0; i--) {
            if (dp[i] > 0 && i + w <= K) {
                dp[i + w] = Math.max(dp[i + w], dp[i] + v);
            }
        }

        dp[w] = Math.max(dp[w], v);
    }
}