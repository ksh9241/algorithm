import java.util.*;
class Solution {
    public int solution(int n) {
        int answer = 0;
        List<Integer> list = divisorCalculation(n);
        answer = list.stream().mapToInt(x -> x).sum();
        return answer;
    }

    static List<Integer> divisorCalculation(int num) {
        List<Integer> list = new ArrayList<>();
        int sqrt = (int) Math.sqrt(num);

        for (int i = 1; i <= sqrt; i++) {
            if (num % i == 0) {
                list.add(i);
                if (num / i != i) {
                    list.add(num / i);
                }
            }
        }
        return list;
    }
}

class Solution {
    public long solution(long n) {
        long answer = -1;
        double number = (double) Math.sqrt(n);
        if (number % 1 == 0) {
            answer = (long) (number + 1) * (long) (number + 1);
        }
        return answer;
    }
}