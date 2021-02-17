#include <iostream>
#include <queue>
#include <cstring>

using namespace std;

int N, l; //N은 testcase갯수, l은 체스판 한변길이
int arr[301][301];
int a, b, c, d;
int dx[8] = { 1,1,2,2,-1,-1,-2,-2 };
int dy[8] = { 2,-2,1,-1,2,-2,1,-1 }; //이동할 수 있는 모든 경우
int result = 0;


void bfs() //
{
	queue<pair<int, int>> q;

	q.push(make_pair(a, b));
	int x, y;
	int nx, ny;
	while (!q.empty())
	{
		x = q.front().first;
		y = q.front().second;
		q.pop(); //pop을 해주지않아서 계속 오류가 발생했다. 
             //pop을 해주지 않으면 queue가 계속해서 찬 상태, 결과 도출 x
		if (x == c && y == d)
		{
			cout << arr[x][y] <<endl;
			break;
		}

		for (int i = 0; i < 8; i++)
		{
			nx = x + dx[i];
			ny = y + dy[i];

			if (nx >= 0 && ny >= 0 && nx < l && ny < l)
			{
				if (arr[nx][ny] == 0)
				{
					arr[nx][ny] = arr[x][y] + 1;
					q.push(make_pair(nx, ny));
				}
			}

		}
	}
}
int main(void)
{
	cin >> N;

	for (int i = 0; i < N; i++)
	{
		cin >> l;
		cin >> a >> b >> c >> d;

		bfs();
		memset(arr, 0, sizeof(arr));

	}
	return 0;


}
