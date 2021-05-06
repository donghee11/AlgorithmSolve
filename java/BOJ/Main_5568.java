package May_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main_5568 {
	//카드는 총 n장, 1~99까지 정수.
	//이중에서 k장을 선택하고 가로로 나란히 정수를 만든다.
	private static int n, k;
	private static int map[];
	private static HashSet<String> answer=new HashSet<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		k=Integer.parseInt(br.readLine());
		map=new int[n];
		for(int i=0; i<n; i++) {
			map[i]=Integer.parseInt(br.readLine());
		}
		
		//n개 선택, comb
		visit=new boolean[100000000]; //만들수있는 최대 큰수
		select=new boolean[n];
		comb(0,"");
		System.out.println(answer.size());
	}
	private static boolean visit[], select[];
	
	private static void comb(int cnt,String str) {
		
		if(cnt==k) {
			answer.add(str);
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(select[i]) continue;
			select[i]=true;
			comb(cnt+1, str+map[i]);
			select[i]=false;
		}
	}
}
