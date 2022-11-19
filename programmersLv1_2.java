
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k = 2;

        Map<String, Set<String>> reportList = new HashMap<>();
        int[] answer = new int[id_list.length];

        for (int i = 0; i < id_list.length; i++) {
            reportList.put(id_list[i], new HashSet<>());
        }

        for (int i = 0; i < report.length; i++) {
            StringTokenizer st = new StringTokenizer(report[i]);
            String reporter = st.nextToken();   // 신고자
            String criminal = st.nextToken();   // 신고당한사람

            reportList.get(criminal).add(reporter);
        }
    

        for (String key:reportList.keySet()) {
            Set<String> reports = reportList.get(key);
            if (reports.size() >= k) {
                for (String val:reports) {
                    for (int i = 0; i < id_list.length; i++) {
                        if (val.equals(id_list[i])) {
                            answer[i]++;
                            break;
                        }
                    }
                }
            }
        }
    }
}

