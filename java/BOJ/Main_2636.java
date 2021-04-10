package April_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//치즈
//공기와 접촉된 칸, 한시간이 지나면 녹아 없어진다. 
public class Main_2636 {
	private static int N, M;
	private static boolean visit[][];
	private static int map[][];
	private static int time, cnt;
	private static Queue<int[]> q;
	private static ArrayList<int[]> cheeze; 
	private static int cheezeCnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		visit=new boolean[N][M];
		q=new LinkedList<>();
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					cheezeCnt++;
				}
			}
		}//end of input 
		//처음 치즈는 외벽의 1과 만나고 있는 2를 찾아서 cheeze 리스트에 넣어서 보관,
		//다음에는 cheeze리스트에 있는 것들중에서 2를 찾아서 보관,
		//그다음에도 쭉쭉쭉
		//리스트가 비면 종료한다.
		while(cheezeCnt>0) {
			first_bfs();
		}
		System.out.println(time+" "+cnt);
		
	}
	private static int dr[]= {1,-1,0,0};
	private static int dc[]= {0,0,1,-1};
	private static void first_bfs() {
		//처음에 1로부터 , 찾을때, cheeze 배열에 넣기
		q.add(new int[] {0,0});
		visit=new boolean[N][M]; 
		visit[0][0]=true;
		cheeze=new ArrayList<>();
		while(!q.isEmpty()) {
			int[] p=q.poll();
			int r=p[0]; int c=p[1];
			for(int i=0; i<4; i++) {
				int nr=r+dr[i]; int nc=c+dc[i];
				if(nr>=0 && nc>=0 && nr<N && nc<M && !visit[nr][nc]) {
					if(map[nr][nc]==0) {
						visit[nr][nc]=true; //빈칸이면 방문 처리만 하기
						q.add(new int[]{nr,nc});
					}
					else { //빈칸이 아니라면, cheeze니까 방문처리하고cheeze 배열에 넣기
						visit[nr][nc]=true;
						cheeze.add(new int[] {nr,nc}); //q에 넣지는 않는다.
					}
				}
			}
		}
		cnt=cheeze.size(); //이만큼의 치즈를 없앤것, 
		cheezeCnt-=cnt;
		time++;
		//cheeze들 0처리하기
		for(int i=0; i<cheeze.size(); i++)
			map[cheeze.get(i)[0]][cheeze.get(i)[1]]=0;
	}
	private static void bfs() {
		//일반 bfs시작, cheeze에 담겨있는 애들을 queue에 넣고 bfs를 돌린다.
		visit=new boolean[N][M];
		q=new LinkedList<>();
		for(int i=0; i<cheeze.size(); i++) {
			q.add(new int[] {cheeze.get(i)[0], cheeze.get(i)[1]});
			visit[cheeze.get(i)[0]][cheeze.get(i)[1]]=true;
		}
		cheeze=new ArrayList<>();
		while(!q.isEmpty()) {
			int []p=q.poll();
			int r=p[0]; int c=p[1];
			for(int i=0; i<4; i++) {
				int nr=r+dr[i]; int nc=c+dc[i];
				if(nr>=0 && nc>=0 && nr<N && nc<M && !visit[nr][nc]) {
					if(map[nr][nc]==0) visit[nr][nc]=true;
					else {
						visit[nr][nc]=true;
						cheeze.add(new int[] {nr,nc});
					}
				}
			}
		}
		cnt=cheeze.size();
		
		cheezeCnt-=cnt;
		time++;
	}
}
