class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        int mul = 1;
        boolean flag = true;
        while (flag) {
            int val = arr[arr.length - 1] * mul;
            
            boolean f = true;
            for (int i = arr.length - 2; i >= 0; i--) {
                if (val % arr[i] != 0) {
                    f = false;
                    break;
                }
            }
            
            if (f) {
                flag = false;
                answer = val;
            } else {
                mul++;
            }
        }
        return answer;
    }
}
