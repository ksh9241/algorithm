class Solution {
        int minCount = Integer.MAX_VALUE;
        int answer = 0;

        int[] dx = {0, 1};
        int[] dy = {1, 0};
        int[][] map;
        public int solution(int width, int height, int[][] diagonals) {
            map = new int[height + 1][width + 1];

            Queue<Bfs> q = new LinkedList<>();
            q.add(new Bfs(0, true, 0, 0));

            while (!q.isEmpty()) {
                Bfs bfs = q.poll();

                if (height == bfs.x && width == bfs.y && !bfs.diagonal) {
                    // 최소값이 존재하면 초기화
                    if (bfs.depth < minCount) {
                        answer = 1;
                        minCount = bfs.depth;
                    } else if (bfs.depth == minCount) {
                        answer = (answer + 1) % 10000019;
                    }
                    continue;
                }

                // 대각선 처리
                if (bfs.diagonal) {
                    for (int i = 0; i < diagonals.length; i++) {
                        if (diagonals[i][0] - 1 == bfs.x && diagonals[i][1] == bfs.y) {
                            q.add(new Bfs(bfs.depth + 1, false, bfs.x + 1, bfs.y - 1));
                        }

                        if (diagonals[i][0] == bfs.x && diagonals[i][1] - 1 == bfs.y) {
                            q.add(new Bfs(bfs.depth + 1, false, bfs.x - 1, bfs.y + 1));
                        }
                    }
                }

                // 직선 처리
                for (int i = 0; i < 2; i++) {
                    int nxtX = dx[i] + bfs.x;
                    int nxtY = dy[i] + bfs.y;

                    if (nxtX <= height && nxtY <= width) {
                        q.add(new Bfs(bfs.depth + 1, bfs.diagonal, nxtX, nxtY));
                    }
                }
            }
            
            return answer;

        }

        public class Bfs {

            public Bfs(int depth, boolean diagonal, int x, int y) {
                this.depth = depth;
                this.diagonal = diagonal;
                this.x = x;
                this.y = y;
            }
    
            int depth;
            boolean diagonal;
            int x;
            int y;
        }
    }

    
