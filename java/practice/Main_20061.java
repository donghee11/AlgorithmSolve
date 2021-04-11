package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20061 {
	private static int N, green[][], blue[][], answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st;
		green=new int[6][4]; blue=new int[4][6];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			int t=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			//t블록을 이동 시키기 ( 올바른 위치에 )
			moveBlock(t,x,y);
			
			//이동시킴으로써 한줄이 꽉찬 상황이 발생했는지 확인하기
			//방금 온 블록이 있던 행/열을 확인하면 된다. --> 이동
			
			//0,1 특별한 칸에 칸이 꽉찼는지 확인하기 --> 이동
			moveSpecialBlock();
		}
		System.out.println(answer);
		System.out.println(calc());
	}
	private static int calc() {
		int cnt=0;
		for(int i=0; i<6; i++)
			for(int j=0; j<4; j++) {
				if(green[i][j]==1) {
					cnt++;
				}
			}
		for(int i=0; i<4; i++) {
			for(int j=0; j<6; j++)
				if(blue[i][j]==1) cnt++;
		}
		return cnt;
	}
	private static void moveSpecialBlock() {
		//green확인하기
		int cnt=0;
		for(int i=0; i<2; i++) {
			boolean flag=false;
			for(int j=0; j<4; j++) {
				if(green[i][j]==1) {
					flag=true; break;
				}
			}
			if(flag) cnt++;
		}
		//cnt줄 움직이기
		if(cnt>=1) {
			for(int i=5; i>=2; i--) {
				for(int j=0; j<4; j++) {
					green[i][j]=green[i-cnt][j];
				}
			}
			for(int i=0; i<2; i++)
				for(int j=0; j<4; j++) green[i][j]=0;
		}
		//blue 이동
		cnt=0;
		for(int i=0; i<2; i++) {
			boolean flag=false;
			for(int j=0; j<4; j++) {
				if(blue[j][i]==1) {
					flag=true; break;
				}
			}
			if(flag) cnt++;
		}
		if(cnt>=1) {
			for(int i=5; i>=2; i--) {
				for(int j=0; j<4; j++) {
					blue[j][i]=blue[j][i-cnt];
				}
			}
			for(int i=0; i<2; i++)
				for(int j=0; j<4; j++) blue[j][i]=0;
		}
	}

	private static void moveBlock(int t, int r, int c) {
		switch(t) {
		case 1:
			//내릴 수 있는 곳 까지 ㄴ내려
			int find=0;
			for(int i=1; i<=5; i++) {
				if(green[i][c]!=0) break; //막힘
				//아니면 그쪽으로이동
				find=i;
			}
			green[find][c]=1;
			//find행이 한줄 꽉 찼는지 확인하기
			checkRow(find);
			find=0;
			for(int i=1; i<=5; i++) {
				if(blue[r][i]!=0) break;
				find=i;
			}
			blue[r][find]=1;
			checkCol(find);
			break;
		case 2:
			//r,c 와 r,c+1
			find=0;
			for(int i=1; i<=5; i++) {
				if(green[i][c]!=0 || green[i][c+1]!=0) {
					break;
				}
				find=i;
			}
			green[find][c]=1; green[find][c+1]=1;
			checkRow(find);
			
			find=1;
			for(int i=2; i<6; i++) {
				if(blue[r][i]!=0) {
					break;
				}
				find=i;
			}
			blue[r][find-1]=1; blue[r][find]=1;
			checkCol(find);
			break;
		case 3: //r,c와 r+1,c
			find=1;
			for(int i=2; i<6; i++) {
				if(green[i][c]!=0) break;
				find=i;
			}
			green[find-1][c]=1; green[find][c]=1;
			checkRow(find);
			find=0;
			for(int i=1; i<6; i++) {
				if(blue[r][i]!=0 || blue[r+1][i]!=0) break;
				find=i;
			}
			blue[r][find]=1; blue[r+1][find]=1;
			checkCol(find);
			break;
		}
	}

	private static void checkCol(int find) {
		//find~2열까지 꽉 찬 행 있는지 확인하기
		for(int c=find; c>=2; c--) {
			boolean flag=true;
			for(int i=0; i<4; i++) {
				if(blue[i][c]==0) {
					flag=false; break;
				}
			}
			if(flag) {
				for(int cc=c; cc>=1; cc--) {
					for(int i=0; i<4; i++) {
						blue[i][cc]=blue[i][cc-1];
					}
				}
				answer++;
				for(int i=0; i<4; i++) blue[i][0]=0;
				c++;
			}
		}
	}

	private static void checkRow(int find) {
		//find~2행까지 꽉 찬 행 있는지 확인하기
		for(int r=find; r>=2; r--) {
			boolean flag=true;
			for(int j=0; j<4; j++) {
				if(green[r][j]==0) {
					flag=false; break;
				}
			}
			if(flag) { //한 줄이 채워진 경우면 여기 제거한다.
				for(int rr=r; rr>=1; rr--) {
					for(int j=0; j<4; j++) {
						green[rr][j]=green[rr-1][j];
					}
				}
				for(int j=0; j<4; j++)
					green[0][j]=0;
				answer++;
				r++; //그 행 다시 계산해야하니까..
			}
		}
	}
}
