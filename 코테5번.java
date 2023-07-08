class Solution {
        int answer = 0;
        public int solution(int A, int B, int C, int D, int N, int[][] Chips) {
            // 케이크: A cm x B cm
            // 직사각형 틀: C cm x D cm
            // 초콜릿 칩: N개
            // 초콜릿 칩의 좌표 xn, yn: (Chips[n-1][0], Chips[n-1][1])
            int[][] cake = new int[A][B];

            for (int i = 0; i < Chips.length; i++) {
                int[] chip = Chips[i];
                cake[chip[0]][chip[1]] = 1;
            }

            for (int i = 0; i < cake.length; i++) {
                for (int j = 0; j < cake[i].length; j++) {
                    quest(i, j, cake, C, D);
                }
            }
            return answer;
        }

        public void quest (int x, int y, int[][] cake, int C, int D) {
            // 정사각형일 땐 1번만 체크

            if (C == D) {
                if (check(cake, x + C, y + D)) {

                    int count = 0;
                    for (int i = x; i <= x + C; i++) {
                        for (int j = y; j <= y + D; j++) {
                            if (cake[i][j] == 1) {
                                count++;
                            }
                        }
                    }
                    answer = Math.max(answer, count);
                }

            } else {
                if (check(cake, x + C, y + D)) {

                    int count = 0;
                    for (int i = x; i <= x + C; i++) {
                        for (int j = y; j <= y + D; j++) {
                            if (cake[i][j] == 1) {
                                count++;
                            }
                        }
                    }
                    answer = Math.max(answer, count);
                }

                if (check(cake, x + D, y + C)) {

                    int count = 0;
                    for (int i = x; i <= x + D; i++) {
                        for (int j = y; j <= y + C; j++) {
                            if (cake[i][j] == 1) {
                                count++;
                            }
                        }
                    }
                    answer = Math.max(answer, count);
                }
            }
        }

        public boolean check (int[][] cake, int x, int y) {
            return !(x >= cake.length || y >= cake[0].length);
        }
    }
