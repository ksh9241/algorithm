boolean[] visit = new boolean[1_000_001];
    List<List<Integer>> list = new ArrayList<>();

    int donutCount = 0; // idx 1
    int lineCount = 0; // idx 2
    int eightCount = 0; // idx 3

    public int[] solution(int[][] edges) {
        // Output 0번째 생성 정점, 1번째 도넛모양 개수(자기자신), 2번째 막대 그래프 개수, 3번째 8자모양 개수
        int[] answer = new int[4];

        for (int i = 0; i < 1_000_001; i++) {
            list.add(new ArrayList<Integer>());
        }
        // 노드 세팅
        for (int i = 0 ; i < edges.length; i++) {
            int idx = edges[i][0];
            int value = edges[i][1];

            list.get(idx).add(value);
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).size() > 0) {
                System.out.println(i + " == " + list.get(i));
            }
        }


        // 8 자 탐색
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).size() == 2 && !visit[i]) {
                checkEight(i);
            }
        }

        // 도넛 찾기
        // 시작 노드가 종료노드와 같을 경우
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).size() == 1 && !visit[i]) {
                checkDonut(i);
            }
        }

        System.out.println(donutCount + " / " + eightCount);

        // 생성 노드 (8자노드, 도넛노드 빼고 남은 노드중 List가 가장 많은 노드)

        return answer;
    }

    public void checkDonut(int startIdx) {
        Set<Integer> checkSet = new HashSet<>();
        boolean flag = false;

        Queue<Integer> q = new LinkedList<>();
        q.add(startIdx);

        while (!q.isEmpty()) {
            int idx = q.poll();
            checkSet.add(idx);

            if (startIdx == list.get(idx).get(0)) {
                flag = true;
                break;
            }

            if (list.get(idx).size() == 1) {
                q.add(list.get(idx).get(0));
            }
        }

        if (flag) {
            donutCount++;

            for (int i : checkSet) {
                visit[i] = true;
            }
        }
    }

    public void checkEight(int startIdx) {
        Set<Integer> checkSet = new HashSet<>();
        int upCount = 0;
        int downCount = 0;
        boolean flag = true;

        for (int i = 0; i < list.get(startIdx).size(); i++) {

            Queue<Integer> q = new LinkedList<>();
            q.add(list.get(startIdx).get(i));

            while(!q.isEmpty()) {
                int idx = q.poll();
                checkSet.add(idx);

                // 한바퀴 탐색 후 시작노드로 돌아왔을 때 종료 조건
                if (startIdx == idx) {
                    break;
                }

                if (i % 2 == 0) {
                    upCount++;
                } else {
                    downCount++;
                }

                // 연결고리 노드가 1개 이상이면 조건 불 충분
                if (list.get(idx).size() != 1 || list.get(idx).get(0) == idx) {
                    flag = false;
                    break;
                }

                q.add(list.get(idx).get(0));
            }
        }

        if (flag && upCount == downCount) {
            eightCount++;

            for (int i : checkSet) {
                visit[i] = true;
            }
        }
    }
