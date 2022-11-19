
import java.util.*;

public class Main {
    static List<Integer> diviserList = new ArrayList<>();

    public static void main(String[] args) {
        // Input Value
        int number = 10;
        int limit = 3;
        int power = 2;
        int answer = 0;

        Main.diviserListInit(number);

        for (int num: diviserList) {
            if (num > limit) {
                answer += power;
            } else {
                answer += num;
            }
        }

        System.out.println(answer);
    }

    static void diviserListInit(int num) {
        for (int i = 1; i <= num; i++) {
            int count = countDivideNumber(i);
            diviserList.add(count);
        }
    }

    private static int countDivideNumber(int number) {
        int count = 0;

        int sqrt = (int) Math.sqrt(number); // 제곱근
        for (int j = 1; j <= sqrt; j++) {
            if (number % j == 0) {
                count++;
                if (number / j != j) {
                    count++;
                }
            }
        }

        return count;
    }
}

