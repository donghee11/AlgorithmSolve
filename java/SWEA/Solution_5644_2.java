package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5644_2 {
	static int M, bcCnt;
	static int[] pathA, pathB, playerA, playerB;
	static int [][] bc;
	private static int dx[]= {0,0,1,0,-1};
	private static int dy[]= {0,-1,0,1,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		StringTokenizer st;
		playerA=new int[2]; //사용자 A의 위치
		playerB=new int[2];
		for(int tc=1; tc<=TC; tc++) {
			st=new StringTokenizer(br.readLine());
			M=Integer.parseInt(st.nextToken());
			bcCnt=Integer.parseInt(st.nextToken());
			
			playerA[0]=playerA[1]=1;
			playerB[0]=playerB[1]=10;
			pathA=new int[M+1];
			pathB=new int[M+1];
			bc=new int[bcCnt][4];
			st=new StringTokenizer(br.readLine());
			for(int i=1; i<=M; i++) {
				pathA[i]=Integer.parseInt(st.nextToken());
			}
			st=new StringTokenizer(br.readLine());
			for(int i=1; i<=M; i++) {
				pathB[i]=Integer.parseInt(st.nextToken());
			}
			for(int i=0; i<bcCnt; i++) {
				st=new StringTokenizer(br.readLine());
				bc[i][0]=Integer.parseInt(st.nextToken());
				bc[i][1]=Integer.parseInt(st.nextToken());
				bc[i][2]=Integer.parseInt(st.nextToken());
				bc[i][3]=Integer.parseInt(st.nextToken());
				
			}
			System.out.println("#"+tc+" "+move());
			
		}//end of tc
	}
	private static int move() {
		//매시간마다 각 위치에서 두 플레이어의 최대 충전량을 합산하여 계산
		int totalSum=0;
		for(int t=0; t<=M; t++) {
			playerA[0]+=dx[pathA[t]];
			playerB[0]+=dx[pathB[t]];
			playerA[1]+=dy[pathA[t]];
			playerB[1]+=dy[pathB[t]];
			
			//현재위치에서 두 플레이어 모두가 합산된 충전량을 최대가 되도록
			totalSum+=getMaxCharge();
		}
		return totalSum;
	}
	
	private static int check(int a, int x, int y) {
		if(Math.abs(bc[a][0]-x)+Math.abs(bc[a][1]-y)<=bc[a][2]) {
			return bc[a][3];
		}
		return 0;
	}
	private static int getMaxCharge() {
		//중복 조합
		int max=0;
		for(int a=0; a<bcCnt; a++) { //플레이어 a가 선택한 bc
			for(int b=0; b<bcCnt; b++) { //플레이어 b가 선택한 bc
				// 가능한지 봐야하고, 둘이 같은지 확인해야지
				int sum=0;
				//거리가 되는지!
				int amountA=check(a,playerA[0],playerA[1]);
				int amountB=check(b,playerB[0],playerB[1]);
				if(a!=b) sum=(amountA+amountB);
				else {
					sum=amountA>amountB?amountA:amountB; //둘 중에 그냥 큰 값 가져오면 된다.
				}
				if(sum>max) max=sum;
			}
		}
		return max;
	}
}
