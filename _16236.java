import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int N;
    static int time = 0;
    static int[][] map;
    static List<List<Fish>> list = new ArrayList<>();

    // 아기상어 위치
    static int sharkX = 0;
    static int sharkY = 0;
    static int sharkSize = 2;
    static int eatCount = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i <= 6; i++) {
            list.add(new ArrayList<Fish>());
        }


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());

                if (num == 9) {
                    sharkX = i;
                    sharkY = j;
                } else if (num > 0) {
                    map[i][j] = num;
                    list.get(num).add(new Fish(i, j, num, 0));
                }
            }
        }

        // bfs를 통한 처리
        while (true) {
            Queue<Fish> pq = new PriorityQueue<>();
            Queue<int[]> bfs = new LinkedList<>();

            bfs.add(new int[]{sharkX, sharkY, 0});

            boolean[][] visit = new boolean[N][N];
            visit[sharkX][sharkY] = true;

            // 완탐을 해야되겠지??
            while(!bfs.isEmpty()) {
                int[] info = bfs.poll();
                quest(info, bfs, pq, visit);
            }

            if (!pq.isEmpty()) {
                Fish eat = pq.poll();

                map[sharkX][sharkY] = 0;
                map[eat.x][eat.y] = 0;

                sharkX = eat.x;
                sharkY = eat.y;

                time += eat.distance;
                eatCount++;

                if (eatCount == sharkSize) {
                    sharkSize++;
                    eatCount = 0;
                }
            } else {
                break;
            }
        }

        System.out.println(time);
    }

    public static void quest(int[] info, Queue<int[]> bfs, Queue<Fish> pq, boolean[][] visit) {
        for (int i = 0; i < 4; i++) {
            int nxtX = info[0] + dx[i];
            int nxtY = info[1] + dy[i];

            // 범위 벗어나면
            if (nxtX < 0 || nxtY < 0 || nxtX >= N || nxtY >= N || visit[nxtX][nxtY]) {
                continue;
            }

            // 먹을게 없거나 지나갈 수는 있을 때
            if (map[nxtX][nxtY] == 0 || map[nxtX][nxtY] == sharkSize) {
                visit[nxtX][nxtY] = true;
                bfs.add(new int[]{nxtX, nxtY, info[2] + 1});
                continue;
            }

            if (map[nxtX][nxtY] < sharkSize) {
                if (!pq.isEmpty()) {
                    Fish minFish = pq.peek();
                    if (minFish.distance < info[2] + 1) {
                        return;
                    }
                }

                visit[nxtX][nxtY] = true;
                pq.add(new Fish(nxtX, nxtY, map[nxtX][nxtY], info[2] + 1));
            }
        }

    }

    static class Fish implements Comparable<Fish>{
        int x;
        int y;
        int size;
        int distance;

        public Fish(int x, int y, int size, int distance) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.distance = distance;
        }

        @Override
        public int compareTo(Fish o) {
            // 거리가 짧은순, 거리가 같다면 더 높이있는 순, 높이도 같다면 더 왼쪽 순
            if (this.distance == o.distance) {
                if (this.x == o.x) {
                    return this.y - o.y;
                }
                return this.x - o.x;
            }
            return this.distance - o.distance;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "x=" + x +
                    ", y=" + y +
                    ", size=" + size +
                    ", distance=" + distance +
                    '}';
        }
    }
}

