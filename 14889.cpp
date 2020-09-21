#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>
#include <string.h>

using namespace std;

int N;
int arr[22][22];
int result;
int choice[30];
int temp[30];

int visit[30];
int result2;
int finalresult = 100000;
void sum()
{
	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			if (visit[i] == 1 && visit[j] == 1 && i!=j)
				result += arr[i][j];
			if (visit[i] == 0 && visit[j] == 0 & i != j)
				result2 += arr[i][j];
		}
	}
	finalresult = min(finalresult, abs(result - result2));
	result = 0;
	result2 = 0;

}

void comb(int num, int idx)
{
	

	if (idx > N / 2) //구하려는 갯수 초과하면
	{
		for (int i = 1; i <= N / 2; i++)
		{
			visit[choice[i]] = 1; //선택된 인덱스에 해당하는 visit 에 1, 없으면 0 
		}
		/*for (int i = 1; i <=N; i++)
			cout << visit[i];
		cout << endl;*/
		sum();
		memset(visit, 0, sizeof(visit));
		return;
	}
	if (num > N)
		return;
	//선택한 원소를 선택 배열에 저장
	choice[idx] = temp[num];
	comb(num + 1, idx + 1);
	comb(num + 1, idx);
	
}


int main(void)
{
	cin >> N;
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++) {
			cin >> arr[i][j];
		}

	for (int i = 1; i <= N; i++)
		temp[i] = i; //temp i번째에 i 넣기
	comb(1, 1);
	cout << finalresult;
	return 0;
}
