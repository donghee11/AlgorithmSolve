package April_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//배열돌리기
public class Main_17406 {
	private static int N, M, K, answer=Integer.MAX_VALUE;
	private static int map[][];
	private static boolean visit[];
	private static ArrayList<int[]> list;
	private static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		map=new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		list=new ArrayList<>();
		for(int k=1; k<=K; k++) {
			st=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
			list.add(new int[] {r,c,s});
			
		}
		cnt=list.size();
		visit=new boolean[cnt];
		//답 구하기
		dfs(0);
		System.out.println(answer);
	}
	
	private static void dfs(int count) {
		if(count==cnt) {
			answer=Math.min(cal(), answer);
			return;
		}
		
		int C_map[][]=new int[N+1][M+1];
		for(int i=0; i<cnt; i++) {
			if(visit[i]) continue;
			visit[i]=true;
			Copy(C_map,map);
			rotate(list.get(i)[0], list.get(i)[1], list.get(i)[2]);
			dfs(count+1);
			visit[i]=false;
			Copy(map,C_map);
		}
	}
	//맵 상태 보관!!
	private static void Copy(int [][]map, int map2[][]) {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				map[i][j]=map2[i][j];
			}
		}
	}
	private static int cal() {
		int answer=Integer.MAX_VALUE;
		for(int i=1; i<=N; i++) {
			int tmp=0;
			for(int j=1; j<=M; j++)
				tmp+=map[i][j];
			answer=Math.min(answer, tmp);
		}
		return answer;
	}
	private static void rotate(int x, int y, int s) {
		//총 s번 rotate돈다
		for(int cnt=0; cnt<s; cnt++) {
			//시작점
			int r=x-s+cnt; int c=y-s+cnt;
			int end_r=x+s-cnt; int end_c=y+s-cnt;
			
			//첫번째점 tmp에 저장
			int tmp=map[r][c];
			//맨왼쪽 줄 옮기기, 열 고정,
			for(int i=r+1; i<=end_r; i++) {
				map[i-1][c]=map[i][c];
			}
			//맨 밑 줄 옮기기, 행 고정, end_r행
			for(int i=c+1; i<=end_c; i++) {
				map[end_r][i-1]=map[end_r][i];
			}
			//맨 오른쪽 옮기기, 열 고정, end_c열
			for(int i=end_r; i>=r+1; i--) {
				map[i][end_c]=map[i-1][end_c];
			}
			//맨 윗줄 옮기기, 행 r로 고정
			for(int i=end_c; i>=c+2; i--) {
				map[r][i]=map[r][i-1];
			}
			map[r][c+1]=tmp;
			
		}
	}
}
