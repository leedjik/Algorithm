#include <string>
#include <vector>
#include <queue>
using namespace std;
#define INF 2100000000

typedef struct {
	int to, val;
}vtx;

struct cmp {
	bool operator()(const pair<int, int> &a, const pair<int, int> &b) {
		return a.second > b.second;
	}
};

priority_queue <pair<int, int>, vector<pair<int, int>>, cmp> q;
vector <vtx> v[201];
int cost[201][201];
int faresSize;
int dijk[3][201]; // dijk[0]: s시작점, dijk[1]: a시작점, dijk[2]: b시작점

// start -> end 최단 경로 구하기
void dijkstra(int which, int n, int start) {
	for (int i = 1; i <= n; i++) {
		dijk[which][i] = INF;  
	}

	dijk[which][start] = 0;
	q.push({start, 0});

	while (!q.empty()) {
		int idx = q.top().first;
		int cost = q.top().second;
		q.pop();

		if (cost > dijk[which][idx]) continue;

		for (int i = 0; i < v[idx].size(); i++) {
			int next = v[idx][i].to;
			int val = v[idx][i].val;

			if (dijk[which][next] > dijk[which][idx] + val) {
				dijk[which][next] = dijk[which][idx] + val;
				q.push({next, dijk[which][next]});
			}
		}
	}
}
int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
	int answer = INF;
	faresSize = fares.size();

	// 트리 생성
	for (int i = 0; i < faresSize; i++) {
		int node1 = fares[i][0];
		int node2 = fares[i][1];
		int fee = fares[i][2];

		v[node1].push_back({ node2, fee });
		v[node2].push_back({ node1, fee });
		cost[node1][node2] = cost[node2][node1] = fee;
	}

	// (1) s에서 모든 정점까지의 최단 거리
	dijkstra(0, n, s);
	// (2) a에서 모든 정점까지의 최단 거리
	dijkstra(1, n, a);
	// (3) b에서 모든 정점까지의 최단 거리
	dijkstra(2, n, b);

	// (s -> i) + (i -> a) + (i -> b)
	// 가 최소인 합 구하기

	for (int i = 1; i <= n; i++) {
		int sum = dijk[0][i] + dijk[1][i] + dijk[2][i];

		if (answer > sum) {
			answer = sum;
		}
	}


	return answer;
}