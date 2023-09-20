import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int inputCnt = Integer.parseInt(st.nextToken());
        int totalCount =  Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < inputCnt; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(list, Collections.reverseOrder());

        long maxLen = list.get(0);
        long minLen = 1;

        boolean check = true;

        long answer = 0;

        long value = maxLen;
        int count = 0;

        for (int i = 0; i < list.size(); i++)
        {
            count += list.get(i) / value;
        }

        if (count >= totalCount) {
            answer = Math.max(answer, value);
        }

        while (check)
        {
            value = (maxLen + minLen) / 2;
            count = 0;

            for (int i = 0; i < list.size(); i++)
            {
                count += list.get(i) / value;
            }

            if (count < totalCount) {
                maxLen = value;
            }

            if (count >= totalCount) {
                answer = Math.max(answer, value);
                if (minLen == value) {
                    value++;
                }
                minLen = value;
            }

            if (minLen >= maxLen) {
                check = false;
            }
        }

        System.out.println(answer);
    }
}
