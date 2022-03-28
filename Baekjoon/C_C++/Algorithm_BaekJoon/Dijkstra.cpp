#include <cstdio>
#include <vector>
#include <queue>
using namespace std;
#define INF INT_MAX

typedef struct {
	int node;	// ����� ���� ��� ��ȣ
	int weight;	// ������ ����ġ
}Data;

vector <Data> v[6];	// v[x]: x�� ��忡 ����� ���� ���� ����ġ�� ����
int Dijk[6];	// Dijk[x]: x�� �������� �ִ� ��� ��
queue <int> q;

void MakeGraph() {

	// 1�� ���� ����� �׷��� ����
	v[1].push_back({ 2, 8 });
	v[1].push_back({ 3, 2 });
	// 2�� ���� ����� �׷��� ����
	v[2].push_back({ 1, 8 });
	v[2].push_back({ 4, 10 });
	// 3�� ���� ����� �׷��� ����
	v[3].push_back({ 1, 2 });
	v[3].push_back({ 4, 1 });
	v[3].push_back({ 5, 7 });
	// 4�� ���� ����� �׷��� ����
	v[4].push_back({ 2, 10 });
	v[4].push_back({ 5, 9 });
	// 5�� ���� ����� �׷��� ����
	v[5].push_back({ 3, 7 });
	v[5].push_back({ 4, 9 });
}

void InitDijkArray() {
	for (int i = 1; i <= 5; i++) {
		Dijk[i] = INF;
	}
}
void InitQueue() {
	q.push(1);		// �����ϴ� 1�� ��� ����
	Dijk[1] = 0;	// Dijk[1] = 0���� ����
}
void DijkStra() {

	// queue�� ��~ �������� ���ͽ�Ʈ�� �˰��� �ݺ� ����
	while (!q.empty()) {
	
		// queue �� �� ��� ��������
		int nowNode = q.front();
		q.pop();


		// nowNode�� ����Ǿ� �ִ� �ٸ� ��� ��� Ž��
		for (int i = 0; i < v[nowNode].size(); i++) {
			int nextNode = v[nowNode][i].node;
			int weight = v[nowNode][i].weight;

			// ( ���� ��忡 ����� �� > ���� ���� �湮�ϸ鼭 �Һ��� ����ġ�� �� ) �϶��� �� ���� & ť ����
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