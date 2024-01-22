import java.util.*;

class Solution {
    boolean[][] blueArea;
    boolean[][] redArea;
    
    int[][] globalMaze;
    int answer = Integer.MAX_VALUE;
    
    int[] dx = new int[]{0, 1, 0, -1};
    int[] dy = new int[]{1, 0, -1, 0};
    
    /*
    1 : 빨간 수레 시작 칸
    2 : 파란 수레 시작 칸
    3 : 빨간 수레 도착 칸
    4 : 파란 수레 도착 칸
    5 : 벽
    */
    public int solution(int[][] maze) {
        blueArea = new boolean[maze.length][maze[0].length];
        redArea = new boolean[maze.length][maze[0].length];
        globalMaze = maze;
        
        int[] blueIdx = new int[2];
        int[] redIdx = new int[2];
        
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 1) {
                    redIdx[0] = i;
                    redIdx[1] = j;
                }
                else if (maze[i][j] == 2) {
                    blueIdx[0] = i;
                    blueIdx[1] = j;
                }
            }
        }
        
        backtracking(blueIdx, redIdx, 0, 0, true);
        
        if (answer == Integer.MAX_VALUE) {
            answer = 0;
        }
        
        return answer;
    }
    
    // checkColor : true(파랑), false(빨강)
    public void backtracking(int[] blueIdx, int[] redIdx, int blueCnt, int redCnt, boolean checkColor) {
        int blueX = blueIdx[0];
        int blueY = blueIdx[1];
        int redX = redIdx[0];
        int redY = redIdx[1];
        
        // 빨간수레와 파란수레가 원하는 위치에 도달했을 경우
        if (globalMaze[redX][redY] == 3 && globalMaze[blueX][blueY] == 4) {
            int endCount = Math.max(blueCnt, redCnt);
            answer = Math.min(answer, endCount);
            return;
        }
        
        // 빨강만 도착위치에 도달한 경우
        if (globalMaze[redX][redY] == 3 && !checkColor) {
            backtracking(blueIdx, redIdx, blueCnt, redCnt, !checkColor);
        }
        else if (globalMaze[blueX][blueY] == 4 && checkColor) {
            backtracking(blueIdx, redIdx, blueCnt, redCnt, !checkColor);
        }
        
        // 파랑 탐색
        if (checkColor) {
            for (int i = 0; i < 4; i++) {
                int nxtX = blueX + dx[i];
                int nxtY = blueY + dy[i];
                
                if (check(nxtX, nxtY, checkColor, redIdx)) {
                    blueArea[nxtX][nxtY] = true;
                    backtracking(new int[]{nxtX, nxtY}, redIdx, blueCnt + 1, redCnt, !checkColor);
                    blueArea[nxtX][nxtY] = false;
                } 
            }
        }
        else { // 빨강 탐색
            for (int i = 0; i < 4; i++) {
                int nxtX = redX + dx[i];
                int nxtY = redY + dy[i];
                
                if (check(nxtX, nxtY, checkColor, blueIdx)) {
                    redArea[nxtX][nxtY] = true;
                    backtracking(blueIdx, new int[]{nxtX, nxtY} , blueCnt , redCnt + 1, !checkColor);
                    redArea[nxtX][nxtY] = false;    
                }
            }
        }
    }
    
    // 벽이 아니면서, 해당 컬러의 탐색한 곳이 아닌 곳, 인덱스 범위 내의 있는 곳
    public boolean check(int x, int y, boolean checkColor, int[] opponentColor) {
        boolean result = false;
        
        if (x >= 0 && y >= 0 && x < globalMaze.length && y < globalMaze[0].length && globalMaze[x][y] != 5) {
            if (checkColor && !blueArea[x][y] && !(opponentColor[0] == x && opponentColor[1] == y)) {
                result = true;
            } else if (!checkColor && !redArea[x][y] && !(opponentColor[0] == x && opponentColor[1] == y)) {
                result = true;
            }
        }
        
        return result;
    }
}
