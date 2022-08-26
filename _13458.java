import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int B; // 총감독
    static int C; // 부감독

    public static void main(String[] args) throws Exception {
        long answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st2.nextToken());
        C = Integer.parseInt(st2.nextToken());

        for (int i = 0; i < N; i++) {
            int personnel = Integer.parseInt(st.nextToken());
            if (personnel > B) {
                answer++;
                personnel -= B;
                if (personnel % C == 0) {
                    answer += personnel / C;
                } else {
                    answer += (personnel / C) + 1;
                }
            } else {
                answer++;
            }
        }

        System.out.println(answer);
    }
}