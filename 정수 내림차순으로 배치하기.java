class Solution {
    public long solution(long n) {
        int[] num = new int[10]; // 0 ~ 9
        String s = String.valueOf(n);
        
        for (int i = 0; i < s.length(); i++) {
            int number = Integer.parseInt(s.substring(i, i + 1));
            num[number]++;
        }
        
        String result = "";
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < num[i]; j++) {
                result += String.valueOf(i);
            }
        }
        
        long answer = Long.parseLong(result);
        return answer;
    }
}
