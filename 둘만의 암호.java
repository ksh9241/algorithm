class Solution {
    public String solution(String s, String skip, int index) {
        String answer = "";
        
        int[] alphabet = new int[32];
        for (int i = 0; i < skip.length(); i++) {
            int idx = (int) skip.charAt(i) - 97;
            alphabet[idx]++;
        }
        
        
        for (int i = 0; i < s.length(); i++) {
            int charNum = (int) s.charAt(i) - 97;
            int count = 0;
            
            while (count < index) {
                ++charNum;
                if (charNum == 26) {
                    charNum = 0;
                }
                
                // 스킵 대상이 아니면
                if (alphabet[charNum] == 0) {
                    count++;
                }
            }
            
            answer += String.valueOf((char) (charNum + 97));
        }
        
        
        return answer;
    }
}
