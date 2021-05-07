package May_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1717 {
	private static int N, M; //M은 연산의 갯수이고 숫자는 0이상 n이하
	private static int parent[];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		parent=new int[N+1];
		for(int i=0; i<=N; i++) {
			parent[i]=i; //자기자신으로 초기화
		}
		//0이면 합집합 union, 1로 시작하는 입력에 대해서
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int op=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			if(op==0) {
				union(x,y);
			}else if(op==1) {
				if(find(x)==find(y)) System.out.println("YES");
				else System.out.println("NO");
			}
		}
//		System.out.println(find(1)+" "+find(2)+" "+find(3));
	}
	private static void union(int x, int y) {
		int a=find(x); int b=find(y);
		if(a<b) parent[a]=b;
		else parent[b]=a;
	}
	private static int find(int x) {
		//x의 부모 찾기
		if(parent[x]==x) return x;
		return parent[x]=find(parent[x]);
		
	}
}
