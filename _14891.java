import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /*
    N = 0, S = 1
    1번기어 : 3번 idx
    2번기어 : 3번 idx, 7번 idx
    3번기어 : 3번 idx, 7번 idx
    4번기어 : 7번 idx

    기어를 시계방향으로 돌릴 때
    연결된 기어는 반시계, 또 연결된 기어는 시계...

    */
    static int[][] gear = new int[5][10];
    static boolean[] visit;
    static int[] score = {0,1,2,4,8};
    static int N;
    public static void main (String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // gear 초기화
        for (int i = 1; i <= 4; i++) {
            String gearVal = br.readLine();

            for (int j = 1; j <= gearVal.length() ; j++) {
                gear[i][j] = Integer.parseInt(gearVal.substring(j - 1, j));
            }
        }

        // gear 돌리는 횟수
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken());
            int rotationVal = Integer.parseInt(st.nextToken());
            visit = new boolean[5];
            checkGear(gearNum, rotationVal);
        }
        int answer = sumVal();
        System.out.println(answer);
    }

    private static int sumVal() {
        int answer = 0;
        for (int i = 1; i < gear.length; i++) {
            if (gear[i][1] == 1) {
                answer += score[i];
            }
        }
        return answer;
    }

    private static void checkGear(int gearNum, int rotationVal) {
        visit[gearNum] = true;
        int nxtRotationVal = rotationVal > 0 ? -1 : 1;

        // 왼쪽 기어 체크
        if (gearNum - 1 > 0 && !visit[gearNum - 1]) {
            if (gear[gearNum][7] != gear[gearNum - 1][3]) {
                checkGear(gearNum - 1, nxtRotationVal);
            }
        }

        // 오른쪽 기어 체크
        if (gearNum + 1 < 5 && !visit[gearNum + 1]) {
            if (gear[gearNum][3] != gear[gearNum + 1][7]) {
                checkGear(gearNum + 1, nxtRotationVal);
            }
        }
        rotationGear(gearNum, rotationVal);
    }

    private static void rotationGear(int gearNum, int rotationVal) {
        // 시계방향으로 톱니바퀴 값 돌리기
        if (rotationVal > 0) {
            for (int i = 9; i > 0; i--) { // 9번 실행
                int nxtIdx = i - 1 < 1 ? 9 : i - 1;
                gear[gearNum][i] = gear[gearNum][nxtIdx];
            }
            // 톱니바퀴 돌리고 안쓰는 값 0 초기화
            gear[gearNum][9] = 0;
        }
        // 반시계 방향으로 톱니바퀴 값 돌리기
        else {
            for (int i = 0; i < 9; i++) {
                int nxtIdx = i + 1 > 8 ? 0 : i + 1;
                gear[gearNum][i] = gear[gearNum][nxtIdx];
            }
            gear[gearNum][0] = 0;
        }
    }
}