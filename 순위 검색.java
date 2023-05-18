import java.util.*;
class Solution {
    Map<String, int[]> map = new HashMap<>();
    List<Applicant> list = new ArrayList<>();
    int[] answer;
    public int[] solution(String[] info, String[] query) {
        answer = new int[query.length];
        list = info(info);
        settingMap(list);

        for (int i = 0; i < query.length; i++) {
            answer[i] = check(query[i]);
        }


        return answer;
    }

    public int check(String query) {
        String[] split = query.split(" ");
        String language = split[0];
        String type = split[2];
        String career = split[4];
        String taste = split[6];
        String score = split[7];

        int start = 0;
        int end = list.size() - 1;

        if (!"-".equals(language)) {
            int[] idx = map.get(language);
            start = idx[0];
            end = idx[1];
        }

        int count = 0;
        for (int i = start; i <= end; i++) {
            if (("-".equals(type) || list.get(i).type.equals(type))
                    && ("-".equals(career) || list.get(i).career.equals(career))
                    && ("-".equals(taste) || list.get(i).taste.equals(taste))
                    && ("-".equals(score) || list.get(i).score >= Integer.parseInt(score))
            ) {
                count++;
            }
        }
        return count;
    }

    private void settingMap(List<Applicant> list) {
        String language = "";
        int start = 0;
        for (int i = 0; i < list.size(); i++) {
            Applicant applicant = list.get(i);
            if (!language.equals(applicant.language)) {
                if (language.equals("")) {
                    start = i;
                    language = applicant.language;
                } else {
                    map.put(language, new int[]{start, i - 1});
                    start = i;
                    language = applicant.language;
                }
            }
        }
        map.put(language, new int[]{start, list.size() - 1});
    }

    public List<Applicant> info(String[] info) {
        List<Applicant> list = new ArrayList<>();

        for (int i = 0; i < info.length; i++) {
            StringTokenizer st = new StringTokenizer(info[i]);
            list.add(new Applicant(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(list);

        return list;
    }
}

class Applicant implements Comparable<Applicant> {
    String language;
    String type;
    String career;
    String taste;
    int score;

    public Applicant(String language,String type,String career,String taste,int score) {
        this.language = language;
        this.type = type;
        this.career = career;
        this.taste = taste;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "language='" + language + '\'' +
                ", type='" + type + '\'' +
                ", career='" + career + '\'' +
                ", taste='" + taste + '\'' +
                ", score=" + score +
                '}';
    }

    // 점수만 내림차순
    @Override
    public int compareTo(Applicant o) {
        if (language.compareTo(o.language) == 0) {
            if (type.compareTo(o.type) == 0) {
                if (career.compareTo(o.career) == 0) {
                    if (taste.compareTo(o.taste) == 0) {
                        return o.score - score;
                    } else {
                        return taste.compareTo(o.taste);
                    }
                } else {
                    return career.compareTo(o.career);
                }
            } else {
                return type.compareTo(o.type);
            }
        } else {
            return language.compareTo(o.language);
        }
    }
}
