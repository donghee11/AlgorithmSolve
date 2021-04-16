package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2239 {
	private static int map[][];
	private static ArrayList<int[]> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map=new int[9][9];
		row=new boolean[9][9];
		col=new boolean[9][9];
		square=new boolean[9][9];
		list=new ArrayList<>();
		for(int i=0; i<9; i++) {
			String str=br.readLine();
			for(int j=0; j<9; j++) {
				map[i][j]=str.charAt(j)-'0';
				if(map[i][j]==0) {
					list.add(new int[] {i,j});
				}else {
					row[i][map[i][j]-1]=true;
					col[j][map[i][j]-1]=true;
					square[3*(i/3)+j/3][map[i][j]-1]=true;
				}
			}
		}//end of input
		recur(0);
	}
	
	private static boolean row[][];
	private static boolean col[][];
	private static boolean square[][];
	
	private static void recur(int cnt) {
		//종료조건
		if(cnt==list.size()) {
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			
			System.exit(0);
			return;
		}
		int[]a=list.get(cnt);
		int r=a[0]; int c=a[1];
		for(int x=0; x<9 ;x++) {
			//넣을 수 있는조건만족
			if(!row[r][x] && !col[c][x] && !square[3*(r/3)+c/3][x]) {
				map[r][c]=x+1;
				row[r][x]=true; col[c][x]=true;
				square[3*(r/3)+(c/3)][x]=true;
				recur(cnt+1);
				map[r][c]=0;
				row[r][x]=false; col[c][x]=false;
				square[3*(r/3)+(c/3)][x]=false;
			}
		}
	}
	
	
	
}
