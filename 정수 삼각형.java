class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        for (int i = triangle.length - 2; i >= 0; i--) {
            for (int j = 0; j < triangle[i].length; j++) {
                int highNum = Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);
                triangle[i][j] += highNum;
            }
        }
        answer = triangle[0][0];
        return answer;
    }
}
