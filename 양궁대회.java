import java.util.*;
class Solution {
    int[] answer = new int[11];
    int maxVal = Integer.MIN_VALUE;
    public int[] solution(int n, int[] info) {
        recursion(n, info, new int[info.length], 0);
        
        boolean flag = true;
        for (int i = 0; i < answer.length; i++) {
            if (answer[i] != 0) {
                flag = false;
                break;
            }
        }
        
        if (flag) {
            answer = new int[]{-1};
        }
        return answer;
    }

    public void recursion(int n, int[] info, int[] result, int idx) {
        if (n == 0) {
            check(info, result);
            return;
        }

        for (int i = idx; i < info.length; i++) {
            int infoVal = info[i];
            if (i == info.length - 1) {
                result[i] = n;
                recursion(0, info, result, i + 1);
                result[i] = 0;
                continue;
            }
            // 이길 수 있는 경우의 수가 존재한다면
            if (n - infoVal > 0) {
                result[i] = infoVal + 1;
                recursion(n - (infoVal + 1), info, result, i + 1);
                result[i] = 0;
            }
        }
    }

    public void check(int[] info, int[] result) {
        int infoTotal = 0;
        int resultTotal = 0;

        for (int i = 0; i < info.length; i++) {
            int infoNum = info[i];
            int resultNum = result[i];

            if (infoNum == 0 && resultNum == 0) continue;

            if (infoNum >= resultNum) {
                infoTotal += 10 - i;
            } else {
                resultTotal += 10 - i;
            }
        }

        if (resultTotal > infoTotal) {
            // 점수차이가 더 큰 경우
            if (resultTotal - infoTotal > maxVal) {
                answer = result.clone();
                maxVal = resultTotal - infoTotal;
            }
            // 점수차이가 동일한 경우
            else if (resultTotal - infoTotal == maxVal) {
                for (int i = result.length - 1; i >= 0; i--) {
                    int answerVal = answer[i];
                    int resultVal = result[i];

                    if (answerVal == 0 && resultVal == 0) continue;

                    if (answerVal > resultVal) {
                        break;
                    } else {
                        answer = result.clone();
                        break;
                    }
                }
            }
        }
    }
}
