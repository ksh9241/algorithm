import java.util.*;

class Solution {
    String[][] globalMap = null;
    int answer = Integer.MAX_VALUE;
    int startX = -1;
    int startY = -1;
    Queue<int[]> q = new LinkedList<>();
    boolean check = false;
    
    int leverCnt = Integer.MAX_VALUE;
    
    boolean[][] visible = null;
    
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    
    public int solution(String[] maps) {
        globalMap = new String[maps.length][maps[0].length()];
        visible = new boolean[maps.length][maps[0].length()];
        
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                String val = maps[i].substring(j, j + 1);
                if ("S".equals(val)) {
                    startX = i;
                    startY = j;
                }
                
                globalMap[i][j] = val;
            }
        }
        
        
        int[] questInfo = {startX, startY, 0};
        q.add(questInfo);
        
        while (!q.isEmpty()) {
            questInfo = q.poll();
            int x = questInfo[0];
            int y = questInfo[1];
            int cnt = questInfo[2];
            
            
            if ("L".equals(globalMap[x][y]) && !check) {
                leverCnt = cnt;
                
                // 초기화
                q = new LinkedList<>();
                questInfo[2] = 0;
                q.add(questInfo);
                visible = new boolean[maps.length][maps[0].length()];
                
                check = true;
                continue;
            }
            
            // 종료조건
            if ("E".equals(globalMap[x][y]) && check) {
                answer = cnt + leverCnt;
                break;
            }
            
            quest(x, y, cnt);
        }
        
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        
        return answer;
    }
    
    public void quest(int x, int y, int count) {
        for (int i = 0; i < 4; i++) {
            int nxtX = x + dx[i];
            int nxtY = y + dy[i];
            
            if (nxtX >= 0 && nxtX < globalMap.length 
                && nxtY >= 0 && nxtY < globalMap[0].length
                && !visible[nxtX][nxtY]
                && !"X".equals(globalMap[nxtX][nxtY]) ) {
                
                int[] questInfo = {nxtX, nxtY, count + 1};
                q.add(questInfo);
                
                visible[nxtX][nxtY] = true;
            }
        }
    }
}
