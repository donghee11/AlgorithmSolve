package March_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//RGB 거리 2, 빨/초/파 
//기존 RGB거리에서 추가된것 : 1번집과 N번 집이 같으면 안된다는 것.
public class Main_17404 {
	private static int map[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		map=new int[N][3];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++)
				map[i][j]=Integer.parseInt(st.nextToken());
		}//end of input
		
		int dp[][]=new int[N][3];
		int ans=Integer.MAX_VALUE;
		//총 3번을 반복,
		for(int i=0; i<3; i++) { //순서대로 첫번째 집이 빨강, 초록, 파랑
			
			for(int j=0; j<3; j++) {
				if(i==j) dp[0][j]=map[0][j];
				else dp[0][j]=1001; //범위가 1~1000,
			}
			for(int j=1; j<N; j++) {
				dp[j][0]=Math.min(dp[j-1][1], dp[j-1][2])+map[j][0];
				dp[j][1]=Math.min(dp[j-1][0], dp[j-1][2])+map[j][1];
				dp[j][2]=Math.min(dp[j-1][0], dp[j-1][1])+map[j][2];
			}
			for(int j=0; j<3; j++) {
				if(i!=j) ans=ans>dp[N-1][j]?dp[N-1][j]:ans;
			}
		}
		
		System.out.println(ans);

	}

}
