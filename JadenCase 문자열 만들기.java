class Solution {
    public String solution(String s) {
        String answer = "";
        
        s = s.replaceAll(" ", "-");
        
        boolean check = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('-' == c) {
                answer += " ";
                check = true;
                continue;
            }
            
            // 첫글자 대소문자 확인
            if (check) {
                if (c >= 97 && c <= 122) {
                    c -= 32;
                }
                check = false;
            } else {
                if (c >= 65 && c <= 90) {
                    c += 32;
                }
            }
            answer += String.valueOf(c);
        }
        
        return answer;
    }
}
