package April_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_17780 {
	private static int N, K;
	private static int chessBoard[][]; //체스판의 색깔 상태표시,
	private static ArrayList<Integer> map[][]; //map의 각 칸에 말의 index를ㄹ ArrayList 형식으로놓기
	private static Horse horse[];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		chessBoard=new int[N][N];
		map=new ArrayList[N][N];
		horse=new Horse[K+1]; // 말은 1번부터 K번까지
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				map[i][j]=new ArrayList<>();
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				chessBoard[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		//k개의 말의 정보 입력받기
		for(int k=1; k<=K; k++) {
			st=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken())-1;
			int d=Integer.parseInt(st.nextToken())-1;
			horse[k]=new Horse(r,c,d,0);
			map[r][c].add(k);
		}
		int t=1;
		for(t=1; t<=1001; t++) {
			//t초에 1~K까지의 말이 움직인다.
			if(!moveHorse()) break;
		}
		t=t>=1001?-1:t;
		System.out.println(t);
		
	}	
	private static boolean moveHorse() {
		for(int i=1; i<=K; i++) {
			//그 말을 움직일 수 있는지 여부, 가장 아래에 있는가?
			if(horse[i].idx!=0) {
				continue;
			}
			//움직일 수 있는 상황이면, 방향에 맞게 움직인다
			int d=horse[i].d;
			int r=horse[i].r; int c=horse[i].c;
			int nr=horse[i].r+dr[d]; int nc=horse[i].c+dc[d];
			//범위를 벗어나는경우
			if(!isPossible(nr,nc) || isBlue(nr,nc)) {
				//범위 벗어나거나 파란색이면, d 방향바꿧 nr,nc계산했음
				horse[i].d=d=changeDir(d);
				nr=horse[i].r+dr[d]; nc=horse[i].c+dc[d];
			}
			//여기왔는데, 또 범위벗어나거나 파란색인 경우가 있으면?
			//그 땐 그냥 방향 다시한번바꿔주고, continue
			if(!isPossible(nr,nc) || isBlue(nr,nc)) {
				horse[i].d=d=changeDir(d);
				continue;
			}
			///여기온건 빨강, 아니면 흰
			if(chessBoard[nr][nc]==0) {
				moveToWhite(r,c,nr,nc);
				if(map[nr][nc].size()>=4) return false;
			}
			if(chessBoard[nr][nc]==1) {
				moveToRed(r,c,nr,nc);
				if(map[nr][nc].size()>=4) return false;
			}
		}
		return true;
	}
	private static void moveToRed(int r, int c, int nr, int nc) {
		int size=map[nr][nc].size();
		int cnt=0;
		for(int i=map[r][c].size()-1; i>=0; i--) {
			int node=map[r][c].get(i);
			map[nr][nc].add(node);
			horse[node].r=nr;
			horse[node].c=nc;
			horse[node].idx=size+cnt++;
		}
		map[r][c]=new ArrayList<>(); //자리초기화
	}
	private static void moveToWhite(int r, int c, int nr, int nc) {
		int size=map[nr][nc].size();
		for(int i=0; i<map[r][c].size(); i++) {
			//원래 자리의 처음 부터 끝까지 다음칸으로 옮기기,
			//horse 인덱스 변경해주기
			int node=map[r][c].get(i);
			map[nr][nc].add(node);
			//해당 말 위치 변경
			horse[node].r=nr;
			horse[node].c=nc;
			horse[node].idx=size+i;
		}
		map[r][c]=new ArrayList<>(); //자리초기화
	}
	private static boolean isBlue(int nr, int nc) {
		if(chessBoard[nr][nc]==2) return true;
		return false;
	}
	private static boolean isPossible(int nr, int nc) {
		if(nr<0 || nc<0 || nr>=N || nc>=N) return false;
		return true;
	}
	private static int changeDir(int d) {
		switch(d) {
		case 0:
			return 1;
		case 1:
			return 0;
		case 2:
			return 3;
		case 3:
			return 2;
		}
		return 0;
	}
	private static int dr[]= {0,0,-1,1};
	private static int dc[]= {1,-1,0,0}; //우,좌,상,하
	private static class Horse{
		int r, c; //말의 현재 위치
		int d; //이동방향
		int idx; //얘가 위치한 자리에서 얘의 index
		public Horse(int r, int c, int d, int idx) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.idx = idx;
		}
		
	}
}
