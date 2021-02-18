package Feb_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 알파벳 , 세로 R칸 가로 C칸, 인덱스 (1,1) 부터 시작
 * 말이 지날 수 있는 최대 칸수
 * 
 */
public class Main_1987 {
	static private int R, C;
	static private char map[][];
	static private int dx[]= {1,-1,0,0};
	static private int dy[]= {0,0,-1,1};
	static private int cnt[]; //해당 알파벳이 나왔는지 check하기 위한 배열!
	static private boolean visit[][]; 
	static private int result=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		map=new char[R][];
		visit=new boolean[R][C];
		cnt=new int[27]; //해당 알파벳이 나왔는지 표시하자,
		for(int i=0; i<R; i++) {
			map[i]=br.readLine().toCharArray();
		}
		visit[0][0]=true;
		cnt[map[0][0]-'A']++;
		DFS(0,0,1); //처음에 첫 칸은 무조건 들어가므로,
		System.out.println(result);
	}
	private static void DFS(int x, int y, int ans) {
		boolean flag=false;
		
		for(int i=0; i<4; i++) { //해당 좌표로부터 4방 탐색,
			int nx=x+dx[i]; int ny=y+dy[i];
			if(nx>=0 && ny>=0 && nx<R && ny<C && !visit[nx][ny]) {
				if(cnt[map[nx][ny]-'A']==0) { //아직 나오지 않은 문자라면,
					cnt[map[nx][ny]-'A']++; 
					visit[nx][ny]=true;
					flag=true;
					DFS(nx,ny, ans+1);
					visit[nx][ny]=false;
					cnt[map[nx][ny]-'A']--;
				}
			}
		}
		if(!flag) { //탐색종료 시점이면,
			result=Math.max(result, ans);
			return;
		}
	}
}
