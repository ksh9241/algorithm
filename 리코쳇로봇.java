import java.util.*;

class Solution {
    String[][] map = null;
    boolean[][] visit = null;
    Queue<QuestInfo> q = new LinkedList<>();
    
    int answer = -1;
    boolean stopFlag = false;
    
    // 오른쪽, 위에, 왼쪽, 아래
    int[] dx = {0, -1, 0, 1};
    int[] dy = {1, 0, -1, 0};
    
    public int solution(String[] board) {
        map = new String[board.length][board[0].length()];
        visit = new boolean[board.length][board[0].length()];
        
        int startX = 0;
        int startY = 0;
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                String val = board[i].substring(j, j + 1);
                map[i][j] = val;
                
                if ("R".equals(val)) {
                    startX = i;
                    startY = j;
                }
            }
        }
        q.add(new QuestInfo(startX, startY, 0, -1));
        
        while(!q.isEmpty()) {
            if (stopFlag) {
                break;
            }
            
            quest(q.poll());
        }
        
        return answer;
    }
    
    public void quest(QuestInfo info) {
        
        for (int i = 0; i < 4; i++) {
            int x = info.x;
            int y = info.y;
            
            // 이전에 왔던 경로일 경우 탐색 X
            if (info.prevDirection != -1 && (info.prevDirection + 2) % 4 == i ) {
                continue;
            }
            
            while (true) {
                x += dx[i];
                y += dy[i];
                
                // 벽에 부딛치거나 맵 끝에 도달했을 때
                if (x < 0 || y < 0 || x >= map.length || y >= map[0].length || "D".equals(map[x][y])) {
                    // 탐색한 이력이 있으면 미 처리
                    if (visit[x - dx[i]][y - dy[i]]) {
                        break;
                    }
                    
                    // 딱 도착지점이 G일경우
                    if ("G".equals(map[x - dx[i]][y - dy[i]])) {
                        answer = info.count + 1;
                        stopFlag = true;
                        break;
                    }
                    
                    if (x - dx[i] == info.x && y - dy[i] == info.y) {
                        break;
                    }
                    
                    q.add(new QuestInfo(x - dx[i], y - dy[i], info.count + 1, i));
                    visit[x - dx[i]][y - dy[i]] = true;
                    break;
                }
            }
            if (stopFlag) {
                break;
            }
        }
    }
    
    class QuestInfo {
        public QuestInfo(int x, int y, int count, int prevDirection) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.prevDirection = prevDirection;
        }
        
        int x;
        int y;
        int count;
        int prevDirection; // 0 : 왼쪽, 1 : 아래쪽, 2 : 오른쪽, 3 : 위쪽 , -1 처음
        
        public String toString() {
            return x + " / " + y + " / " + count + " / " + prevDirection;
        }
    }
}
