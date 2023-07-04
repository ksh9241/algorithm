class Solution {
    public int solution(String s) {
        int answer = 0;
        
        if (s.charAt(0) == '-') {
            answer = Integer.parseInt(s.substring(1, s.length()));
            answer *= -1;
        } else {
            answer = Integer.parseInt(s.substring(0, s.length()));
        }
        
        return answer;
    }
}
