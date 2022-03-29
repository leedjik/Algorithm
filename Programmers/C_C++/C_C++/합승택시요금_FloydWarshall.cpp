#include <string>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;
#define INF 1000000000

typedef struct {
	int next, val;
}vtx;
vector <vtx> v[201];
int FloydWarshallCost[201][201];

void FloydWarshall(int n, int s, int a, int b) {

	// 노드간 거리 초기화
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (i == j) FloydWarshallCost[i][j] = 0;
			else {
				if (FloydWarshallCost[i][j] == -1) FloydWarshallCost[i][j] = INF;
			}
		}
	}

	// 플로이드 와샬 알고리즘
	for (int k = 1; k <= n; k++) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				FloydWarshallCost[i][j] = min(FloydWarshallCost[i][j], FloydWarshallCost[i][k] + FloydWarshallCost[k][j]);
			}
		}
	}
}
int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
	int answer = INF;
	int faresSize = fares.size();

	memset(FloydWarshallCost, -1, sizeof(FloydWarshallCost));

	// 주어진 간선 초기화
	for (int i = 0; i < faresSize; i++) {
		int node1 = fares[i][0];
		int node2 = fares[i][1];
		int cost = fares[i][2];

		FloydWarshallCost[node1][node2] = FloydWarshallCost[node2][node1] = cost;
	}
	

	FloydWarshall(n, s, a, b);
	
	for (int i = 1; i <= n; i++) {
		// (s -> i) + (i -> a) + (i -> b) 최소합 갱신

		int sum = FloydWarshallCost[s][i] + FloydWarshallCost[i][a] + FloydWarshallCost[i][b];

		if (sum < answer) answer = sum;
	}


	return answer;
}