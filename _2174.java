import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int A,B,N,M;
	static String answer = "";
	static Robot[][] map;
	
	static String[] s = {"N", "W", "S", "E"};
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());		// y
		B = Integer.parseInt(st.nextToken());		// x
		
		map = new Robot[B][A];
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 초기 위치 세팅
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = B - Integer.parseInt(st.nextToken()); // 왼쪽 아래부터 시작하기 때문에 B크기에서 빼줘서 위치에 로봇 생성
			
			
			map[x][y] = new Robot(i + 1, check(st.nextToken()));
		}
		
		// 명령 횟수
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int robotNum = Integer.parseInt(st.nextToken());
			String direction = st.nextToken();
			int count = Integer.parseInt(st.nextToken());
			
			validation(robotNum, direction, count);
		}
		
		System.out.println("OK");
		
	}
	
	static int check (String compass) {
		int result = 0;
		for (int i = 0; i < s.length; i++) {
			if (s[i].equals(compass)) {
				result = i;
			}
		}
		return result;
	}
	
	static void validation (int robotNum, String command, int count) {
		Robot robot = null;
		int x = 0;
		int y = 0;
		
		// 로봇 찾기
		for (int i = 0; i< B; i++) {
			for (int j = 0; j < A; j++) {
				if (map[i][j] != null && map[i][j].getRobotNum() == robotNum) {
					robot = map[i][j];
					x = i;
					y = j;
				}
			}
		}
		
		for (int i = 0; i < count; i++) {
			if ("F".equals(command)) {
				
				// E
				if (3 == robot.getDirection()) { 
					if (y + i + 1 >= A) {
						info(robot.getRobotNum());
						System.exit(0);
					}
					
					if (map[x][y + i + 1] != null) {
						info(robot.getRobotNum(), map[x][y + i + 1].getRobotNum());
						System.exit(0);
					}
					
					map[x][y + i] = null;
					map[x][y + i + 1] = robot;
				}
				
				// W
				if (1 == robot.getDirection()) {
					if (y - (i + 1) < 0) {
						info(robot.getRobotNum());
						System.exit(0);
					}
					
					if (map[x][y - (i + 1)] != null) {
						info(robot.getRobotNum(), map[x][y - (i + 1)].getRobotNum());
						System.exit(0);
					}
					
					map[x][y - i] = null;
					map[x][y - (i + 1)] = robot;
				}
				
				// N
				if (0 == robot.getDirection()) {
					if (x - (i + 1) < 0) {
						info(robot.getRobotNum());
						System.exit(0);
					}
					
					if (map[x - (i + 1)][y] != null) {
						info(robot.getRobotNum(), map[x - (i + 1)][y].getRobotNum());
						System.exit(0);
					}
					
					map[x - i][y] = null;
					map[x - (i + 1)][y] = robot;
				}
				
				// S
				if (2 == robot.getDirection()) {
					if (x + i + 1>= B) {
						info(robot.getRobotNum());
						System.exit(0);
					}
					
					if (map[x + i + 1][y] != null) {
						info(robot.getRobotNum(), map[x + i + 1][y].getRobotNum());
						System.exit(0);
					}
					
					map[x + i][y] = null;
					map[x + i + 1][y] = robot;
				}
			}
			
			if ("L".equals(command)) {
				int direction = (robot.getDirection() + 1) % 4;
				robot.setDirection(direction);
			}
			
			if ("R".equals(command)) {
				int direction = (robot.getDirection() + 3) % 4;
				robot.setDirection(direction);
			}
			
			
		}
	}
	
	static void info (int robotNum) {
		System.out.println("Robot " + robotNum + " crashes into the wall");
	}
	
	static void info (int robotNum1, int robotNum2) {
		System.out.println("Robot " + robotNum1 + " crashes into robot " + robotNum2);
	}
	
	static class Robot {
		private int robotNum;
		private int direction;
		
		public int getRobotNum() {
			return robotNum;
		}
		
		public int getDirection() {
			return direction;
		}
		
		public void setRobotNum(int robotNum) {
			this.robotNum = robotNum;
		}
		
		public void setDirection(int direction) {
			this.direction = direction;
		}
		
		public Robot (int robotNum, int direction) {
			this.robotNum = robotNum;
			this.direction = direction;
		}
		
		@Override
		public String toString() {
			return "number :" + robotNum + " direction :" + direction;
		}
	}
}