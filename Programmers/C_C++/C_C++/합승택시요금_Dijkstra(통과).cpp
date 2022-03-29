#include <string>
#include <vector>
#include <queue>
using namespace std;
#define INF 2100000000

typedef struct {
	int to, val;
}vtx;

priority_queue <int> q;
vector <vtx> v[201];
int cost[201][201];
int faresSize;
int dijk[201];

// start -> end �ִ� ��� ���ϱ�
int dijkstra(int n, int start, int end) {
	for (int i = 1; i <= n; i++) {
		dijk[i] = INF;
	}

	dijk[start] = 0;
	q.push(start);

	while (!q.empty()) {
		int idx = q.top();
		q.pop();

		for (int i = 0; i < v[idx].size(); i++) {
			int next = v[idx][i].to;
			int val = v[idx][i].val;

			if (dijk[next] > dijk[idx] + val) {
				dijk[next] = dijk[idx] + val;
				q.push(next);
			}
		}
	}
	return dijk[end];
}
int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
	int answer = INF;
	faresSize = fares.size();

	// Ʈ�� ����
	for (int i = 0; i < faresSize; i++) {
		int node1 = fares[i][0];
		int node2 = fares[i][1];
		int fee = fares[i][2];

		v[node1].push_back({ node2, fee });
		v[node2].push_back({ node1, fee });
		cost[node1][node2] = cost[node2][node1] = fee;
	}

	for (int i = 1; i <= n; i++) {
		// (s -> i �ִܰŸ�) + (i -> a �ִܰŸ�) + (i -> b �ִܰŸ�)
		int res = dijkstra(n, s, i) + dijkstra(n, i, a) + dijkstra(n, i, b);

		if (answer > res) answer = res;
	}

	return answer;
}