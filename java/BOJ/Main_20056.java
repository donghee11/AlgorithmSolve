package March_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_20056 {
	private static class Ball{
		int m,s,d;
		public Ball(int m, int s, int d) {
			this.m = m;
			this.s = s;this.d = d;
		} 
		
	}
	private static int N, M, K; 
	private static int dr[]= {-1,-1,0,1,1,1,0,-1};
	private static int dc[]= {0,1,1,1,0,-1,-1,-1};
	private static ArrayList<Ball> map[][];
	private static int cnt[][];
	private static int cnt2[][];
	private static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		//M개의 갯수만큼 파이어볼 입력받기,
		map=new ArrayList[N+1][N+1];
		for(int i=0; i<N+1; i++)
			for(int j=0; j<N+1; j++)
				map[i][j]=new ArrayList<>();
		
		cnt=new int[N+1][N+1];
		cnt2=new int[N+1][N+1];
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int r,c,m,s,d;
			r=Integer.parseInt(st.nextToken());
			c=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			s=Integer.parseInt(st.nextToken());
			d=Integer.parseInt(st.nextToken());
			map[r][c].add(new Ball(m,s,d));
			cnt[r][c]+=1;
		}
		for(int k=0; k<K; k++) {
			//k번 이동
			moveFireBall();
			
		}
		calculate();
		System.out.println(ans);
	}//end of main
	private static void moveFireBall() {
		cnt2=new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) { //마법사 공이 있는 곳이라면 움직인다.
				if(cnt[i][j]>0) {
					move(i,j,map[i][j]);
				}
			}
		}
		
		//모든 파이어볼을 이동했다면, 
		//cnt2에 있는 값들이 진짜 파이어볼 갯수
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(cnt2[i][j]>1) {
					//2개 이상있는애들은 쪼개줘야함.
					separate(i, j,map[i][j], cnt2[i][j]);
				}
			}
		}
		
		//cnt2를 cnt로 
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++)
				cnt[i][j]=cnt2[i][j];
	}//end of func
	
	private static void calculate() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j].size()>=1) {
					ans+=(map[i][j].get(0).m)*(map[i][j].size());
				}
			}
		}
	}
	
	private static void separate(int r, int c,ArrayList<Ball> ball, int size) {
		//ball에있는 질량 합, 속도 합, 방향 값 계산해야함
		int m=0; int s=0; int d=ball.get(0).d%2; boolean flag=true;
		int ss=size;
		while(size-->0) {
			Ball tmpBall=ball.remove(0);
			m+=tmpBall.m;
			s+=tmpBall.s;
			//d는 모두 짝수거나 모두 홀수거나
			int tmpd=tmpBall.d%2;
			if(flag) {
				if(tmpd!=d) flag=false;
			}
		}
		//여기서 질량의 합이 5보다 작으면 구할필요가 없다
		if(m<5) {
			cnt2[r][c]=0;
			return;
		}
		//계산된 애들은 4개로 나누어진다
		if(flag) { //0,2,4,6으로
			for(int i=0; i<8; i+=2) {
				map[r][c].add(new Ball(m/5, s/ss, i));
			}
			cnt2[r][c]=4;
		}else {
			for(int i=1; i<8; i+=2) {
				map[r][c].add(new Ball(m/5, s/ss, i));
			}
			cnt2[r][c]=4;
		}
	
	}
	private static void move(int r, int c, ArrayList<Ball> ball) {
		//ball에 있는 모든 공들 이동시키기
		while(cnt[r][c]-->0) { //ball에 있는 갯수들만큼만 이동해주기!
			Ball tmpBall=ball.remove(0);
			int nr=r+tmpBall.s*dr[tmpBall.d];
			int nc=c+tmpBall.s*dc[tmpBall.d];
			
			//이어져있으니까 범위가 넘어갈 경우에는 
			if(nr>N) nr%=N;
			if(nr<1) nr=(nr%N)+N;
			if(nc>N) nc%=N;
			if(nc<1) nc=(nc%N)+N;
			
			cnt2[nr][nc]++;
			//map의(nr,nc)위치에 ball을 넣기
			map[nr][nc].add(tmpBall);
		}
	}
	
	
}//end of class
