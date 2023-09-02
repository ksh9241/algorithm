import java.util.*;

public class Main {
    static String problem = "4+12-7*3+2"; // 4 + 12 - 21 + 2 = -3
//    static String problem = "4*12-7*3+2"; // 48 - 21 + 2 = 29
    public static void main(String[] args) {
        System.out.println(calculator());
     }

    static int calculator() {
        Deque<String> deque = new LinkedList<>();

        int preIdx = 0;

        for (int i = 0; i < problem.length(); i++) {
            char c = problem.charAt(i);

            if (checkSign(c)) {
                String inputValue = problem.substring(preIdx, i);

                if (!deque.isEmpty() && "*".equals(deque.peekLast())) {
                    String sign = deque.pollLast();
                    String num = deque.pollLast();

                    int calcNum = Integer.parseInt(inputValue) * Integer.parseInt(num);

                    inputValue = String.valueOf(calcNum);
                }

                deque.add(inputValue);
                deque.add(String.valueOf(c));
                preIdx = i + 1;
            }
        }

        deque.add(problem.substring(preIdx, problem.length()));

        System.out.println(deque);

        int resultNum = Integer.parseInt(deque.pollFirst());

        while (!deque.isEmpty()) {
            String sign = deque.pollFirst();
            switch (sign) {
                case "+" :
                    resultNum += Integer.parseInt(deque.pollFirst());
                    break;
                case "-" :
                    resultNum -= Integer.parseInt(deque.pollFirst());
                    break;
            }
        }

        return resultNum;
    }

    static boolean checkSign(char sign) {
        return sign == '+' || sign == '-' || sign == '*';
    }
}
