#include <algorithm>
#include <cstdio>
using namespace std;

int arr[5] = { 4, 5, 3, 2, 1 };

int main(void) {

	do {
		for (int i = 0; i < 5; i++) {
			printf("%d ", arr[i]);
		}
		printf("\n");
	} while (next_permutation(arr, arr + 5));


}