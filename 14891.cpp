#include <iostream>
#include <algorithm>
#include <cstdio>
#include <queue>


using namespace std;

int arr[5][8];
int K;
int x, y;
queue <pair <int, int> > q;
void checkright(int n) //오른쪽을 검사
{
	int a = y;
	while (n<4)
	{
		if (arr[n][2] == arr[n + 1][6])
			break; //같으면 볼 필요가 없다
		else {
			a = -a; //부호반전
			q.push(make_pair(n + 1, a));
			n++;
		}
	}

}
void checkleft(int n) //왼쪽을 검사
{
	int a = y;
	while (n>1)
	{
		if (arr[n][6] == arr[n - 1][2])
			break; //같으면 볼 필요가 없다
		else {
			a = -a; //부호반전
			q.push(make_pair(n -1, a));
			n--;
		}
	}

}
void change()
{
	while (!q.empty())
	{
		int nx = q.front().first;
		int ny = q.front().second;
		q.pop();
		//cout << nx << " " << ny << endl;
		if (ny == 1) //시계방향이면
		{
			int temp = arr[nx][7];
			for (int i = 7; i > 0; i--)
			{

				arr[nx][i] = arr[nx][i - 1];

			}
			arr[nx][0] = temp;
		}
		else
		{
			int temp = arr[nx][0];
			for (int i = 0; i <7; i++)
			{
				arr[nx][i] = arr[nx][i + 1];
			}
			arr[nx][7] = temp;
		}
	}

}
int main(void)
{
	
	for (int i = 1; i < 5; i++)
		for (int j = 0; j < 8; j++)
			scanf("%1d", &arr[i][j]);
	cin >> K;
	while (K--)
	{
		cin >> x >> y;
		q.push(make_pair(x, y));
		checkright(x);
		checkleft(x);
		
		change();
		/*for (int i = 1; i < 5; i++)
		{
			for (int j = 0; j < 8; j++)
				cout << arr[i][j];
			cout << endl;
		}
		*/
	}

	int result = 0;
	if (arr[1][0] == 1)
		result += 1;
	if (arr[2][0] == 1)
		result += 2;
	if (arr[3][0] == 1)
		result += 4;
	if (arr[4][0] == 1)
		result += 8;
	cout << result;
	return 0;
}
