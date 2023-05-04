import java.util.*;
class Solution {
        int[] persent = {10, 20, 30, 40};
        int[] answer = new int[2];
        int[][] globalUsers;
        int[] globalEmoticons;
        int[] emoticonsIdx;
        public int[] solution(int[][] users, int[] emoticons) {
            globalUsers = Arrays.copyOf(users, users.length);
            globalEmoticons = Arrays.copyOf(emoticons, emoticons.length);

            emoticonsIdx = new int[emoticons.length];

            recursion();

            return answer;
        }

        public void recursion() {
            // 모든 탐색 종료 시
            if (emoticonsIdx[0] > 3) {
                return;
            }
            check();

            emoticonsIdx[emoticonsIdx.length - 1] = emoticonsIdx[emoticonsIdx.length - 1] + 1;
            calculateIdx();
            recursion();
        }

        public void check() {
            int[] result = new int[2];
            // 모든 유저 정보 순회하면서 값 체크
            for (int i = 0; i < globalUsers.length; i++) {
                int userPersent = globalUsers[i][0];

                int totalPrice = 0;
                for (int j = 0; j < globalEmoticons.length; j++) {
                    // 유저가 생각하는 할인율보다 크면
                    if ( userPersent <= persent[emoticonsIdx[j]] ) {
                        totalPrice += globalEmoticons[j] * (100 - persent[emoticonsIdx[j]]) / 100;
                    }
                }

                if (totalPrice >= globalUsers[i][1]) {
                    result[0]++;
                } else {
                    result[1] += totalPrice;
                }
            }

            // 최대 좋은 결과값으로 재정의
            if (answer[0] < result[0]) {
                answer[0] = result[0];
                answer[1] = result[1];
            } else if (answer[0] == result[0]) {
                answer[1] = Math.max(answer[1], result[1]);
            }
        }

        public void calculateIdx() {
            for (int i = emoticonsIdx.length - 1; i > 0; i--) {
                if (emoticonsIdx[i] == 4) {
                    emoticonsIdx[i] = 0;
                    emoticonsIdx[i - 1]++;
                }
            }
        }
    }