import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 최대 가방에 넣을 수 있는 개수
        K = Integer.parseInt(st.nextToken());   // 최대 가방에 넣을 수 있는 무게

        int[] arr = new int[100001];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            for (int j = 100000; j >= 0; j--) {
                if (arr[j] != 0 && j + w < 100001) {
                    arr[j + w] = Math.max(arr[j + w], arr[j] + v);
                }
            }
            arr[w] = v;
        }
        int answer = 0;
        for (int i = 0; i <= K; i++) {
            answer = Math.max(answer, arr[i]);
        }
        System.out.println(answer);
    }
}

