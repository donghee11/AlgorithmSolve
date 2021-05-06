package May_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//가장 가까운 공통 조상
public class Main_3584 {
	private static int N; //노드의 수
	private static int tree[];
	private static ArrayList<Integer> node1, node2;
	public static void main(String[] args) throws NumberFormatException, IOException {
		//두 노드의 가장 가까운 공통 조상은, 두 노드를 자손으로 가지면서 깊이가 가장 깊은 노드
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			//N-1개의 정점이 주어진다
			tree=new int[N+1];
			for(int i=0; i<N-1; i++) {
				st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				tree[b]=a; //b의 부모가 a
			}
			//구하려는 노드 2개
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			//x의 조상에 해당하는 것들 모으고, y의 조상에 해당하는 것들 모으기
			node1=new ArrayList<>(); node2=new ArrayList<>();
			
			//자기자신부터 넣고시작
			node1.add(x); node2.add(y);
			searchParent(x,node1); searchParent(y,node2);
			
			//끝에서부터 조사하면서 
			///무조건 마지막 노드가 공통 조상인건 확실
			//같은게 나올 때 까지
			int i=node1.size()-1; int j=node2.size()-1;
			int num=0;
			while(i>=0 && j>=0) {
				int a=node1.get(i); int b=node2.get(j);
				if(a!=b) break;
				//같으면
				num=a;
				i--; j--;
			}
			System.out.println(num);
		}
	}
	
	private static void searchParent(int x, ArrayList<Integer>list) {
		if(tree[x]==0) return;
		
		list.add(tree[x]);
		searchParent(tree[x], list);
	}
}
