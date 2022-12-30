class Solution {
    int answer = 0;
    public int solution(int[] numbers, int target) {
        recursion(0, target, numbers, 0);
        return answer;
    }
    
    void recursion (int count, int target, int[] numbers, int value) {
        if (count == numbers.length) {
            if (target == value) {
                answer++;
            }
            return;
        }
        
        recursion(count + 1, target, numbers, value + numbers[count]);
        recursion(count + 1, target, numbers, value - numbers[count]);
    }
}
