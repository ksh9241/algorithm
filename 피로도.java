class Solution {
    int maxVal = Integer.MIN_VALUE;
    boolean[] visit;
    public int solution(int k, int[][] dungeons) {
        visit = new boolean[dungeons.length];
        quest(dungeons, 0, k);
        int answer = maxVal;
        return answer;
    }

    void quest(int[][] dungeons, int count, int k) {
        // 완전탐색
        for (int i = 0; i < dungeons.length; i++) {
            if (!visit[i] && k >= dungeons[i][0]) {
                visit[i] = true;
                quest(dungeons, count + 1, k - dungeons[i][1]);
                visit[i] = false;
            }
        }

        maxVal = Math.max(maxVal, count);
    }
}