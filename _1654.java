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

        int maxLen = list.get(0);
        int minLen = 0;

        boolean check = true;

        int answer = 0;

        while (check)
        {
            int value = (maxLen + minLen) / 2;
            int count = 0;

            for (int i = 0; i < list.size(); i++)
            {
                count += list.get(i) / value;
            }

            if (count < totalCount) {
                maxLen = value;
            }

            if (count > totalCount) {
                minLen = value;
                answer = Math.max(answer, value);
            }

            if (minLen >= maxLen) {
                check = false;
            }
        }

        System.out.println(maxLen);
     }
}

/*
3 3
1000
1000
1

1 1
1

5 5
2
2
2
2
2

1 3
3

3 4
2
6
19

answer : 6
* */

