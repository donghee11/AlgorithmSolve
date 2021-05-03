package May_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
public class Main_10814 {
//나이순 정렬
	private static class Pair{
		int n; //나이
		int idx; //가입한 순서
		String name;
		public Pair(int n, int idx, String name) {
			this.n=n;
			this.idx=idx;
			this.name=name;
		}

	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		StringTokenizer st;
		ArrayList<Pair> list=new ArrayList<>();
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			list.add(new Pair(Integer.parseInt(st.nextToken()), i, st.nextToken()));
		}
		Collections.sort(list, new Comparator<Pair>() {
			public int compare(Pair o1, Pair o2) {
				if(o1.n!=o2.n) return o1.n-o2.n;
				else return o1.idx-o2.idx;
			}
		});
		
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<list.size(); i++) {
			sb.append(list.get(i).n).append(" ").append(list.get(i).name).append("\n");
		}
		System.out.println(sb.toString());
	}
}
