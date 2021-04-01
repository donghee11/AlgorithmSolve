package April_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14501_2 {
	private static int N, t[], p[];
	private static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		t=new int[N]; p=new int[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			t[i]=Integer.parseInt(st.nextToken());
			p[i]=Integer.parseInt(st.nextToken());
		}
		dfs(0,0);
		System.out.println(answer);
	}
	private static void dfs(int cnt, int ans) {
		if(cnt>=N) { //해당 요일까지 왔으면 
			answer=Math.max(ans, answer);
			return;
		}
		//오는 요일 뽑기
		//그 요일까지 갈수 있는지
		if(t[cnt]+cnt-1<N) dfs(cnt+t[cnt], ans+p[cnt]);
		//안뽑기
		dfs(cnt+1, ans);
	}
}
