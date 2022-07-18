import java.io.*;
import java.util.*;

public class Main {
    static int[][] ARR;
    static int[][] copyArr;
    static String[] sArr;
    static int R, C, S;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        
        ARR = new int[R + 2][C + 2];
        copyArr = new int [R + 2][C + 2];
        sArr = new String[S];
        
        // Array 초기화
        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 1; j <= C; j++) {
                ARR[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int result = 0;
        int answer = Integer.MAX_VALUE;
        
        // 경우의 수 받기
        for (int i = 0; i < S; i++) {
            sArr[i] = br.readLine();
        }
        
        
        boolean flag = true; // sArr 값 정방향으로 처리하고, 역방향으로 처리해야되서 flag로 구분 값
        
        for (int k = 0; k < S; k++) {
        	copyArray();
        	for (int i = 0; i < sArr.length; i++) {
        		if (!flag) {
        			st = new StringTokenizer(sArr[S - 1 - i]);
        		} else {
        			st = new StringTokenizer(sArr[i]);
        		}
        		int x = Integer.parseInt(st.nextToken());
        		int y = Integer.parseInt(st.nextToken());
        		int num = Integer.parseInt(st.nextToken());
        		
        		moveArray(x, y, num);
        		if (i == S - 1) {
        			result = minVal(x, y, num);
        		}
        	}
        	flag = false;
        }
        
        answer = Math.min(answer, result);
        System.out.println(answer);
    }
    
    // Array 이동
    static void moveArray(int r, int c, int s) {
        int startX = r - s;
        int startY = c - s;
        int endX = r + s;
        int endY = c + s;
        
        int temp1 = 0;
        int temp2 = 0;
        
        // 북쪽
        for (int i = startY; i < endY; i++) {
            if (i == startY) temp1 = copyArr[startX][startY];
            temp2 = copyArr[startX][i + 1];
            copyArr[startX][i + 1] = temp1;
            temp1 = temp2;
        }
        
        // 동쪽
        for (int i = startX + 1; i <= endX; i++) {
            if (i == startX + 1) {
                temp1 = copyArr[i][endY];
                copyArr[i][endY] = temp2;
                continue;
            }
            temp2 = copyArr[i][endY];
            copyArr[i][endY] = temp1;
            temp1 = temp2;
        }
        
        // 남쪽
        for (int i = endY - 1; i >= startY; i--) {
            if (i == endY - 1) {
                temp1 = copyArr[endX][i];
                copyArr[endX][i] = temp2;
                continue;
            }
            temp2 = copyArr[endX][i];
            copyArr[endX][i] = temp1;
            temp1 = temp2;
        }
        
        // 서쪽
        for (int i = endX - 1; i >= startX; i--) {
            if (i == endX - 1) {
                temp1 = copyArr[i][startY];
                copyArr[i][startY] = temp2;
                continue;
            }
            temp2 = copyArr[i][startY];
            copyArr[i][startY] = temp1;
            temp1 = temp2;
        }
        
        // 내부에도 이동할 배열이 있을경우
        if (s - 1 > 0) {
            moveArray(r, c, s - 1);
        }
    }
    
    // 최소 값 구하기
    static int minVal (int r, int c, int s) {
        int startX = r - s;
        int endX = r + s;
        int x = (startX + endX) / 2;
        
        int startY = c - s;
        int endY = c + s;
        
        int sum = 0;
        for (int i = startY; i <= endY; i++) {
            sum += copyArr[x][i];
        }
        
        return sum;
    }
    
    // 카피
    static void copyArray() {
        for (int i = 0; i < ARR.length; i++) {
            for (int j = 0; j < ARR.length; j++) {
                copyArr[i][j] = ARR[i][j];
            }
        }
    }
}