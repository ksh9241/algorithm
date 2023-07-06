import java.util.*;
class Solution {
    int strX = 0;
    int strY = 0;
    String[][] map;
    public int[] solution(String[] park, String[] routes) {
         map = new String[park.length][park[0].length()];
        
        // init
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[i].length(); j++) {
                String value = park[i].substring(j, j + 1);
                if ("S".equals(value)) {
                    strX = i;
                    strY = j;
                }
                map[i][j] = value;
            }
        }
        
        quest(routes);
        
        int[] answer = new int[]{strX, strY};
        return answer;
    }
    
    public void quest (String[] routes) {
        
        for (int i = 0; i < routes.length; i++) {
            StringTokenizer st = new StringTokenizer(routes[i]);
            String direction = st.nextToken();
            int distance = Integer.parseInt(st.nextToken());
            
            move(direction, distance);
        }
    }
    
    public void move(String direction, int distance) {
        boolean result = true;
        switch (direction) {
            case "N" : // 위로
                if (strX - distance > -1) {
                    for (int i = 1; i <= distance; i++) {
                        if ("X".equals(map[strX - i][strY])) {
                            result = false;
                            break;
                        }
                    }
                    
                    if (result) {
                        strX -= distance;
                    }
                }
                break;
            case "S" : // 아래로
               if (strX + distance < map.length) {
                    for (int i = 1; i <= distance; i++) {
                        if ("X".equals(map[strX + i][strY])) {
                            result = false;
                            break;
                        }
                    }
                    
                    if (result) {
                        strX += distance;
                    }
                }
                break;
            case "W" : // 왼쪽
               if (strY - distance > -1) {
                    for (int i = 1; i <= distance; i++) {
                        if ("X".equals(map[strX][strY - i])) {
                            result = false;
                            break;
                        }
                    }

                    if (result) {
                        strY -= distance;
                    }
                }
                break;
            case "E" : // 아래로
               if (strY + distance < map[0].length) {
                    for (int i = 1; i <= distance; i++) {
                        if ("X".equals(map[strX][strY + i])) {
                            result = false;
                            break;
                        }
                    }

                    if (result) {
                        strY += distance;
                    }
                }
                break;
        }
    }
}
