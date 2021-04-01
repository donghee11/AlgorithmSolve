package April_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_17837 {
	private static int N, K;
	private static ArrayList<Integer> map[][];
	private static int chessboard[][];
	private static Chess chess[];
	private static int answer=1001;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		map=new ArrayList[N][N];
		chessboard=new int[N][N];
		chess=new Chess[K];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				chessboard[i][j]=Integer.parseInt(st.nextToken());
				map[i][j]=new ArrayList<>(); //초기화
			}
		}
		for(int i=0; i<K; i++) {
			st=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken())-1;
			int dir=Integer.parseInt(st.nextToken())-1;
			chess[i]=new Chess(r,c,dir); //chess들 각각 넣고, map에도 list형식으로 넣기
			map[r][c].add(i);
		}//end of input
		//순서대로 이동 시작! 0번부터 k-1까지
		simulation();
		System.out.println(answer);
	}
	private static int dr[]= {0,0,-1,1};
	private static int dc[]= {1,-1,0,0};
	private static void simulation() {
		for(int t=1; t<=1000; t++) {
			moveChess(t);
			if(answer<=1000) return;
		}
		if(answer==1001) answer=-1;
	}
	private static void moveChess(int time) {
		//1번의 이동을 구현, 0번말부터 k-1번 말까지 순서대로 이동
		for(int i=0; i<K; i++) {
			//i번 말일 때 i번 말의 위치에서 이동
			Chess now=chess[i];
			int r=now.r; int c=now.c; int dir=now.dir;
			//이동할 판의 위치
			int nr=r+dr[dir]; int nc=c+dc[dir];
			//범위를 벗어나는 경우
			if(nr<0 || nc<0 || nr>=N || nc>=N) {
				//방향을 전환한다. 
				dir=changeDirection(dir);
				chess[i].dir=dir;
				nr=r+dr[dir]; nc=c+dc[dir]; //새방향 (nr,nc)
				if(chessboard[nr][nc]==2) {
					chess[i].dir=dir; continue;
				}
			}
			if(chessboard[nr][nc]==2) { //파란색
				dir=changeDirection(dir);
				chess[i].dir=dir; 
				nr=r+dr[dir]; nc=c+dc[dir];
				if(nr<0 || nc<0 || nr>=N || nc>=N) {
					continue;
				}
			}
			//그렇지 않으면 (nr,nc) 범위를 벗어나지 않는다
			if(chessboard[nr][nc]==0) {//흰색
				//(r,c)에서 자기부터 위에꺼까지 그다음에 이어붙히기
				//붙여야할 위치 찾기
				int idx=0;
				for(int j=0; j<map[r][c].size(); j++) {
					if(map[r][c].get(j)==i) { //찾으려는 i 인덱스찾으면!
						idx=j; break;
					}
				}
				//붙히자
				for(int j=idx; j<map[r][c].size();j++) {
					int chessidx=map[r][c].get(j);
					chess[chessidx].r=nr; chess[chessidx].c=nc;
					map[nr][nc].add(chessidx); //자기 뒤에있는것들 싹 찾고 기존자리에서는 제거하자
				}
				int size=map[r][c].size();
				for(int j=size-1; j>=idx; j--)
					map[r][c].remove(j);
				if(map[nr][nc].size()>=4) answer=time;
			}else if(chessboard[nr][nc]==1) {//빨간색
				//얘도 똑같이 위치를 찾고
				int idx=0;
				for(int j=0; j<map[r][c].size(); j++) {
					if(map[r][c].get(j)==i) { //찾으려는 i 인덱스찾으면!
						idx=j; break;
					}
				}
				for(int j=map[r][c].size()-1; j>=idx;j--) {
					int chessidx=map[r][c].get(j);
					chess[chessidx].r=nr; chess[chessidx].c=nc;
					map[nr][nc].add(chessidx); //자기 뒤에있는것들 싹 찾고 기존자리에서는 제거하자
				}
				if(map[nr][nc].size()>=4) answer=time;
				int size=map[r][c].size();
				for(int j=size-1; j>=idx; j--)
					map[r][c].remove(j);
				
			}else {
				chess[i].dir=dir;
			}
		}
	}
	private static int changeDirection(int dir) {//방향 전환하는 함수
		switch(dir) {
		case 0:
			return 1;
		case 1:
			return 0;
		case 2:
			return 3;
		case 3:
			return 2;
		default:
			return 0;
		}
	}
	private static class Chess{ //체스 각각의 위치와 방향을 나타낼 클래스
		int r, c, dir;
		public Chess(int r, int c, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
		
	}

}
