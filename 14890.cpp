#include <iostream>
#include <algorithm>

using namespace std;

int N, L;
int arr[100][100];
int result;
int visit[100][100];
int arr2[100][100];
void lefttoright(int n) 
{
	int i = 0;
	for (i = 0; i < N - 1; i++)
	{
		if (arr[n][i] == arr[n][i + 1]) //같으면
		{

			continue;
		}
		else if (arr[n][i] - arr[n][i + 1] == 1) //왼쪽이 더 크면
		{
			//확인하기 전에 오른쪽에 L개가 있는지 확인하고 없으면 나가기, 볼필요 x
			if (i + L > N - 1)
				break;
			visit[n][i + 1] = 1; //다음꺼에 벽 세운거 표시 (i+1번째)
			//i+1부터 L개를 확인
			for (int j = i + 2; j <= i + L; j++)
			{
				
				if (arr[n][i+1] == arr[n][j]) //i+1과 (L-1)개가 똑같은지 확인하기
				{
					visit[n][j] = 1; //방문표시
					continue;

				}
				else
				{
					i = N;
					break; //반복문 나가고 for문 못돌게
				}
			}
			if(i!=N)
				i = i + L - 1;

			
		}
		else if (arr[n][i] - arr[n][i + 1] == -1) //오른쪽이 더 크면
		{
			//i로부터 왼쪽으로 (L-1)개 더 있어야함
			if (i - L + 1 < 0 || visit[n][i] == 1)
			{
				break;
			}
				
			for (int j = i - 1; j > i - L; j--)
			{
				
				if ((arr[n][i] == arr[n][j]) && visit[n][j]==0)
				{
						continue;
				}
				else
				{
					i = N;
					break;
				}
			}
		}
		else {
			i = N;
			break; //이 외에는 불가능한것
		}
	}
	//n번째 줄 검사하기

	//끝까지 갔는지 확인방법?
	if (i == N - 1)
		result++;
}

void change()
{
	for(int i=0; i<N; i++)
		for (int j = 0; j < N; j++)
		{
			arr2[i][j] = arr[j][i];
		}
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			arr[i][j] = arr2[i][j];

	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			visit[i][j] = 0;
}
int main(void)
{
	cin >> N >> L;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			cin >> arr[i][j];
	for (int i = 0; i < N; i++)
		lefttoright(i);
	

	change();
	
	for (int i = 0; i < N; i++)
		lefttoright(i);

	cout << result;

	return 0;
}
