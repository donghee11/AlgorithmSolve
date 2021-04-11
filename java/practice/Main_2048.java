package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12100 {
	private static int answer=2;
	private static int N, map[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st;
		map=new int[N][N];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		dfs(0);
		System.out.println(answer);
	}
	//쵣 5번 이동시켜서 얻을 수 있는 가장 큰 블록
	//이동할 수록 블록이 커지면 커졌지 작아지진 않으니까
	private static void dfs(int cnt) {
		if(cnt==5) {
			//가장 큰 블록 찾기
			answer=Math.max(answer, find());
			return; 
		}
		
		moveLeft(cnt);
		moveRight(cnt);
		moveUp(cnt);
		moveDown(cnt);
		
	}
	private static int find() {
		int max=0;
		for(int i=0; i<N;i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]!=0) {
					max=Math.max(max, map[i][j]);
				}
			}
		}
		return max;
	}
	private static void copy(int [][]C_map, int[][]map) {
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				C_map[i][j]=map[i][j];
	}
	//다 왼쪽으로 밀기
	private static void moveLeft(int num) {
		//0행부터 N-1행까지,
		int [][]C_map=new int[N][N];
		copy(C_map,map);
		int idx=0; int cnt=0; //이전값 ? 기억해둘 값?
		for(int i=0; i<N; i++) {
			//모든 열을 관찰한다.
			idx=0; cnt=0;
			for(int j=0; j<N; j++) {
				if(map[i][j]!=0) {
					//0이 아니면,
					if(cnt==map[i][j]) { //전꺼랑 같으면 idx-1에!
						map[i][j]=0; //자기자리는 0으로
						map[i][idx-1]*=2;
						cnt=0; //cnt는 0으로 돌려놔서 아무거랑 못같게
					}else { //전꺼랑 다르면 , idx에 넣고 idx++
						int tmp=map[i][j];
						map[i][j]=0;
						map[i][idx]=tmp;
						cnt=tmp;
						idx++;
					}
				}
			}
		}
		dfs(num+1);
		copy(map,C_map);
	}
	
	//다 오른쪽으로 밀기 
	private static void moveRight(int num) {
		//0행부터 N-1행까지,
		int [][]C_map=new int[N][N];
		copy(C_map,map);
		int idx=N-1; int cnt=0; //이전값 ? 기억해둘 값?
		for(int i=0; i<N; i++) {
			//모든 열을 관찰한다.
			idx=N-1; cnt=0;
			for(int j=N-1; j>=0; j--) {
				if(map[i][j]!=0) {
					//0이 아니면,
					if(cnt==map[i][j]) { //전꺼랑 같으면 idx-1에!
						map[i][j]=0; //자기자리는 0으로
						map[i][idx+1]*=2;
						cnt=0; //cnt는 0으로 돌려놔서 아무거랑 못같게
					}else { //전꺼랑 다르면 , idx에 넣고 idx++
						int tmp=map[i][j];
						map[i][j]=0;
						map[i][idx]=tmp;
						cnt=tmp;
						idx--;
					}
				}
			}
		}
		dfs(num+1);
		copy(map,C_map);
	}
	//다 위로 밀기
	private static void moveUp(int num) {
		//0열부터 N-1열까지,
		int [][]C_map=new int[N][N];
		copy(C_map,map);
		int idx=0; int cnt=0; //이전값 ? 기억해둘 값?
		for(int j=0; j<N; j++) {
			//모든 행을 관찰한다
			idx=0; cnt=0;
			for(int i=0; i<N; i++) {
				if(map[i][j]==0) continue;
				if(cnt==map[i][j]) {
					map[i][j]=0;
					map[idx-1][j]*=2;
					cnt=0;
				}else {
					int tmp=map[i][j];
					map[i][j]=0;
					map[idx][j]=tmp;
					idx++;
					cnt=tmp;
				}
			}
		}
		dfs(num+1);
		copy(map,C_map);
	}
	
	//다 아래로 밀기
		private static void moveDown(int num) {
			//0열부터 N-1열까지,
			int [][]C_map=new int[N][N];
			copy(C_map,map);
			int idx=N-1; int cnt=0; //이전값 ? 기억해둘 값?
			for(int j=0; j<N; j++) {
				//모든 행을 관찰한다
				idx=N-1; cnt=0;
				for(int i=N-1; i>=0; i--) {
					if(map[i][j]==0) continue;
					if(cnt==map[i][j]) {
						map[i][j]=0;
						map[idx+1][j]*=2;
						cnt=0;
					}else {
						int tmp=map[i][j];
						map[i][j]=0;
						map[idx][j]=tmp;
						idx--;
						cnt=tmp;
					}
				}
			}
			dfs(num+1);
			copy(map,C_map);
		}
	
	
	
	
	
}
