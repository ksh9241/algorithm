import java.util.*;
class Solution {
    List<Coordinate> oList = new ArrayList<>();
        List<Coordinate> xList = new ArrayList<>();
        boolean notPossible = false;

        int[] dx = new int[]{1, 1, 1, 0, -1, -1, -1, 0};
        int[] dy = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
        String[][] map;
        public int solution(String[] board) {
            map = new String[board.length][board[0].length()];
            boolean[][] visit = new boolean[map.length][map[0].length];


            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length(); j++) {
                    String text = board[i].substring(j, j + 1);
                    map[i][j] = text;
                    if ("O".equals(text)) {
                        oList.add(new Coordinate(i, j));
                    } else if ("X".equals(text)) {
                        xList.add(new Coordinate(i, j));
                    }
                }
            }


            if (oList.size() >= xList.size() && oList.size() - xList.size() < 2) {
                quest(true, visit);
            } else {
                notPossible = true;
            }


            int answer = 1;
            if (notPossible) {
                answer = 0;
            }

            return answer;
        }


        // 체크 내용 : 빙고처리됐는데도 X 개수가 동일할 때,
        // attackTurn : true 선공, false 후공
        private void quest(boolean attackTurn, boolean[][] visit) {
            // 종료조건
            if (notPossible) {
                return;
            }
            
            // O가 이긴 케이스
            if (oCheck(visit)) {
                if (xCheck(visit) == 1 && !attackTurn) {
                    if (bingo(visit, oList, "O")) {
                        notPossible = true;
                    }
                }
                return;
            }
            
            // X가 먼저 이긴 케이스
            if (xCheck(visit) == 0 && attackTurn && !oCheck(visit)) {
                if (bingo(visit, xList, "X")) {
                    notPossible = true;
                    return;
                }
            }

            if (attackTurn) {
                recursion(visit, oList, attackTurn);
            } else {
                recursion(visit, xList, attackTurn);
            }
        }
    
        private void recursion(boolean[][] visit, List<Coordinate> list, boolean attackTurn) {
            for (int i = 0; i < list.size(); i++) {
                Coordinate coordinate = list.get(i);
                if (!visit[coordinate.x][coordinate.y]) {
                    visit[coordinate.x][coordinate.y] = true;
                    quest(!attackTurn, visit);
                    visit[coordinate.x][coordinate.y] = false;
                }

            }
        }

        private boolean bingo(boolean[][] visit, List<Coordinate> list, String type) {
            boolean result = false;
            for (int i = 0; i < list.size(); i++) {
                Coordinate coordinate = list.get(i);
                // 8방향
                for (int j = 0; j < 8; j++) {

                    boolean check = true;
                    int endX = coordinate.x;
                    int endY = coordinate.y;

                    for (int k = 0; k < 3; k++) {
                        if (k > 0) {
                            endX += dx[j];
                            endY += dy[j];
                        }
                        if (!(endX >= 0 && endY >= 0 && endX < 3 && endY < 3 && visit[endX][endY] && type.equals(map[endX][endY]))) {
                            check = false;
                            break;
                        }
                    }

                    if (check) {
                        result = true;
                        break;
                    }
                }

                if (result) break;
            }

            return result;
        }

        private boolean oCheck(boolean[][] visit) {
            boolean result = true;
            for (int i = 0; i < oList.size(); i++) {
                Coordinate coordinate = oList.get(i);
                if (!visit[coordinate.x][coordinate.y]) {
                    result = false;
                    break;
                }
            }
            return result;
        }

        private int xCheck(boolean[][] visit) {
            int count = 0;
            for (int i = 0; i < xList.size(); i++) {
                Coordinate coordinate = xList.get(i);
                if (!visit[coordinate.x][coordinate.y]) {
                    count++;
                }
            }

            return count;
        }

        class Coordinate {
            int x;
            int y;

            public Coordinate(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
}
