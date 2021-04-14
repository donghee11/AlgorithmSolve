package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//드래곤커브
public class Main_15685 {
	private static int N;
	private static ArrayList<Curb> list;
	private static boolean visit[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st;
		visit=new boolean[101][101];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			int start_x=Integer.parseInt(st.nextToken());
			int start_y=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			int g=Integer.parseInt(st.nextToken()); //세대
			list=new ArrayList<>();
			list.add(new Curb(start_x+dx[d], start_y+dy[d],d));
			visit[start_x][start_y]=true;
			visit[start_x+dx[d]][start_y+dy[d]]=true;
			//g세대만큼 돌리자
			for(int k=1; k<=g; k++) {
				findCurb(k);
			}
			
			
		}
		System.out.println(check());
	}
	private static int dx[]= {1,0,-1,0};
	private static int dy[]= {0,-1,0,1};
	private static int check() {
		int answer=0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(visit[i][j] && visit[i][j+1] && visit[i+1][j]&& visit[i+1][j+1]) {
					answer++;
				}
					
			}
		}
		return answer;
	}
	private static void findCurb(int g) {
		//g세대의 커브 찾기
		//list의 끝부터 돌면서
		int size=list.size();
		int x=list.get(size-1).end_x; int y=list.get(size-1).end_y;
		for(int i=size-1; i>=0; i--) {
			Curb c=list.get(i);
			int d=change(c.d);
			x+=dx[d]; y+=dy[d];
			visit[x][y]=true;
			list.add(new Curb(x,y,d));
		}
	}
	private static int change(int dir) {
		dir=(dir<3)?(dir+1):0;
		return dir;
	}
	private static class Curb{
		//커브에 필요한 정보들 담을 클래스
		int d, end_x, end_y;
		public Curb(int end_x, int end_y, int d) {
			super();
			this.d = d;
			this.end_x = end_x;
			this.end_y = end_y;
		}
		
	}
}
