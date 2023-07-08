class Solution {
        int answer = 0;
        boolean[] visit;

        public int solution(String s, int N) {
            visit = new boolean[N + 1];
            permutation(s, 0, N, "");

            if (answer == 0) {
                answer = -1;
            }
            return answer;
        }


        public void permutation(String s, int depth, int N, String text) {
            if (N == depth) {
                if (s.contains(text)) {
                    answer = Math.max(answer, Integer.parseInt(text));
                }
            }

            for (int i = 1; i <= N; i++) {
                if (!visit[i]) {
                    visit[i] = true;
                    permutation(s, depth + 1, N, text + String.valueOf(i));
                    visit[i] = false;
                }
            }


        }
    }
