import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Boj1717 {

	static public int parent[];
	

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		init(N);
		
		int type, a, b;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			type = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			
			if(type == 0) {
				union(a, b);
			}
			else {
				if(find(a) == find(b)) {
					bw.write("YES\n");
				}
				else {
					bw.write("NO\n");
				}
			}
		}
		bw.close();
	}

	static public void union(int x, int y) {
		int xParent = find(x);
		int yParent = find(y);
		
		parent[xParent] = yParent;
	}
	
	static public int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
		
	}
	static public void init(int N) {
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}
	}
}
