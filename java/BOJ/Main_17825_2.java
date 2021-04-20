package April_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//주사위 윷놀이, 맵 그리기가 핵심..
//갈수있는 4개줄의 맵을 배열로 그리기

public class Main_17825 {
	//윷놀이판, 자기자신 다음에 어디로갈지,
	private static int map[]= {
			1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,
			21,23,24,25,26,27,20,29,30,25,32,25
		////21,22,23,24,25,26,27,28,29,30,31,32
	};
	
	//방향 전환
	private static int turn[];
	
	//점수
	private static int score[]= {
			0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,
			0,13,16,19,25,30,35,28,27,26,22,24
	};
	
	//말 있는지 확인
	private static boolean visit[]; 
	private static int dice[]; //주사위
	private static int answer;
	private static int horse[];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		dice=new int[10];
		for(int i=0; i<10; i++) {
			dice[i]=Integer.parseInt(st.nextToken());
		}
		visit=new boolean[33];
		horse=new int[4];
		//아직 시작안했을 때 index -1
		turn=new int[33];
		turn[5]=22; turn[10]=31; turn[15]=28;
		dfs(0,0);
		System.out.println(answer);
	}
	private static void dfs(int cnt, int sum) {
		if(cnt==10) {
			answer=Math.max(answer, sum);
			return;
		}
		//4방향을 조사
		for(int i=0; i<4; i++) {
			
			//현재 말의 위치
			int num=dice[cnt];
			int nowidx=horse[i];
			int dest=nowidx; //목표 지점
			if(nowidx==21) continue; //이미 도착지점에 있는애라면,
			
			//만약 현재 위치가 방향 전환해야하는 점이라면!
			if(turn[nowidx]>0) {
				//방향전환을 하고,
				dest=turn[nowidx];
				num--;
			}
			//해당 점에서 다시 num만큼 이동하기
			while(num-->0) {
				dest=map[dest]; //다음점을 나타내므로, 
			}
			if(visit[dest]) continue; //이미 방문한 점이면,
			
			if(dest==21) {
				horse[i]=dest;
				visit[nowidx]=false;
				dfs(cnt+1, sum);
				horse[i]=nowidx;
				visit[nowidx]=true;
			}else {
			//해당 점을 방문하자!
			visit[nowidx]=false;
			visit[dest]=true;
			horse[i]=dest;
			dfs(cnt+1, score[dest]+sum);
			horse[i]=nowidx;
			visit[dest]=false;
			visit[nowidx]=true;
			}
		}
	}
}
