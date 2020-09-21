#include <iostream>
#include <algorithm>

using namespace std;
bool visit[25];
int arr[25][25];
int N;
int result;
int result2;
int finalresult=10000;
void comb(int idx, int cnt)
{
	if (cnt == N / 2)
	{
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (visit[i] == true && visit[j] == true && i != j)
					result += arr[i][j];
				if (visit[i] == false && visit[j] == false && i != j)
					result2 += arr[i][j];
				
			}
		}
		finalresult = min(finalresult, abs(result - result2));
		result = 0; result2 = 0;
		return;
	}
	for (int i = idx; i < N; i++)
	{
		if (visit[i])
			continue;
		else
		{
			visit[i] = true;
			comb(i+1, cnt + 1); 
			visit[i] = false; //조합부분
		}
	}
}
int main(void)
{
	cin >> N;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			cin >> arr[i][j];
	comb(0, 0);
	cout << finalresult;

}
