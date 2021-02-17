#include <iostream>
#include <algorithm>

using namespace std;

int N, M;
int arr[1001][1001]; 
int visit[1001];
int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };
int x, y;
int cnt;

void dfs(int v)
{
	visit[v] = 1;
	for (int i = 1; i < N + 1; i++)
	{
		if (arr[v][i] == 1&&visit[i]==0)
			dfs(i);
	}
}


int main(void)
{
	cin >> N >> M;
	for (int i = 0; i < M; i++)
	{
		cin >> x >> y;
		arr[x][y] = 1;
		arr[y][x] = 1;
	}

	for (int i = 1; i < N + 1; i++)
	{
		if (visit[i] == 0) {
			dfs(i);
			cnt++;
		}
	
	}
	

	cout << cnt << endl;
	return 0;

}
