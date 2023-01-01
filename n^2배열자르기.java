import java.util.*;
class Solution {
    public int[] solution(int n, long left, long right) {

        List<Long> list = new ArrayList<>();
        for (long i = left; i <= right; i++) {
            long num = (long) (Math.floor(i / n) - i % n);
            num = num < 0 ? 0 : num;
            list.add(i % n + 1 + num);
        }

        int[] answer = new int[list.size()];
        int idx = 0;
        for (long i : list) {
            answer[idx] = (int) i;
            idx++;
        }
        return answer;
    }
}
