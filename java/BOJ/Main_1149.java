package Feb_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * RGB 거리,
 * 빨강, 초록, 파랑으로 칠하는 비용,
 * dp[N][color]= N번째 집을 color로 칠할 때 최소비용을구하는 dp
 */
public class Main_1149 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		int map[][]=new int[N][3];
		int dp[][]=new int[N][3];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++)
				map[i][j]=Integer.parseInt(st.nextToken());
		}
		//0번째 집은 무조건 다 
		dp[0][0]=map[0][0]; dp[0][1]=map[0][1]; dp[0][2]=map[0][2];
		
		//그 이후부터는 ?
		//그 다음집은 앞에 있는거랑 안겹치려면 다른 인덱스 있는 거 중에 작은걸로, 
		for(int i=1; i<N; i++) {
			dp[i][0]=Math.min(dp[i-1][1], dp[i-1][2])+map[i][0];
			dp[i][1]=Math.min(dp[i-1][0], dp[i-1][2])+map[i][1];
			dp[i][2]=Math.min(dp[i-1][0], dp[i-1][1])+map[i][2];
		}
		int answer=Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2]));
		System.out.println(answer);
	}
}
