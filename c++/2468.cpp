#include <iostream>
#include <algorithm>
#include <queue>
#include <cstring>
#include <vector>

using namespace std;
int N;
int M = 0;
int arr[101][101];
int visit[101][101];
int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };

int result = 0;
int finalresult = -1;



void bfs(int height, int n, int m)
{
	queue<pair<int, int>> q;

	q.push(make_pair(n, m));
	visit[n][m] = 1; //방문한 것으로 바꿔주기
	int nx, ny;
	int x, y;

	while (!q.empty()) {

		x = q.front().first;
		y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];

			if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
				if (arr[nx][ny] > height && visit[nx][ny] == 0) //물 높이가 높고 방문하지 않았따면
				{
					visit[nx][ny] = 1; //방문처리 시키고
					q.push(make_pair(nx, ny));
				}
			}
		}
	}

	result++;
}
int main(void)
{
	cin >> N;

	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
		{
			cin >> arr[i][j]; //높이가 적힌 배열을 입력받음 N*N
			M = max(M, arr[i][j]); // M에는 높이의 최댓값이 담겨있다.
		}

	for (int height = 0; height <= M; height++) //높이가 1일 때부터 M 일때 까지를 모두 조사한후, 가장 큰 값이 결과
	{
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
			{
				if (visit[i][j] == 0 && arr[i][j] > height)
					bfs(height, i, j);
			}
		} //최종 result값이 나와!

		finalresult = max(finalresult, result);
		result = 0; //초기화
		memset(visit, 0, sizeof(visit));
	}


	cout << finalresult << endl;
	return 0;
}
