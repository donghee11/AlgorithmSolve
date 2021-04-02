package April_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//나무재테크
public class Main_16235 {
	private static int N,M,K;
	private static int a[][];
	private static int food[][];
	private static int treeCnt=0;
	private static ArrayList<Integer> map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken()); //k년후 ?
		a=new int[N][N]; //추가될 양분의 양
		map=new ArrayList[N][N];
		food=new int[N][N];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				a[i][j]=Integer.parseInt(st.nextToken()); //양분의 양 넣기
				map[i][j]=new ArrayList<>();
				food[i][j]=5;
			}
		}
		//상도가 심은 나무의 정보 x,y,z 나무의 위치와 나무의 나이!
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken())-1;
			int y=Integer.parseInt(st.nextToken())-1;
			int z=Integer.parseInt(st.nextToken());
			map[x][y].add(z);
		}
		treeCnt=M; //현재 갯수 !
		for(int k=0; k<K; k++) {
			solve();
			if(treeCnt<=0) break;
		}
		System.out.println(treeCnt);
	}
	//봄, 여름 , 가을 , 겨울 순으로 재테크
	private static int dr[]= {-1,-1,-1,0,0,1,1,1};
	private static int dc[]= {-1,0,1,-1,1,-1,0,1};
	private static void solve() {
		//봄
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				//list가 비어있으면 sort해서 나이 어린애부터 양분 먹도록
				if(map[i][j].size()==0) continue;
				int addFood=0;
				Collections.sort(map[i][j]);
				//자신의 나이인cnt만큼 양분을 먹을 수 있으면 먹기
				int size=map[i][j].size();
				int count=food[i][j];
				for(int s=0; s<size; s++) {
					int cnt=map[i][j].get(0);
					if(count<cnt) { //못먹는 상ㄱ황 
						addFood+=(map[i][j].get(0)/2); map[i][j].remove(0);  treeCnt--; continue;
					}
					//먹을수 있으면, food줄이기, count줄이기, 나이올리기
					food[i][j]-=cnt; count-=cnt;
					map[i][j].remove(0); map[i][j].add(cnt+1);
				}
				//없애는 양분 있으면 더해주기
				food[i][j]+=addFood;
			}
		}//봄 끝

		if(treeCnt<=0)return;
		//가을, 나무 번식! , 겨울엔 동시에 양분만추가하니까 그냥 이때같이해도될거같은데?
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				food[i][j]+=a[i][j];
				int size=map[i][j].size();
				Collections.sort(map[i][j]);
				for(int s=size-1; s>=0; s--) {
					int cnt=map[i][j].get(s);
					if(cnt<5) break; //볼필요 x
					if(cnt%5==0) { //나무가 생길 수 있는 경우
						makeTree(i,j);
					}
				}
			}
		}
	}
	
	private static void makeTree(int r, int c) {
		//(r,c)의 8군데에 나무가 생긴다
		for(int i=0; i<8; i++) {
			int nr=r+dr[i]; int nc=c+dc[i];
			if(nr>=0 && nc>=0&& nr<N && nc<N) {
				map[nr][nc].add(1);
				treeCnt++;
			}
		}
		
	}

}
