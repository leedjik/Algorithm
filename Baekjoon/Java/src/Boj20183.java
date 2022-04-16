import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj20183 {

	public static int N, M, A, B;
	public static long C;
	public static ArrayList<ArrayList<aPair>> arr;
	public static Queue<qPair> q = new LinkedList<qPair>();
	public static vPair[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Long.parseLong(st.nextToken());

		visited = new vPair[N + 1];

		for (int i = 0; i < N + 1; i++) {
			arr.add(new ArrayList<aPair>());
			visited[i] = new vPair(Integer.MAX_VALUE, 0);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());

			arr.get(node1).add(new aPair(node2, val));
			arr.get(node2).add(new aPair(node1, val));
		}

		initQ();
		int ans = bfs();

		System.out.println(ans);
	}

	public static int bfs() {

		while (!q.isEmpty()) {

			qPair now = q.poll();
			int idx = now.idx;
			long remainMoney = now.remainMoney;
			int maxShame = now.maxShame;

			// B에 도착해버림.
			if (idx == B)
				continue;

			for (int i = 0; i < arr.get(idx).size(); i++) {
				int nextIdx = arr.get(idx).get(i).node;
				int val = arr.get(idx).get(i).val;
				int nextMaxShame = maxShame;
				long nextRemainMoney = remainMoney - val;

				// 수치심이 더 커지기 때문에 가지 않는다.
				if (val > nextMaxShame)
					nextMaxShame = val;

				// 돈이 부족하면 가지 않는다.
				if (nextRemainMoney < 0)
					continue;

				// 수치심이 같더라도 비용이 저렴할때만 간다. -> 요금의 최솟값!
				if (nextMaxShame == visited[nextIdx].shame) {
					if (nextRemainMoney > visited[nextIdx].money) {
						visited[nextIdx].money = nextRemainMoney;
						q.add(new qPair(nextIdx, nextRemainMoney, nextMaxShame));
					}
				}
				// 수치심이 적으면 비용이 더 들더라도 움직인다.
				else if (nextMaxShame < visited[nextIdx].shame) {
					visited[nextIdx].money = nextRemainMoney;
					visited[nextIdx].shame = nextMaxShame;
					q.add(new qPair(nextIdx, nextRemainMoney, nextMaxShame));
				}
			}
		}

		if (visited[B].shame == Integer.MAX_VALUE)
			return -1;
		else
			return visited[B].shame;
	}

	public static void initQ() {
		q.add(new qPair(A, C, 0));
		visited[A].shame = 0;
		visited[A].money = C;
	}

	// ArrayList에서 사용할 Pair
	public static class aPair {
		int node, val;

		aPair(int node, int val) {
			this.node = node;
			this.val = val;
		}
	}

	// visited에서 사용할 Pair
	public static class vPair {
		int shame;
		long money;

		vPair(int shame, long money) {
			this.shame = shame;
			this.money = money;
		}
	}

	// queue에서 사용할 Pair
	public static class qPair {
		int idx, maxShame;
		long remainMoney;

		qPair(int idx, long rm, int ms) {
			this.idx = idx;
			this.remainMoney = rm;
			this.maxShame = ms;
		}
	}
}
