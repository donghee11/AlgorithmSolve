package March_04;

import java.util.Scanner;

public class 배열돌리기 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int [][]map=new int[n][n];
		//filling data
		int k=1;
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				map[i][j]=k++;
		
		System.out.println("회전하기 전");
		print(n,map);
		System.out.println("시계방향 회전 90도");
		int [][]rightResult=rightRotate(n, map);
		print(n,rightResult);
		System.out.println("반시계 방향 회전 90도");
		int [][]leftResult=leftRotate(n,map);
		print(n, leftResult);
		System.out.println("위아래 뒤집기");
		int[][]reverseResult=reverseTopBottom(n,map);
		print(n,reverseResult);
		System.out.println("좌우로 뒤집기");
		int [][] reverseResult2=reverseleftright(n,map);
		print(n,reverseResult2);
	}
	
	
	private static int[][] reverseleftright(int n, int[][] map) {
		int [][]temp=new int[n][n];
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++) {
				temp[i][j]=map[i][n-1-j];
			}
		return temp;
	}

	private static int[][] reverseTopBottom(int n, int[][] map) {
		int[][]temp=new int[n][n];
		//끝에서부터 작성하기
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				temp[i][j]=map[n-1-i][j];
			}
		}
		return temp;
	}

	private static int[][] leftRotate(int n, int[][] map) {
		int[][]temp=new int[n][n];
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				temp[i][j]=map[j][n-i-1];
		return temp;
	}

	private static int[][] rightRotate(int n, int[][] map) {
		int [][]temp=new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++)
				temp[i][j]=map[n-j-1][i];
		}
		return temp;
	}


	private static void print(int n, int[][]map) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
