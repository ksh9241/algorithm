class Solution {
    public String solution(String[] seoul) {
        String answer = "";
        
        int idx = -1;
        for (int i = 0; i < seoul.length; i++) {
            if ("Kim".equals(seoul[i])) {
                idx = i;
                break;
            }
        }
        answer = String.format("김서방은 %s에 있다", String.valueOf(idx));
        return answer;
    }
}
