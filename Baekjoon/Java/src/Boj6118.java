import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj6118 {

	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static Queue<Pair> q = new LinkedList<>();
	static int[] dist;
	static boolean[] visited;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N + 1];
		dist = new int[N + 1];

		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		// Q ½ÇÇà
		solve();
	}
	public static void solve() {
		int maxDist = 0;
		
		visited[1] = true;
		q.add(new Pair(1, 0));
		
		while(!q.isEmpty()) {
			Pair now = q.poll();
			
			for(int next : graph.get(now.num)) {
				if(visited[next]) continue;
				visited[next] = true;
				dist[next] = now.dist + 1;
				q.add(new Pair(next, dist[next]));
				
				if(dist[next] > maxDist)
					maxDist = dist[next];
			}
		}
		
		int numHide = 0;
		int numSameDist = 0;
		
		for(int i=1; i<=N; i++) {
			if(dist[i] == maxDist) {
				if(numHide == 0) numHide = i;
				
				numSameDist++;
			}
		}
		System.out.println(numHide + " " + maxDist + " " + numSameDist);
	}
	private static class Pair{
		int num, dist;
		
		Pair(int num, int dist){
			this.num = num;
			this.dist = dist;
		}
	}
}
