#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;
#define INF 1000000000
typedef struct {
	int node, weight;
}INFO;
vector <INFO> v[6];
int ArrayFY[6][6];

void InitFY() {
	// (1) ��� ���� 0 Ȥ�� INF�� �ʱ�ȭ
	for (int i = 1; i <= 5; i++) {
		for (int j = 1; j <= 5; j++) {
			if (i == j) ArrayFY[i][j] = 0;
			else ArrayFY[i][j] = INF;
		}
	}

	// (2) �׷����� �׷��� ���� ���� �ֱ�
	for (int i = 1; i <= 5; i++) {
		for (int j = 0; j < v[i].size(); j++) {
			int nextNode = v[i][j].node;
			int weight = v[i][j].weight;

			ArrayFY[i][nextNode] = weight;
		}
	}
}
void MakeGraph() {
	// ��� 1�� ����� ����
	v[1].push_back({ 2, 1 });
	v[1].push_back({ 3, 5 });
	v[1].push_back({ 5, 9 });
	// ��� 2�� ����� ����
	v[2].push_back({ 1, 1 });
	v[2].push_back({ 3, 2 });
	// ��� 3�� ����� ����
	v[3].push_back({ 1, 5 });
	v[3].push_back({ 2, 2 });
	v[3].push_back({ 5, 2 });
	// ��� 4�� ����� ����
	v[4].push_back({ 5, 3 });
	// ��� 5�� ����� ����
	v[5].push_back({ 1, 9 });
	v[5].push_back({ 3, 2 });
	v[5].push_back({ 4, 3 });
}
void FloydWarshall() {

	for (int k = 1; k <= 5; k++) {
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 5; j++) {
				ArrayFY[i][j] = min(ArrayFY[i][j], ArrayFY[i][k] + ArrayFY[k][j]);
			}
		}
	}
}
void show() {
	for (int i = 1; i <= 5; i++) {
		for (int j = 1; j <= 5; j++) {
			if (ArrayFY[i][j] == INF) printf("%5s ", "INF");
			else printf("%5d ", ArrayFY[i][j]);
		}
		printf("\n");
	}
}
int main(void) {
	MakeGraph();
	InitFY();
	FloydWarshall();
	show();
}