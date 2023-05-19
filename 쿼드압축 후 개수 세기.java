class Solution {
    int[] answer = new int[2];
    public int[] solution(int[][] arr) {
        check(arr.length, 0, 0, arr);
        return answer;
    }
    public void check (int length, int x, int y, int[][] arr) {
        int num = -1;
        boolean equals = true;
        for (int i = x; i < x + length; i++) {
            for (int j = y; j < y + length; j++) {
                if (num < 0) {
                    num = arr[i][j];
                }
                if (num != arr[i][j]) {
                    equals = false;
                }
            }
        }

        if (equals) {
            answer[num]++;
            return;
        }

        int nxtLength = length / 2;
        check(nxtLength, x, y, arr);
        check(nxtLength, x + nxtLength, y, arr);
        check(nxtLength, x, y + nxtLength, arr);
        check(nxtLength, x + nxtLength, y + nxtLength, arr);
    }
}
