class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        String replaceS = "";
        while (s.length() > 1) {
            replaceS = s.replaceAll("0", "");
            System.out.println(replaceS.length());
            answer[1] += s.length() - replaceS.length();
            answer[0]++;
            
            s = String.valueOf(Integer.toBinaryString(replaceS.length()));
        }
        
        return answer;
    }
}
