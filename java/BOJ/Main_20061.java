package April_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20061_2 {
	private static int answer;
	private static int[][] blue;
	private static int[][] green;

	public static void main(String[] args) throws NumberFormatException, IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     blue = new int[4][6];
	     green = new int[6][4];
	     int k=Integer.parseInt(br.readLine());
	     StringTokenizer st;
	     for(int i=0; i<k; i++) {
	    	 st=new StringTokenizer(br.readLine());
	    	 int t = Integer.parseInt(st.nextToken());
	         int x = Integer.parseInt(st.nextToken());
	         int y = Integer.parseInt(st.nextToken());
	         
	         moveBlock(t,x,y);
	         getScore(); //몇점 얻을 수 있는지 찾기
	         //스페셜 위치에 있는 칸 밀지 안밀지 결정하기
	         moveGreen(checkGreen());
	         moveBlue(checkBlue());
	     }
	     System.out.println(answer);
	     int count=0;
	     for(int i=0; i<6; i++)
	    	 for(int j=0; j<4; j++) {
	    		 if(green[i][j]==1) count++;
	    		 if(blue[j][i]==1) count++;
	    	 }
	     System.out.println(count);
	}

	private static void moveBlue(int cnt) {
		for(int c=0; c<cnt; c++) {
			for(int i=4; i>=0; i--) {
				for(int j=0; j<4; j++) {
					blue[j][i+1]=blue[j][i];
				}
			}
		}
		for(int i=0; i<2; i++)
			for(int j=0; j<4; j++)
				blue[j][i]=0;
	}

	private static void moveGreen(int cnt) {
		for(int c=0; c<cnt; c++) {
			for(int i=4; i>=0; i--) {
				for(int j=0; j<4; j++) {
					green[i+1][j]=green[i][j];
				}
			}
		}
		for(int i=0; i<2; i++)
			for(int j=0; j<4; j++)
				green[i][j]=0;
	}

	private static int checkGreen() {
		//0,1행에 칸 존재하는지
		int cnt=0;
		for(int i=0; i<2; i++) {
			for(int j=0; j<4; j++) {
				if(green[i][j]==1) {
					cnt++; break;
				}
			}
		}
		return cnt;
	}
	private static int checkBlue() {
		//0,1행에 칸 존재하는지
		int cnt=0;
		for(int i=0; i<2; i++) {
			for(int j=0; j<4; j++) {
				if(blue[j][i]==1) {
					cnt++; break;
				}
			}
		}
		return cnt;
	}

	private static void getScore() {
		//green은 모든 행, blue는 모든 열을 조사한다.
		//green 먼저 조사하자 . 맨 끝의 행부터 조사
		for(int i=5; i>=2; i--) { //마지막 행은 마지막에 처리?
			int cnt=0;
			for(int j=0; j<4; j++) {
				if(green[i][j]==0) break;
				else cnt++;
			}
			//만약 지울수 있는 곳ㅇ라면
			if(cnt==4) {
				answer++;
				cleanGreen(i); //i행을 지우자!
				//i행을 지우고나면 다시 i행을 검사하도록 처리해야하니까
				i++;
			}
		}
		for(int i=5; i>=2; i--) {
			int cnt=0; 
			for(int j=0; j<4; j++) {
				if(blue[j][i]==0) break;
				cnt++;
			}
			if(cnt==4) {
				answer++;
				cleanBlue(i); //i열에 해당하는 부분 싹 지우자
				i++;
			}
		}
	}

	private static void cleanBlue(int c) {
		//입력받은 c열부터 살펴보자
		for(int i=c-1; i>=0; i--) {
			for(int j=0; j<4; j++) {
				blue[j][i+1]=blue[j][i];
			}
		}
	}

	private static void cleanGreen(int r) {
		//이 때 다른점은 끝까지 내려가는게 아니라 한텀만 내려간다는점
		//싹 다 옮김
		for(int i=r-1; i>=0; i--) {
			for(int j=0; j<4; j++) {
				green[i+1][j]=green[i][j];
			}
		}
	}

	private static void moveBlock(int t, int r, int c) {
		int index=1; //이동할수있는 idx를 찾자
		switch(t) {//t가 무엇이냐에 따라 
		case 1://1*1
			blue[r][0]=1; ///r은 변함이없음
			green[0][c]=1; //c는 변함이 없음
			//얘를 어디까지 끌고 갈 수있는지 확인하기
			while(index<6 && blue[r][index]==0) {
				blue[r][index]=1; blue[r][index-1]=0;
				index++; //index부분 찾을때 까지 내리기 작업
			}
			index=1;
			while(index<6 && green[index][c]==0) {
				green[index][c]=1; 
				green[index-1][c]=0; index++;
			}
			break;
		case 2://1*2
			//2개의 ㅈ고ㅓㄴ이 모두 만족하도록 설계해야한다.
			green[0][c]=1; green[0][c+1]=1;
			while(index<6 && green[index][c]==0 && green[index][c+1]==0) {
				green[index][c]=1; green[index][c+1]=1;
				green[index-1][c]=0; green[index-1][c+1]=0;
				index++;
			}
			index=2;
			blue[r][0]=1; blue[r][1]=1;
			while(index<6 && blue[r][index]==0) {
				blue[r][index]=1;
				blue[r][index-2]=0;
				index++;
			}
			break;
		case 3:
			green[0][c]=1; green[1][c]=1;
			index=2;
			while(index<6 && green[index][c]==0) {
				green[index][c]=1; green[index-2][c]=0;
				index++;
			}
			index=1;
			blue[r][0]=1; blue[r+1][0]=1;
			while(index<6 && blue[r][index]==0 && blue[r+1][index]==0) {
				blue[r][index]=1; blue[r+1][index]=1;
				blue[r][index-1]=0; blue[r+1][index-1]=0;
				index++;
			}
			break;
		}
	}
}
