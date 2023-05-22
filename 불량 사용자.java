import java.util.*;

class Solution {
    boolean[] visit;
    boolean[] visitUser;
    Set<String> set = new HashSet<>();
    int answer = 0;
    public int solution(String[] user_id, String[] banned_id) {
        visit = new boolean[banned_id.length];
        visitUser = new boolean[user_id.length];

        recursion(0, user_id, banned_id, 0);
        return answer;
    }

    public void recursion(int depth, String[] userId, String[] bannedId, int idx) {
        if(depth == bannedId.length) {
            String setVal = "";
            for (int i = 0; i < visitUser.length; i++) {
                if (visitUser[i]) setVal += i;
            }

            if (!set.contains(setVal)) {
                set.add(setVal);
                answer++;
            }
            return;
        }

        for (int i = 0; i < bannedId.length; i++) {
            // 체크안한 밴 아이디만
            if (!visit[i]) {
                for (int j = idx; j < userId.length; j++) {
                    if (visitUser[j]) continue;
                    String user = userId[j];

                    // 두 계정이 같은 길이일때만
                    if (user.length() == bannedId[i].length()) {
                        boolean check = true;
                        for (int k = 0; k < user.length(); k++) {
                            char u = user.charAt(k);
                            char b = bannedId[i].charAt(k);

                            if (!('*' == b || u == b)) {
                                check = false;
                                break;
                            }
                        }

                        // 두 값이 맞을때만
                        if (check) {
                            visit[i] = true;
                            visitUser[j] = true;
                            recursion(depth + 1, userId, bannedId, j + 1);
                            visit[i] = false;
                            visitUser[j] = false;
                        }
                    }
                }
            }
        }
    }
}
