import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj20166 {
	public static int N, M, K, ans;
	public static char[][] board;
	public static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	public static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	public static Map<String, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		
		for(int i=0; i<N; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		for(int i=0; i<K; i++) {
			String godLikesInput = br.readLine();
			
			// 이미 탐색한 적 있는 문자열이라면 value만 가져오기
			if(map.containsKey(godLikesInput)) {
				System.out.println(map.get(godLikesInput));
				continue;
			}
			
			char[] godLikes = godLikesInput.toCharArray();
			ans = 0;
			
			for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					if(board[r][c] == godLikes[0]) {

						dfs(r, c, 0, godLikes);
					}
				}
			}
			//map에 key,value 저장
			map.put(godLikesInput, ans);
			System.out.println(ans);
		}
	}
	public static void dfs(int r, int c, int idx, char[] likesArray) {
		if(idx == likesArray.length - 1) {
			// base condition
			ans++;
			return;
		}
		
		for(int d=0; d<8; d++) {
			int nr = r+dr[d], nc = c+dc[d];
			
			int[] arr = {nr, nc};
			canNext(arr);
			nr = arr[0];
			nc = arr[1];

			if(board[nr][nc] == likesArray[idx + 1]) {
				dfs(nr, nc, idx + 1, likesArray);
			}
		}
	}
	
	public static void canNext(int[] arr) {
		// 주어지는 board 범위 벗어나도 반대편으로 간다.
		if(arr[0] < 0) arr[0] = N - 1;
		if(arr[1] < 0) arr[1] = M - 1;
		if(arr[0] >= N) arr[0] = 0;
		if(arr[1] >= M) arr[1] = 0;
	}
}
