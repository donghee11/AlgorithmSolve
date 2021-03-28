
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//경사로 
//길을 지나갈 수 있는 조건 
//1. 모든 칸의 높이가 같거나, 2. 경사로를 놓는다. (높이가 항상 1이고 길이는 L)
//높이차가 2이상이면 바로 break
//자기보다 높은애를 발견하면, 자신부터 시작해서 L개의 길이가 되는지 조건을 확인하기,
//자기보다 낮은애를 발견하면, 걔부터 L개가 높이가 똑같아서 놓을수 있는지 확인하기
//겹치면 안되는 조건은? --> 경사로를 놓았던 위치를 기억해두기, 그 위치로 넘어가면 얘는 그냥 바로 break
public class Main_14890 {
	private static int map[][];
	private static int N,L;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		L=Integer.parseInt(st.nextToken());
		map=new int[N][N];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)
				map[i][j]=Integer.parseInt(st.nextToken());
		}//end of input
		int answer=0;
		for(int i=0; i<N; i++) {
			if(checkRow(i)) answer++;
		}
		for(int i=0; i<N; i++) {
			if(checkCol(i)) answer++;
		}
		System.out.println(answer);
	}
	
	private static boolean checkCol(int c) {
		boolean flag[]=new boolean[N]; //경사로 놓은곳에 표시하기
		for(int i=0; i<N-1; i++) {
			//같은 경우 pass
			if(map[i][c]==map[i+1][c]) continue;
			//차이가 2이상나는 경우
			if(Math.abs(map[i][c]-map[i+1][c])>=2) return false;
			//오르막길 인 경우
			if(map[i][c]-map[i+1][c]==-1) {
				//map[r][i]부터 L개가 다 같은 지 확인해야함 !! 그리고 경사로 세워주자
				int temp=map[i][c];
				if(i-L+1<0) return false;
				for(int j=i, l=0; j>0 && l<L; j--, l++) {
					if(temp!=map[j][c]) return false;
					if(flag[j]) return false;
					flag[j]=true;
				}
			}else if(map[i][c]-map[i+1][c]==1) { //내리막길 인경우
				int temp=map[i+1][c];
				if(i+L>=N) return false;
				for(int j=i+1, l=0; j<N && l<L; j++, l++) {
					if(temp!=map[j][c]) return false;
					if(flag[j]) return false;
					flag[j]=true;
				}
			}
		}
		
		return true;
	}

	private static boolean checkRow(int r) { //r행을 검사,
		boolean flag[]=new boolean[N]; //경사로 놓은곳에 표시하기
		for(int i=0; i<N-1; i++) {
			//같은 경우 pass
			if(map[r][i]==map[r][i+1]) continue;
			//차이가 2이상나는 경우
			if(Math.abs(map[r][i]-map[r][i+1])>=2) return false;
			//오르막길 인 경우
			if(map[r][i]-map[r][i+1]==-1) {
				//map[r][i]부터 L개가 다 같은 지 확인해야함 !! 그리고 경사로 세워주자
				int temp=map[r][i];
				if(i-L+1<0) return false;
				for(int j=i, l=0; j>0 && l<L; j--, l++) {
					if(temp!=map[r][j]) return false;
					if(flag[j]) return false;
					flag[j]=true;
				}
			}else if(map[r][i]-map[r][i+1]==1) { //내리막길 인경우
				int temp=map[r][i+1];
				if(i+L>=N) return false;
				for(int j=i+1, l=0; j<N && l<L; j++, l++) {
					if(temp!=map[r][j]) return false;
					if(flag[j]) return false;
					flag[j]=true;
				}
			}
		}
		
		return true;
	}
}
