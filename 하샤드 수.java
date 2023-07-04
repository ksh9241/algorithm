class Solution {
    public boolean solution(int x) {
        boolean answer = true;
        
        int number = x;
        int hashard = 0;
        while (true) {
            // 한자리 수 일 때
            if (number < 10) {
                hashard += number;
                break;
            }
            
            hashard += number % 10;
            number /= 10;
        }
        
        if (x % hashard != 0) {
            answer = false;
        }
        
        return answer;
    }
}
