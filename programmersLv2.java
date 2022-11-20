
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 24 24 * 1, 12 * 2, 8 * 3, 6 * 4
        int brown = 24;
        int yellow = 24;

        int total = brown + yellow;

        int[] answer = new int[2];
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i <= yellow; i++) {
            if (yellow % i == 0) {
                list.add(i);
            }
        }

        for(int val : list) {
            int width = val + 2;
            int height = (yellow / val) + 2;

            if ((width * height) == total && width >= height) {
                answer[0] = val + 2;
                answer[1] = (yellow / val) + 2;
                break;
            }
        }

        for (int val : answer) {
            System.out.println(val);
        }
    }
}

