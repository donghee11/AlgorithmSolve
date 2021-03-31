package March_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12100 {
	private static int map[][];
	private static int N;
	private static int ans=0;
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
		}//end of input
		dfs(0,map);
		System.out.println(ans);
		
	}//end of main
	
	private static void dfs(int cnt, int [][]map) {
		if(cnt==5) {
			ans=Math.max(findMIN(map), ans);
			return;
		}
		//4가지 방향으로 모두 움직여보기
		moveUP(map, cnt);
		moveDOWN(map,cnt);
		moveLEFT(map, cnt);
		moveRIGHT(map, cnt);
	}
	private static void copy(int [][]map1, int [][]map2) {
		for(int i=0; i<N;i++) for(int j=0; j<N; j++) map1[i][j]=map2[i][j]; //map2를 map1에 복사하는 함수
	}
	private static void moveUP(int [][]map, int cnt) {
		//여기에 인자로 오는 map을 움직일 건데, 이 때 얘를 보관해놔야지 임시적으로
		int C_map[][]=new int[N][N];
		copy(C_map, map);
		//map을 다 위로 밀기, 모든 열에 대해서 수행
		for(int c=0; c<N; c++) {
			//c열에 해당하는 모든 행들 다 조사하기
			int start=0; boolean flag=true;
			for(int r=0; r<N; r++) {
				if(map[r][c]==0) continue; //빈칸이면 pass
				//0이 아닌칸을 만나면
				//아직 안합쳐진애
				int tmp=map[r][c];
				if(flag) {
					//무조건 start에 놓을건데, start-1이 지랑 똑같은거야 그러면 합쳐줘야지!
					if(start-1>=0 && map[start-1][c]==map[r][c]) {
						map[r][c]=0;
						map[start-1][c]*=2; flag=false;
					}else { //똑같은게 아니면 그냥 차곡차곡쌓으면돼
						map[r][c]=0;
						map[start][c]=tmp; flag=true;
						start++; //그 다음껀 그 이후에 생길 테니까 .
						
					}
				}else { //이미 합쳐진 전적이 있는애야
					flag=true;// 돌려놓고
					//그냥 쌓기
					map[r][c]=0;
					map[start][c]=tmp; start++;
				}
				
				
			}
		}
		//여기까지오면 윗쪽으로 이동 끝
		dfs(cnt+1, map);
		copy(map, C_map); //상태 다시 돌려놓기
	}
	
	private static void moveDOWN(int [][]map, int cnt) {
		//여기에 인자로 오는 map을 움직일 건데, 이 때 얘를 보관해놔야지 임시적으로
		int C_map[][]=new int[N][N];
		copy(C_map, map);
		//map을 다 아래로 밀기, 모든 열에 대해서 수행
		for(int c=0; c<N; c++) {
			//c열에 해당하는 모든 행들 다 조사하기
			int start=N-1; boolean flag=true;
			for(int r=N-1; r>=0; r--) {
				if(map[r][c]==0) continue; //빈칸이면 pass
				//0이 아닌칸을 만나면
				//아직 안합쳐진애
				int tmp=map[r][c];
				if(flag) {
					//무조건 start에 놓을건데, start-1이 지랑 똑같은거야 그러면 합쳐줘야지!
					if(start+1<N && map[start+1][c]==map[r][c]) {
						 map[r][c]=0;
						map[start+1][c]*=2; flag=false;
					}else { //똑같은게 아니면 그냥 차곡차곡쌓으면돼
						map[r][c]=0;
						map[start][c]=tmp; flag=true;
						start--; //그 다음껀 그 이후에 생길 테니까 .
					}
				}else { //이미 합쳐진 전적이 있는애야
					flag=true;// 돌려놓고
					//그냥 쌓기
					map[r][c]=0;
					map[start][c]=tmp; start--;
				}
				
				
			}
		}
		//여기까지오면 윗쪽으로 이동 끝
		dfs(cnt+1, map);
		copy(map, C_map); //상태 다시 돌려놓기
	}
	private static void moveLEFT(int [][]map, int cnt) {
		//여기에 인자로 오는 map을 움직일 건데, 이 때 얘를 보관해놔야지 임시적으로
		int C_map[][]=new int[N][N];
		copy(C_map, map);
		//map을 다 왼쪽으로 밀기, 모든 행에 대해서 수행
		for(int r=0; r<N; r++) {
			//c열에 해당하는 모든 행들 다 조사하기
			int start=0; boolean flag=true;
			for(int c=0; c<N; c++) {
				if(map[r][c]==0) continue; //빈칸이면 pass
				//0이 아닌칸을 만나면
				//아직 안합쳐진애
				int tmp=map[r][c];
				if(flag) {
					//무조건 start에 놓을건데, start-1이 지랑 똑같은거야 그러면 합쳐줘야지!
					if(start-1>=0 && map[r][start-1]==map[r][c]) {
						 map[r][c]=0;
						map[r][start-1]*=2; flag=false;
					}else { //똑같은게 아니면 그냥 차곡차곡쌓으면돼
						map[r][c]=0;
						map[r][start]=tmp; flag=true;
						start++; //그 다음껀 그 이후에 생길 테니까 .
					}
				}else { //이미 합쳐진 전적이 있는애야
					flag=true;// 돌려놓고
					//그냥 쌓기
					map[r][c]=0;
					map[r][start]=tmp; start++;
				}
				
				
			}
		}
		//여기까지오면 윗쪽으로 이동 끝
		dfs(cnt+1, map);
		
		copy(map, C_map); //상태 다시 돌려놓기
	}
	private static void moveRIGHT(int [][]map, int cnt) {
		//여기에 인자로 오는 map을 움직일 건데, 이 때 얘를 보관해놔야지 임시적으로
		int C_map[][]=new int[N][N];
		copy(C_map, map);
		//map을 다 왼쪽으로 밀기, 모든 행에 대해서 수행
		for(int r=0; r<N; r++) {
			//c열에 해당하는 모든 행들 다 조사하기
			int start=N-1; boolean flag=true;
			for(int c=N-1; c>=0; c--) {
				if(map[r][c]==0) continue; //빈칸이면 pass
				//0이 아닌칸을 만나면
				//아직 안합쳐진애
				int tmp=map[r][c];
				if(flag) {
					//무조건 start에 놓을건데, start-1이 지랑 똑같은거야 그러면 합쳐줘야지!
					if(start+1<N && map[r][start+1]==map[r][c]) {
						 map[r][c]=0;
						map[r][start+1]*=2; flag=false;
					}else { //똑같은게 아니면 그냥 차곡차곡쌓으면돼
						map[r][c]=0;
						map[r][start]=tmp; flag=true;
						start--; //그 다음껀 그 이후에 생길 테니까 .
					}
				}else { //이미 합쳐진 전적이 있는애야
					flag=true;// 돌려놓고
					//그냥 쌓기
					map[r][c]=0;
					map[r][start]=tmp; start--;
				}
				
				
			}
		}
		//여기까지오면 윗쪽으로 이동 끝
		dfs(cnt+1, map);
		copy(map, C_map); //상태 다시 돌려놓기
	}
	
	private static int findMIN(int [][]map) { //재귀 끝날 때 마다 반복
		int ans=0;
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++) {
				if(map[i][j]!=0) {
					ans=Math.max(ans, map[i][j]);
				}
			}
		return ans;
	}
}
