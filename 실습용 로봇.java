class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    int idx = 0;
    public int[] solution(String command) {
        int[] answer = new int[2];
        int x = 0;
        int y = 0;
        for (int i = 0; i < command.length(); i++) {
            String c = command.substring(i, i + 1);
            switch (c) {
                case "G" :
                x += dx[idx];
                y += dy[idx];
                break;
                case "B" :
                x += dx[(idx + 2) % 4];
                y += dy[(idx + 2) % 4];
                break;
                case "R" :
                idx = (idx + 1) % 4;
                break;
                case "L" :
                idx = (idx + 3) % 4;
                break;
            }
        }
        answer[0] = x;
        answer[1] = y;
        return answer;
    }
}
