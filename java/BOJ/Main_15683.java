package March_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/** 주의 :값을 입력받음과 동시에, CCTV5를 설치해주었을 때 빈 값들 처리못함.*/
public class Main_15683 {
	private static class Pair{
		int x,y;
		public Pair(int x, int y) {
			this.x=x; this.y=y;
		}
	}
	private static int N, M;
	private static int answer=Integer.MAX_VALUE;
	private static int map[][], map2[][];
	private static int dr[]= {0,-1,0,1}; //좌,상,우,하
	private static int dc[]= {-1,0,1,0};
	private static ArrayList<Pair> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		map2=new int[N][M];
		list=new ArrayList<>();
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				map2[i][j]=map[i][j];
				if(map[i][j]>=1 && map[i][j]<=4) list.add(new Pair(i,j)); //나머지 CCTV들
			}
		}//end of input
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				if(map2[i][j]==5) CCTV5(i,j);
		DFS(0);
		System.out.println(answer);
		
	}//end of main
	
	private static void DFS(int idx) {
		if(idx==list.size()) { //모든 CCTV 경우 살펴봤을때
			int sum=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map2[i][j]==0) sum++;
				}
			}
			answer=Math.min(sum, answer);
			return;
		}
		Pair p=list.get(idx);
		int r=p.x; int c=p.y;	
		if(map2[r][c]==1) CCTV1(r,c,idx);
		else if(map2[r][c]==2) CCTV2(r,c,idx);
		else if(map2[r][c]==3) CCTV3(r,c,idx);
		else if(map2[r][c]==4) CCTV4(r,c,idx);
	}
	
	
	private static void CCTV4(int r, int c, int idx) {
		int[][]map3=new int[N][M];
		for(int i=0; i<4; i++) {
			//map의 상태를 저장해놓고 map2를 변화시켜놓고, 다시 돌려놓기
			for(int n=0; n<N; n++)
				for(int m=0; m<M; m++)
					map3[n][m]=map2[n][m];
			int nr=r+dr[i]; int nc=c+dc[i];
			while(true) {
				if(nr<0 || nc<0 || nr>=N || nc>=M || map2[nr][nc]==6) break;
				if(map2[nr][nc]==0) map2[nr][nc]=7;
				nr+=dr[i]; nc+=dc[i];
			}
			int j=(i+1<4)?i+1:0;
			nr=r+dr[j]; nc=c+dc[j];
			while(true) {
				if(nr<0 || nc<0 || nr>=N || nc>=M || map2[nr][nc]==6) break;
				if(map2[nr][nc]==0) map2[nr][nc]=7;
				nr+=dr[j]; nc+=dc[j];
			}
			int t=(j+1<4)?j+1:0;
			nr=r+dr[t]; nc=c+dc[t];
			while(true) {
				if(nr<0 || nc<0 || nr>=N || nc>=M || map2[nr][nc]==6) break;
				if(map2[nr][nc]==0) map2[nr][nc]=7;
				nr+=dr[t]; nc+=dc[t];
			}
			//CCTV설치 끝났으면 이 map2로 계속 변화시키기
			DFS(idx+1);
			//map2를 이전의 상태로 돌려놓기
			for(int n=0; n<N; n++)
				for(int m=0; m<M; m++)
					map2[n][m]=map3[n][m];
		}
		
	}

	private static void CCTV3(int r, int c, int idx) {
		int[][]map3=new int[N][M];
		//입력받은 map2를 변화시키자.
		for(int i=0; i<4; i++) {
			//map의 상태를 저장해놓고 map2를 변화시켜놓고, 다시 돌려놓기
			for(int n=0; n<N; n++)
				for(int m=0; m<M; m++)
					map3[n][m]=map2[n][m];
			int nr=r+dr[i]; int nc=c+dc[i];
			while(true) {
				if(nr<0 || nc<0 || nr>=N || nc>=M || map2[nr][nc]==6) break;
				if(map2[nr][nc]==0) map2[nr][nc]=7;
				nr+=dr[i]; nc+=dc[i];
			}
			int j=(i+1<4)?i+1:0;
			nr=r+dr[j]; nc=c+dc[j];
			while(true) {
				if(nr<0 || nc<0 || nr>=N || nc>=M || map2[nr][nc]==6) break;
				if(map2[nr][nc]==0) map2[nr][nc]=7;
				nr+=dr[j]; nc+=dc[j];
			}
			//CCTV설치 끝났으면 이 map2로 계속 변화시키기
			DFS(idx+1);
			//map2를 이전의 상태로 돌려놓기
			for(int n=0; n<N; n++)
				for(int m=0; m<M; m++)
					map2[n][m]=map3[n][m];
		}
	}
	
	private static void CCTV2(int r, int c, int idx) {
		int[][]map3=new int[N][M];
		for(int i=0; i<2; i++) {
			//map의 상태를 저장해놓고 map2를 변화시켜놓고, 다시 돌려놓기
			for(int n=0; n<N; n++)
				for(int m=0; m<M; m++)
					map3[n][m]=map2[n][m];
			int nr=r+dr[i]; int nc=c+dc[i];
			while(true) {
				if(nr<0 || nc<0 || nr>=N || nc>=M || map2[nr][nc]==6) break;
				if(map2[nr][nc]==0) map2[nr][nc]=7;
				nr+=dr[i]; nc+=dc[i];
			}
			
			nr=r+dr[i+2]; nc=c+dc[i+2];
			while(true) {
				if(nr<0 || nc<0 || nr>=N || nc>=M || map2[nr][nc]==6) break;
				if(map2[nr][nc]==0) map2[nr][nc]=7;
				nr+=dr[i+2]; nc+=dc[i+2];
			}
			
			//CCTV설치 끝났으면 이 map2로 계속 변화시키기
			DFS(idx+1);
			//map2를 이전의 상태로 돌려놓기
			for(int n=0; n<N; n++)
				for(int m=0; m<M; m++)
					map2[n][m]=map3[n][m];
		}
	}

	private static void CCTV1(int r, int c,int idx) {
		int[][]map3=new int[N][M];
		//입력받은 map2를 변화시키자.
		for(int i=0; i<4; i++) {
			//map의 상태를 저장해놓고 map2를 변화시켜놓고, 다시 돌려놓기
			for(int n=0; n<N; n++)
				for(int m=0; m<M; m++)
					map3[n][m]=map2[n][m];
			int nr=r+dr[i]; int nc=c+dc[i];
			while(true) {
				if(nr<0 || nc<0 || nr>=N || nc>=M || map2[nr][nc]==6) break;
				if(map2[nr][nc]==0) map2[nr][nc]=7;
				nr+=dr[i]; nc+=dc[i];
			}
			//CCTV설치 끝났으면 이 map2로 계속 변화시키기
			DFS(idx+1);
			//map2를 이전의 상태로 돌려놓기
			for(int n=0; n<N; n++)
				for(int m=0; m<M; m++)
					map2[n][m]=map3[n][m];
		}
	}
	
	
	
	
	/****CCTV 5 설치 ****/
	private static void CCTV5(int r, int c) {
		//(r,c)를 중심으로 빈공간이면 7로 바꾸기
		for(int i=0; i<4; i++) {
			int nr=r+dr[i]; int nc=c+dc[i];
			while(true) {
				if(nr<0 || nc<0 || nr>=N || nc>=M || map2[nr][nc]==6) break;
				if(map2[nr][nc]==0) map2[nr][nc]=7; //빈칸이면,
				nr+=dr[i]; nc+=dc[i];
			}
		}
	}
}
