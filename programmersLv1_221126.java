import java.util.Arrays;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        Arrays.sort(score);

        for (int i = score.length - 1; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            boolean check = true;
            for (int j = 0; j < m; j++) {
                if(i - j < 0) {
                    check = false;
                    break;
                }
                min = Math.min(min, score[i - j]);
            }
            if (check) {
                answer += min * m;
            }
            i -= m - 1;
        }
        System.out.println(answer);
        return answer;
    }
}

class Solution {
    public String solution(String new_id) {
        String answer = "";
        // 대문자 -> 소문자로 변경
        answer = new_id.toLowerCase();
        // 특수문자 제거
        answer = checkSpecialWord(answer);
        // 마침표 제거
        answer = checkFirstAndLastFullStop(answer);
        // 빈문자열 체크
        answer = checkTrim(answer);
        // 마침표 중복 제거
        answer = continuityFullStop(answer);
        // 최대길이 체크(15자리)
        answer = checkLength(answer);
        // 최소길이 체크 (3자리)
        answer = checkThreeLength(answer);

        return answer;
    }

    static String checkThreeLength(String id) {
        if (id.length() < 3) {
            String lastText = String.valueOf(id.charAt(id.length() - 1));
            for (int i = id.length() - 1; i < 2; i++) {
                id += lastText;
            }
        }
        return id;
    }

    static String continuityFullStop (String id) {
        StringBuilder br = new StringBuilder();
        char preText = ' ';
        for (int i = 0; i < id.length(); i++) {
            if ('.' != id.charAt(i)) {
                br.append(String.valueOf(id.charAt(i)));
            } else {
                if (preText != id.charAt(i)) {
                    br.append(String.valueOf(id.charAt(i)));
                }
            }
            preText = id.charAt(i);
        }

        return br.toString();
    }

    static String checkLength(String id) {
        if (id.length() >= 15) {
            String result = id.substring(0, 15);
            if ('.' == id.charAt(14)) {
                result = id.substring(0, 14);
            }
            return result;
        }

        return id;
    }
    static String checkTrim(String id) {
        if (id.trim().length() == 0) {
            id = "a";
        }
        return id;
    }

    static String checkFirstAndLastFullStop(String id) {
        int head = 0;
        int tail = id.length();

        // 앞쪽 마침표 위치 찾기
        for (int i = 0; i < id.length(); i++) {
            if ('.' != id.charAt(i)) {
                head = i;
                break;
            }
            head = i;
        }
        id = id.substring(head, tail);

        head = 0;
        tail = id.length();
        // 뒤쪽 마침표 위치 찾기
        for (int i = id.length() - 1; i >= 0; i--) {
            if ('.' != id.charAt(i)) {
                break;
            }
            tail = i;
        }
        return id.substring(head, tail);
    }

    static String checkSpecialWord(String id) {
        return id.replaceAll("~","")
                .replaceAll("!","")
                .replaceAll("@","")
                .replaceAll("#","")
                .replaceAll("\\$","")
                .replaceAll("%","")
                .replaceAll("\\^","")
                .replaceAll("&","")
                .replaceAll("\\*","")
                .replaceAll("\\(","")
                .replaceAll("\\)","")
                .replaceAll("=","")
                .replaceAll("\\+","")
                .replaceAll("\\[","")
                .replaceAll("\\{","")
                .replaceAll("]","")
                .replaceAll("}","")
                .replaceAll(":","")
                .replaceAll("\\?","")
                .replaceAll(",","")
                .replaceAll("<","")
                .replaceAll(">","")
                .replaceAll("/","");
    }
}