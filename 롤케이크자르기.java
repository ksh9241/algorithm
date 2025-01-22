import java.util.*;

class Solution {
    public int solution(int[] topping) {
        Set<Integer> left = new HashSet<>();
        Set<Integer> right = new HashSet<>();
        int[] count = new int[10001];

        int answer = 0;

        // 초기 세팅
        for (int i = 0; i < topping.length; i++) {
            int num = topping[i];
            right.add(num);
            count[num]++;
        }


        for (int i = 0; i < topping.length; i++) {
            int num = topping[i];
            left.add(num);

            count[num]--;

            if (count[num] == 0) {
                right.remove(num);
            }

            if (right.size() == left.size()) {
                answer++;
            }
        }

        return answer;
    }
}
