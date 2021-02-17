#include <iostream>
#include <algorithm>
#include <vector>


using namespace std;
int N;
int x, y, d, g;
int arr[101][101];
vector <int> v;

int result = 0;
void direction(int direction) //d에따른 (x,y)의 좌표 이동
{
	if (direction == 0)
		x += 1;
	else if (direction == 1)
		y-=1;
	else if (direction == 2)
		x-= 1;
	else
		y += 1;
}

void check(int x, int y)
{
	if (arr[x + 1][y] && arr[x][y + 1] && arr[x + 1][y + 1])
		result++;
}

int main(void)
{
	cin >> N;
	while (N--)
	{
		cin >> x >> y; //시작점 (x,y)
		cin >> d; //d 방향 0 right 1 above 2 left 3 below
		cin >> g; //세대
		arr[y][x] = 1; //해당 좌표의 arr을 1로 바꾼다.
		direction(d); //처음받은 방향에 따라 다음 (x,y)좌표를얻음, 0세대
		arr[y][x] = 1;

		v.push_back(d);
		//v에는 d의 모음
		//(x,y)에서 시작하기
		while (g--) //g세대까지 구하기 위해
		{
			int S = v.size();
			for (int i = S - 1; i >= 0; i--)
			{
				int newd = v[i];
				if (newd == 3)
					newd = 0;
				else
					newd += 1;

				direction(newd); //(xy)변화
				v.push_back(newd);
				
				if (x >= 0 && y >= 0 && x <= 100 && y <= 100)
					arr[y][x] = 1; //해당 좌표 방문표시하기
			}
			
			
		}
		v.clear();
	}

	//직사각형 갯수 확인하기
	for (int i = 0; i < 100; i++)
		for (int j = 0; j < 100; j++)
		{
			if (arr[i][j] == 1)
				check(i, j);
		}
	cout << result;
	return 0;
}
