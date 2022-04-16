#include <cstdio>
#include <vector>
#include <queue>
using namespace std;
#define INF INT_MAX

typedef struct {
	int node;	// ����� ���� ��� ��ȣ
	int weight;	// ������ ����ġ
}Data;

struct cmp {
	bool operator()(Data a, Data b) {
		return a.weight > b.weight;	// ����ġ ��������
	}
};

vector <Data> v[6];	// v[x]: x�� ��忡 ����� ���� ���� ����ġ�� ����
int Dijk[6];	// Dijk[x]: x�� �������� �ִ� ��� ��

priority_queue <Data, vector<Data>, cmp> pq;

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
void InitQueue(int start) {
	pq.push({start, 0});	// �����ϴ� ��� ����
	Dijk[start] = 0;		// Dijk[����] = 0���� ����
}
void DijkStra() {

	// priority_queue�� ��~ �������� ���ͽ�Ʈ�� �˰��� �ݺ� ����
	while (!pq.empty()) {
	
		// priority_queue �� �� ��� ��������
		Data nowNode = pq.top();
		pq.pop();

		int node = nowNode.node;
		int weightSum = nowNode.weight;

		if (Dijk[node] < weightSum) continue;	//�̹� ������ �� ���� ����ġ�� Dijk[node]�� ������

		// nowNode�� ����Ǿ� �ִ� �ٸ� ��� ��� Ž��
		for (int i = 0; i < v[node].size(); i++) {
			int nextNode = v[node][i].node;
			int weight = v[node][i].weight;

			// ( ���� ��忡 ����� �� > ���� ���� �湮�ϸ鼭 �Һ��� ����ġ�� �� ) �϶��� �� ����, �׸��� pq����
			if (Dijk[nextNode] > weight + weightSum) {
				Dijk[nextNode] = weight + weightSum;

				pq.push({nextNode, Dijk[nextNode]});
			}
		}
	}
}
int main(void) {
	MakeGraph();
	InitDijkArray();
	InitQueue(1);	//��� 1�� �����̶�� ����

	DijkStra();

	for (int i = 1; i <= 5; i++) {
		printf("Dijk[%d] = %d\n", i, Dijk[i]);
	}
}