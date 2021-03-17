import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
//12 10 24 31 25

public class Solution {
	private static class Pair{
		int x,y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	private static int tmplen=0;
	private static int dx[]= {1,-1,0,0};
	private static int dy[]= {0,0,1,-1};
	private static int N, map[][], map2[][], map3[][];
	private static ArrayList<Pair> list;
	private static int max_num, min_len;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			max_num=0; min_len=Integer.MAX_VALUE;
			tmplen=0;
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			map2=new int[N][N];
			map3=new int[N][N];
			list=new ArrayList<>();
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					map2[i][j]=map[i][j];
					if(map[i][j]==1 && i!=0 && j!=0 && i!=N-1 && j!=N-1) {
						list.add(new Pair(i,j)); //끝자리도 아니어야함,
					}
				}
			}//end of input
			comb(0,0,0, map);
			sb.append("#").append(tc).append(" ").append(min_len).append("\n");
			
		}//end of tc
		System.out.println(sb.toString());
	}//end of main

	
	private static void comb(int idx, int cnt, int len, int [][]map) {
		int[][]copy=new int[N][N];
		if(idx==list.size()) {
			if(max_num<cnt) {
				max_num=cnt;
				min_len=len;
			}else if(max_num==cnt) {
				min_len=Math.min(min_len, len);
			}
			return;
		}
		for(int j=0; j<4; j++) {
			copyMap(copy,map);
			if(isPossible(list.get(idx).x, list.get(idx).y, j, copy)) {
				comb(idx+1,cnt+1,len+tmplen, copy);
			}
		}
			
		comb(idx+1,cnt,len, map2);
	}
	
	//전선 만들기
	private static void makeLine(int x, int y , int d, int [][]map2) {
		int nx=x; int ny=y;
		tmplen=0;
		while(true) {
			nx+=dx[d]; ny+=dy[d];
			if(nx<0 || ny<0 || nx>=N || ny>=N ) return;
			map2[nx][ny]=2;
			tmplen++;
		}
		
	}
	private static boolean isPossible(int x, int y, int d, int [][]map2) {
		
		int nx=x; int ny=y;
		while(true) {
			nx+=dx[d]; ny+=dy[d];
			if(nx<0 || ny<0 || nx>=N || ny>=N ) {
				makeLine(x,y,d,map2);
				return true;
			}
			if(map2[nx][ny]!=0) return false;
		}
	}
	
	private static void copyMap(int [][]arr, int [][]arr2) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				arr[i][j]=arr2[i][j]; //arr2를 arr에 복사한다.
			}
		}
	}
	
	
}
