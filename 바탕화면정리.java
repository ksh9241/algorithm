class Solution {
    public int[] solution(String[] wallpaper) {
        String[][] background = new String[wallpaper.length][wallpaper[0].length()];
        
        for (int i = 0; i < wallpaper.length; i++) {
            String s = wallpaper[i];
            for (int j = 0; j < s.length(); j++) {
                background[i][j] = s.substring(j, j + 1);
            }
        }
        
        for (String[] s : background) {
            for (String ss : s) {
                System.out.print(ss + " ");
            }
            System.out.println();
        }
        
        int strX = Integer.MAX_VALUE;
        int strY = Integer.MAX_VALUE;
        int endX = Integer.MIN_VALUE;
        int endY = Integer.MIN_VALUE;
        
        for (int i = 0; i < background.length; i++) {
            for (int j = 0; j < background[i].length; j++) {
                if ("#".equals(background[i][j])) {
                    strX = Math.min(strX, i);
                    strY = Math.min(strY, j);
                    endX = Math.max(endX, i + 1);
                    endY = Math.max(endY, j + 1);
                }
            }
        }
        
        
        int[] answer = new int[]{strX, strY, endX, endY};
        return answer;
    }
}
