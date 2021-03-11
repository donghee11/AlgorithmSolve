package March_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1일, 1달 , 3달, 1년
public class Solution_1952 {
	private static int arr[],dp[];
	private static int m[], ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			arr=new int[4];
			m=new int[12];
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++)
				arr[i]=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<12; i++)
				m[i]=Integer.parseInt(st.nextToken());
			ans=arr[3]; //일년 이용권을 사용하는 경우로 초기화 해놓기,
			dp=new int[12];
			dp[0]=Math.min(arr[0]*m[0], arr[1]);
			dp[1]=Math.min(dp[0]+m[1]*arr[0], dp[0]+arr[1]);
			dp[2]=Math.min(Math.min(dp[1]+arr[0]*m[2], dp[1]+arr[1]), arr[2]);
			for(int i=3; i<12; i++) {
				dp[i]=Math.min(Math.min(dp[i-1]+m[i]*arr[0], dp[i-1]+arr[1]), dp[i-3]+arr[2]);
			}
			ans=Math.min(dp[11], ans);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}//end of tc
		System.out.println(sb.toString());
	}//end of main
}
