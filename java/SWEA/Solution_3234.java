package feb_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3234 {
	static private int N;
	static private int map[];
	static private int dp[]= {1,2,4,8,16,32,64,128,256,512,1024,2048};
	static private int ans;
	private static int sum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		StringTokenizer st;
		for(int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			map=new int[N];
			ans=0; sum=0;
			st=new StringTokenizer(br.readLine());

			for(int i=0; i<N; i++) {
				map[i]=Integer.parseInt(st.nextToken());
				sum+=map[i];
			}
			Arrays.sort(map);
			searchNum(0,0,0, 0);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}//end of tc
		System.out.println(sb.toString());
		
	}//end of main
	
	private static void searchNum(int cnt, int left, int right, int visit) {
		if(left<right) return; //오른쪽이 더 커지면 바로 return
		if(cnt==N) {
			//다 뽑았으면, 가짓수 올리기
			ans++;
			return;
		}
		//왼쪽이 이미 sum의 절반보다크다면, 뒤의 경우는 볼필요 없이 가능하다.
		if((sum/2)<left) {
			int mul=1;
			//남은 갯수
			int count=N-cnt;
			for(int i=1; i<=count; i++) {
				mul*=i;
			}
			mul*=dp[count];
			ans+=mul;
			return;
		}
		
		for(int i=N-1; i>=0; i--) { //큰 숫자부터 넣기 위해서
	
			if((visit& (1<<i))!=0) continue;
			//왼쪽에 놓은경우
			searchNum(cnt+1, left+map[i], right,visit|(1<<i));
			//오른쪽에 놓은경우
			searchNum(cnt+1, left, right+map[i],visit|(1<<i));
		}
	}
}//end of class
