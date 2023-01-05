class Solution {
    int solution(int[][] land) {

        // 제일 아래부터 올라오면서 값 세팅
        for (int i = land.length - 1; i > 0; i--) {
            int firstNum = 0;
            int secondNum = 0;
            int idx = 0;
            
            // 현재 i값의 최대값과 두번째 값 찾기
            for (int j = 0; j < land[i].length; j++) {
                if (firstNum < land[i][j]) {
                    secondNum = Math.max(secondNum, firstNum);
                    firstNum = land[i][j];
                    idx = j;
                } else if (secondNum < land[i][j]) {
                    secondNum = land[i][j];
                }
            }
            
            // 다음 arr[i]에 최대값 더하기
            for (int j = 0; j < land[i].length; j++) {
                if (j == idx) {
                    land[i - 1][j] += secondNum;
                    continue;
                }
                land[i - 1][j] += firstNum;
            }
        }
        
        int answer = 0;
        for (int i = 0 ; i < 4; i++) {
            answer = Math.max(answer, land[0][i]);
        }
        return answer;
    }
}
