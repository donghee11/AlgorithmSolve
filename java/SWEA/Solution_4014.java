package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4014 {
	private static int N, X; //행렬크기, 활주로 길이
	private static int map[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			X=Integer.parseInt(st.nextToken());
			int answer=0;
			map=new int[N][N];
			for(int i=0; i<N;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}//end of input
			//모든 행에 대해서 조사하기
			for(int i=0; i<N;i++) {
				if(findRow(i,map)) answer++;
				if(findRow(i,change(map))) answer++;
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}//end of tc
		System.out.println(sb.toString());
	}
	private static int[][] change(int [][]map){
		int C_map[][]=new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				C_map[i][j]=map[j][i];
			}
		}
		return C_map;
	}
	private static boolean findRow(int r, int [][]map) {
		
		//r행에 대해서 모든 열들을 조사한다.
		int tmp=map[r][0]; //첫번째 인자
		int now=-1; //now에는 경사로가 있습니다를 표시
		for(int i=1; i<N; i++) {
			if(Math.abs(tmp-map[r][i])>=2) return false;
			
			if(tmp==map[r][i]) {
				tmp=map[r][i]; continue;
			}
			//낮아지면
			if(tmp-map[r][i]==1) {
				//해당 i에서부터, x개를 세울수 있는지 살피기
				for(int j=0; j<X; j++) {
					if(i+j>=N ||  map[r][i]!=map[r][i+j]) return false;
				}
				//여기까지 왔으면 된다는거
				now=i+X-1; //여기까지 경사로가 있다
				tmp=map[r][i+X-1];
				i+=(X-1);
				
			}else if(tmp-map[r][i]==-1) {
				//높아지는거였으면 
				//i-1,,,,i-x까지 살펴보기
				for(int j=1; j<=X; j++) {
					if(now>=i-j || i-j<0 || map[r][i-j]!=tmp) return false;
				}
				tmp=map[r][i];
			}
		}
		return true;
	}
}
