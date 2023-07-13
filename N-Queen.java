class Solution {
    int[] map;
    int answer = 0;
    public int solution(int n) {
        for (int i = 1; i <= n; i++) {
            map = new int[n + 1];
            map[1] = i;
            dfs(1, n);
        }

        return answer;
    }

    public void dfs(int row, int n) {
        if (row == n) {
            ++answer;
        } else {
            for (int i = 1; i <= n; i++) {
                map[row + 1] = i;
                if (isPossible(row + 1)) {
                    dfs(row + 1, n);
                } else {
                    map[row + 1] = 0;
                }
            }
        }
    }

    private boolean isPossible (int c) {
        for (int i = 1; i < c; i++) {
            if (map[i] == map[c]) {
                return false;
            }

            if (Math.abs(map[i] - map[c]) == Math.abs(i - c)) {
                return false;
            }
        }
        return true;
    }
}
