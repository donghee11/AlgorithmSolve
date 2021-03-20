package March_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N, M, ans;
	private static int s[];
	private static int cnt[];
	public static void main(String[] args) throws IOException {
		//n명의 학생, m개의 쌍
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		s=new int[N+1];
		cnt=new int[N+1];
		for(int i=1; i<=N; i++) {
			s[i]=i;
		}
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			//(a랑 b를 연결)
			//union할 때, 만약 s[i] 값이 i가 아니라면, 얘 부모랑 무조건 합치기?
			union(a,b);
		}
		for(int i=1; i<=N; i++) {
			if(s[i]==i) ans++;
		}
		System.out.println(ans);
		
	}//end of main
	
	private static void union(int x, int y) {
		int n=find(x);
		int m=find(y);
		//root의 깊이를 비교해서 깊이가 작은것을 큰것 아래에 붙히기
		if(cnt[n]<cnt[m]) {
			s[n]=m;
		}else {
			s[m]=n;
			if(cnt[n]==cnt[m]) {
				cnt[n]++;
			}
		}
	}
	
	private static int find(int x) {
		//x의 조상 찾기,
		if(s[x]==x) return x;
		return find(s[x]);
	}
}
