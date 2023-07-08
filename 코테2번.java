import java.util.*;
    class Solution {
        public int solution(int[][] relationships, int target, int limit) {
            List<List<Integer>> list = new ArrayList<>();
            // limit은 거처가는 친구

            for (int i = 0; i < 101; i++) {
                list.add(new ArrayList<Integer>());
            }

            for (int i = 0; i < relationships.length; i++) {
                int[] friend = relationships[i];

                list.get(friend[0]).add(friend[1]);
                list.get(friend[1]).add(friend[0]);
            }

            Queue<Friend> q = new LinkedList<>();
            q.add(new Friend(target, 0));


            int friendCount = 0;
            int answer = 0;

            boolean[] visit = new boolean[101];
            visit[target] = true;
            while (!q.isEmpty()) {
                Friend f = q.poll();
                if (f.depth > limit) {
                    break;
                }
                friendCount++;
                answer += f.depth == 0 ? 2 : f.depth == 1 ? 5 : 10;
                for (int i = 0; i < list.get(f.number).size(); i++) {
                    if (!visit[list.get(f.number).get(i)]) {
                        q.add(new Friend(list.get(f.number).get(i), f.depth + 1));
                        visit[list.get(f.number).get(i)] = true;
                    }

                }
            }

            return answer+ friendCount - 1;
        }
    }

    class Friend {
        int number;
        int depth;

        public Friend(int number, int depth) {
            this.number = number;
            this.depth = depth;
        }
    }
