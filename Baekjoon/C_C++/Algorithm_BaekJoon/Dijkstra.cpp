#include <cstdio>
#include <vector>
#include <queue>
using namespace std;
#define INF INT_MAX

typedef struct {
	int node;	// 연결된 다음 노드 번호
	int weight;	// 간선의 가중치
}Data;

vector <Data> v[6];	// v[x]: x번 노드에 연결된 다음 노드와 가중치의 정보
int Dijk[6];	// Dijk[x]: x번 노드까지의 최단 경로 값
queue <int> q;

void MakeGraph() {

	// 1번 노드와 연결된 그래프 생성
	v[1].push_back({ 2, 8 });
	v[1].push_back({ 3, 2 });
	// 2번 노드와 연결된 그래프 생성
	v[2].push_back({ 1, 8 });
	v[2].push_back({ 4, 10 });
	// 3번 노드와 연결된 그래프 생성
	v[3].push_back({ 1, 2 });
	v[3].push_back({ 4, 1 });
	v[3].push_back({ 5, 7 });
	// 4번 노드와 연결된 그래프 생성
	v[4].push_back({ 2, 10 });
	v[4].push_back({ 5, 9 });
	// 5번 노드와 연결된 그래프 생성
	v[5].push_back({ 3, 7 });
	v[5].push_back({ 4, 9 });
}

void InitDijkArray() {
	for (int i = 1; i <= 5; i++) {
		Dijk[i] = INF;
	}
}
void InitQueue() {
	q.push(1);		// 시작하는 1번 노드 삽입
	Dijk[1] = 0;	// Dijk[1] = 0으로 갱신
}
void DijkStra() {

	// queue가 텅~ 빌때까지 다익스트라 알고리즘 반복 수행
	while (!q.empty()) {
	
		// queue 맨 앞 노드 가져오기
		int nowNode = q.front();
		q.pop();


		// nowNode와 연결되어 있는 다른 모든 노드 탐색
		for (int i = 0; i < v[nowNode].size(); i++) {
			int nextNode = v[nowNode][i].node;
			int weight = v[nowNode][i].weight;

			// ( 다음 노드에 저장된 값 > 다음 노드로 방문하면서 소비할 가중치의 합 ) 일때만 값 갱신 & 큐 삽입
			if (Dijk[nextNode] > Dijk[nowNode] + weight) {
				Dijk[nextNode] = Dijk[nowNode] + weight;

				q.push(nextNode);
			}
		}
	}
}
int main(void) {
	MakeGraph();
	InitDijkArray();
	InitQueue();

	DijkStra();

	for (int i = 1; i <= 5; i++) {
		printf("Dijk[%d] = %d\n", i, Dijk[i]);
	}
}