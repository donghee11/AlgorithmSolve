#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
int N, M;
int arr[51][51];
int visit[51][51];
vector <pair<int, int> > v; //치킨위치 넣어져있는 함수
int answer=100000; //최종답
//치킨거리 계산 함수

void calculate()
{

	int finaldist = 0;

	for(int i=1; i<=N; i++)
		for (int j = 1; j <= N; j++)
		{
			int dist = 1000;
			if (arr[i][j] == 1) //집이면
			{
				for (int k = 0; k < v.size(); k++)
				{
					int r = v[k].first;
					int c = v[k].second;
					cout << "r c 값은 : " << r << " " << c<<endl;
					dist = min(abs(r - i) + abs(c - j),dist); //그 집에서 치킨 거리 구했음
				}
				cout << "dist finaldist " << dist << " " << finaldist << endl;
				finaldist += dist;
			
			}
	
		}
	
	answer = min(answer, finaldist);
}
void pickup(int x, int y, int n, int now) //now는 현재 뽑은갯수
{
	//n개를 pick 한다

	if (now == n) //뽑은 갯수가 n이랑 같으면
	{
		calculate();
		return;
	}
	//뽑은갯수가 n 이하
	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			if (arr[i][j] == 2 && visit[i][j]==0) //치킨집이면서 방문하지않은곳
			{
				
				v.push_back(make_pair(i, j)); //해당 치킨집 벡터에넣기
				visit[i][j] = 1;
				pickup(i, j, n, now + 1);
				visit[i][j] = 0;
				v.pop_back();
				
			}
		}
	}
}
int main(void)
{
	cin >> N >> M;
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++)
			cin >> arr[i][j];

	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			if (arr[i][j] == 2) //치킨집이면
			{
				for (int k = 1; k <= M; k++) 
				{
					//여기서 (i,j)랑 가능한 조합 다 생성ㅎㅏ기 -> visit다시 0으로 바꿀필요 x
					visit[i][j] = 1; //해당 치킨집 방문처리
					v.push_back(make_pair(i, j));
					pickup(i, j, k, 1); //1개, 2개, 3개,,,,,M개 까지 check
					v.clear();
					//visit[i][j] = 0;
				}
			}
		}
	}

	cout << answer << endl;
	return 0;
}
