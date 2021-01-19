#include <iostream>
#include <algorithm>

using namespace std;

//////백준 14501 삼성 SW 기출문제
//////퇴사


int N;
int arr[16][2];
int result;
bool visit[16];

//DFS 로 접근 
void DFS(int t, int p)
{
	//종료조건
	//날짜가 N을 넘어가면 종료하기!
	if (t >= N + 1)
	{
		result = max(result, p);
		return;
	}
	//해당날 상담을 하고, 걸리는 총 일수만큼
	if (t + arr[t][0] <= N + 1)
		DFS(t + arr[t][0], p + arr[t][1]);
	//해당날에 상담을 하지 않고 다음날로 넘어가기
	if (t + 1 <= N + 1)
		DFS(t + 1, p);

}
int main()
{
	cin >> N;

	for (int i = 1; i <= N; i++)
	{
		//시간, 금액 순서대로 입력받기
		cin >> arr[i][0] >> arr[i][1];
	}

	DFS(1, 0);
	cout << result;
	return 0;
}

