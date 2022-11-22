class Solution {
    public int solution(int[] ingredient) {
        boolean[] visit = new boolean[ingredient.length];
        int answer = 0;

        for (int i = 0; i < ingredient.length; i++) {
            if (ingredient[i] == 1 && !visit[i]) {
                int result = 0;
                result = checkBurger(visit, ingredient, i);

                // 혹시모를 앞쪽 재료 다시 확인
                if (result == 1) {
                    i = backIndex(ingredient, visit, i) - 1;
                }
                answer += result;
            }
        }
        return answer;
    }

    static int backIndex(int[] intgredient, boolean[] visit, int idx) {
        if (idx - 1 < 0) return 0;
        for (int i = idx - 1; i >= 0; i--) {
            if (intgredient[i] == 1 && !visit[i]) {
                return i;
            }
        }
        return idx;
    }

    static int checkBurger(boolean[] visit, int[] ingredient, int idx) {
        int[] check = {1,2,3,1};
        int startIdx = idx;

        int count = 0;
        int answer = 0;
        while (count < 4) {
            if (idx >= ingredient.length) return 0;
            // 이미 햄버거 재료로 사용했다면
            if (visit[idx]) {
                idx++;
                continue;
            }

            // 햄버거 재료가 순서대로 있다면
            if (ingredient[idx] == check[count]) {
                count++;
                idx++;
            } else {
                break;
            }
        }

        if (count == 4) {
            for (int i = startIdx; i < idx; i++) {
                if (idx > ingredient.length) break;
                visit[i] = true;
            }
            answer = 1;
        }

        return answer;
    }
}