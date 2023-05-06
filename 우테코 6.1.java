class Solution {
        int todayMaxMoney = 100000;
        public int solution(int[] boxes, int m, int k) {
            Arrays.sort(boxes);

            for (int i = 0; i < k; i++) {
                int money = m / 10000;

                for (int j = boxes.length - 1; j >= 0; j--) {
                    int plus = money * boxes[j];
                    if (todayMaxMoney >= plus) {
                        m += money * boxes[j];
                        break;
                    }
                }
            }
            
            int answer = m;
            return answer;
        }
