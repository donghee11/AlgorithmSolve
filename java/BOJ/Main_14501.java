package April_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//퇴사

public class Main_14501 {
	private static int N;
	private static int t[], p[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st;
		t=new int[N+1]; p=new int[N+1];
		
		for(int i=1; i<=N; i++) {
			st=new StringTokenizer(br.readLine());
			t[i]=Integer.parseInt(st.nextToken());
			p[i]=Integer.parseInt(st.nextToken());
			
		}
		
		int dp[]=new int[N+1];
		if(t[1]<=N) dp[t[1]]=p[1]; //첫번째 일을 수행했을 때 경우
		for(int i=1; i<=N; i++) {
			//i 번째 일을 수행하기 위해서는 ? (i-1)번째 일을 수행하고 + i번재 일을 수행!!
			//i번째 일을 하는데 걸리는 시간은 t[i]!!! 
			if(i-1+t[i]<=N && dp[i-1+t[i]]<dp[i-1]+p[i]) { //범위 안에 들면서, 최댓값이 더 커지는 경우
				//바꾸기전에 다 그 전껄로 채우기
				for(int j=i; j<i-1+t[i]; j++) dp[j]=Math.max(dp[j-1], dp[j]);
				dp[i-1+t[i]]=dp[i-1]+p[i];
			}else {
				dp[i]=Math.max(dp[i-1], dp[i]); //그 외에는 이어받음
			}
		}
		System.out.println(dp[N]);
	}
}
