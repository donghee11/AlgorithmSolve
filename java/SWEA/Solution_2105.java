package feb_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2105 {
	private static int N;
	private static int map[][];
	private static boolean desert[];
	private static int answer;
	private static int dx[]= {1,1,-1,-1};
	private static int dy[]= {1,-1,-1,1}; //대각선방향으로 이동,
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			desert=new boolean[101]; //index에 해당하는 디저트를 먹었을 때,
			answer=Integer.MIN_VALUE;
			StringTokenizer st;
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}//end of input
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					desert[map[i][j]]=true;
					recur(i,j,0,0,0,1);
					desert[map[i][j]]=false;
				}
			}
			if(answer==Integer.MIN_VALUE) answer=-1;
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}//end of tc
		
		System.out.println(sb.toString());
	}//end of main
	
	private static void recur(int i, int j, int d, int r, int l, int ans) {
		//d가 0이거나 1이면 갈수 있는 길이면 방향 유지하거나 방향 전환하거나, 재귀호출
		//d가 2이거나 3이면 사각형이 되기 위해서 r과 l 만큼 움직일수 있어야함, 불가능하면 return
		//다시 자기자신으로 돌아오면 max 구하고 return
		if(d==3 && l==1) {
			answer=Math.max(ans, answer); //종료할때 답 구하기,
			return;
		}
		
		if(d==3) {
			int nx=i+dx[d]; int ny=j+dy[d];
			if(nx>=0 && ny>=0 && nx<N && ny<N && !desert[map[nx][ny]]) {
				desert[map[nx][ny]]=true;
				recur(nx,ny,d,r,l-1,ans+1);
				desert[map[nx][ny]]=false;
			}
		}
		if(d==2) {
			int nx=i+dx[d]; int ny=j+dy[d];
			if(nx>=0 && ny>=0 && nx<N && ny<N && !desert[map[nx][ny]]) {
				desert[map[nx][ny]]=true;
				if(r==1) recur(nx,ny,d+1,r-1,l,ans+1); //모든 l까지 도달한 경우,
				else if(r>1) recur(nx,ny,d,r-1,l,ans+1);
				desert[map[nx][ny]]=false;
			}
		}
		
		if(d==1) {
			int nx=i+dx[d]; int ny=j+dy[d];
			if(nx>=0 && ny>=0 && nx<N && ny<N && !desert[map[nx][ny]]) {
				//이동시키기
				desert[map[nx][ny]]=true;
				recur(nx,ny,d,r,l+1,ans+1);
				recur(nx,ny,d+1,r,l+1, ans+1);
				desert[map[nx][ny]]=false;
			}
		}
		
		if(d==0) {
			int nx=i+dx[d]; int ny=j+dy[d];
			if(nx>=0 && ny>=0 && nx<N && ny<N && !desert[map[nx][ny]]) {
				//이동시키기
				desert[map[nx][ny]]=true;
				recur(nx,ny,d,r+1,l,ans+1); //방향유지
				recur(nx,ny,d+1,r+1,l,ans+1); //방향전환
				desert[map[nx][ny]]=false;
			}
		}
		return; 
	}
	
	
}
