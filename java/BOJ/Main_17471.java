package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//게리맨더링
public class Main_17471 {
	private static int N;
	private static int sum;
	private static int count[], map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		count=new int[N+1];
		for(int i=1; i<=N; i++) {
			//인구수 
			count[i]=Integer.parseInt(st.nextToken());
			sum+=count[i];
		}
		//연결관계
		map=new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			for(int j=0; j<n; j++) {
				map[i][Integer.parseInt(st.nextToken())]=1;
			}
		}
		visit=new int[N+1];
		visit[1]=0;
		subset(2,0); 
		answer=(answer==Integer.MAX_VALUE)?-1:answer;
		System.out.println(answer);
	}
	//조합 고르기, 선택/비선택
	private static int visit[];
	private static int answer=Integer.MAX_VALUE;
	private static void subset(int idx, int cnt) { //비선택 갯수가 cnt인거
		if(idx==N+1) { //다 골랐으면!
			if(cnt==0) return; //하나도 안뽑는건 xx
			int x=isPossible(cnt, 1);
			int y=isPossible(N-cnt,0);
			if(x!=-1 && y!=-1)
				answer=Math.min(Math.abs(x-y), answer);
			return;
		}
		
		visit[idx]=1;
		subset(idx+1, cnt+1);
		visit[idx]=0;
	
		subset(idx+1, cnt);
	}

	private static int isPossible(int cnt, int s) {
		boolean check[]=new boolean[N+1];
		int totalNum=0;
		Queue<Integer> q=new LinkedList<>();
		for(int i=1; i<=N; i++) {
			if(visit[i]==s) {
				q.add(i);
				check[i]=true;
				totalNum+=count[i];
				break;
			}
		}
		int tmp=1;
		while(!q.isEmpty()) {
			int x=q.poll();
			for(int i=1; i<=N; i++) {
				if(visit[i]==s && !check[i] && map[x][i]==1) {
					q.add(i);
					check[i]=true;
					totalNum+=count[i];
					tmp++;
				}
			}
		}
		if(tmp==cnt) {
			return totalNum;
		}else return -1;

	}
}
