class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;
        
        int tmp = 0;
        if (a > b) {
            tmp = a;
            a = b;
            b = tmp;
        }
        
        boolean flag = true;
        while (flag) {
            answer++;
            if (a + 1 == b && b % 2 == 0) {
                flag = false;
            }
            a = (a + 1) / 2;
            b = (b + 1) / 2;
            
        }
        
        return answer;
    }
}
