import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj20208 {

	public static int map[][];
	public static boolean visited[];
	public static int dr[]= {-1, 1, 0, 0};
	public static int dc[]= {0, 0, -1, 1};
	public static int ans, N, M, H, milkCnt;
	public static int homeR, homeC;
	public static ArrayList<Pair> arr =  new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		// map 입력 받기
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					homeR = i; 
					homeC = j;
				}
				else if(map[i][j] == 2) {
					arr.add(new Pair(i, j));
					milkCnt++;
				}
			}
		}
		visited = new boolean[milkCnt];
		
		// dfs 탐색 시작
		dfs(new Pair(homeR, homeC), 0, M, false);
		
		System.out.println(ans);
	}
	public static void dfs(Pair now, int eatMilk, int hp, boolean home) {
		//System.out.println("("+now.r+","+now.c+"), eatMilk:"+eatMilk+", hp:"+hp+", home:"+home);
		if(home == true) {
			ans = Math.max(ans, eatMilk);
			return;
		}
		
		// 집으로 돌아가 보기
		int distToHome = distFromTo(now.r, now.c, homeR, homeC);
		if(hp >= distToHome) {
			dfs(new Pair(homeR, homeC), eatMilk, hp - distToHome, true);
		}
		
		// 우유 먹으러 가보기
		for(int i=0; i<milkCnt; i++) {
			if(visited[i]) continue;
			
			int distToMilk = distFromTo(now.r, now.c, arr.get(i).r, arr.get(i).c);
			if(hp >= distToMilk) {
				
				//System.out.println("("+i+")번째 우유 탐색");
				
				visited[i] = true;
				dfs(arr.get(i), eatMilk + 1, hp - distToMilk + H, false);
				visited[i] = false;
			}
		}		
	}
	public static int distFromTo(int nowR, int nowC, int milkR, int milkC) {
		//맨하탄 거리 계산
		return Math.abs(nowR - milkR) + Math.abs(nowC - milkC);
	}
}
class Pair{
	int r, c;
	
	Pair(int r, int c){
		this.r = r;
		this.c = c;
	}
}
