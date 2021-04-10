package April_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_17136 {
	private static int map[][];
	private static int totalCnt;
	private static ArrayList<int[]> list;
	private static int answer=Integer.MAX_VALUE;
//	private static int[][] C_map;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map=new int[10][10];
		list=new ArrayList<>();
		for(int i=0; i<10; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<10; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					list.add(new int[] {i,j});
					totalCnt++;
				}
			}
		}
		dfs(0,0,0,0,0,0,0);
		if(answer==Integer.MAX_VALUE) answer=-1;
		System.out.println(answer);
	}
	private static void dfs(int k, int cnt, int a, int b, int c, int d, int e) {
		//종료조건, 갯수를 넘어갈 때는 그만
		if(a>5 || b>5 || c>5 || d>5 || e>5) return; //갯수 초과
		if(cnt==totalCnt) {
			//모든 색종이를 다 사용했으면
			answer=Math.min(answer, a+b+c+d+e);
			return;
		}
		
		//가지치기
		if(answer<a+b+c+d+e) return; //이미 작으면
//		for(int k=0; k<list.size(); k++) {
			int i=list.get(k)[0]; int j=list.get(k)[1];
			boolean flag=true;
			if(map[i][j]==1) { //색종이가 필요한 지점이면,
				if(check(i,j,1)) { //해당 점에서 1*1 가능하면
					make(i,j,1,0);
					dfs(k+1,cnt+1, a+1, b,c,d,e);
					make(i,j,1,1);
				}else flag=false;
				
				if(check(i,j,2) && flag) {
					make(i,j,2,0);
					dfs(k+1, cnt+4, a, b+1,c,d,e);
					make(i,j,2,1);
				}else flag=false;
				
				if(check(i,j,3) && flag) {
					make(i,j,3,0);
					dfs(k+1,cnt+9, a, b,c+1,d,e);
					make(i,j,3,1);
				}else flag=false;
				
				 if(check(i,j,4) && flag) {
					make(i,j,4,0);
					dfs(k+1,cnt+16, a, b,c,d+1,e);
					make(i,j,4,1);
				}else flag=false;
				 
				 if(check(i,j,5) && flag) {
					make(i,j,5,0);
					dfs(k+1,cnt+25, a, b,c,d,e+1);
					make(i,j,5,1);
				}
			}else {
				//그 점이 이미 0으로 바뀌었으면
				dfs(k+1, cnt,a,b,c,d,e);
			}
//		}
		
	}
	private static void make(int r, int c, int d, int s) {
		//(r,c)에서 길이 d를 상태 s로 바꾸기
		for(int i=0; i<d; i++) {
			for(int j=0; j<d; j++) {
				map[r+i][c+j]=s;
			}
		}
	}
	private static boolean check(int r, int c,int d) {
		for(int i=0; i<d; i++) {
			for(int j=0; j<d; j++) {
				if(r+i<0 || c+j<0 || r+i>=10 || c+j>=10) return false; //범위벗어날때
				if(map[r+i][c+j]==0) return false;
			}
		}
		return true;
	}
}
