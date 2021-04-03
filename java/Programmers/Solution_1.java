package April_01;

class Solution_1 {
	  

	private static int N, result, index;
	private static int[][] map;
	private static boolean[] visit;
	public int[] solution(int n, int[] passenger, int[][] train) {
        int[] answer = new int[2];
        N=n;
        map=new int[n][n];
        for(int i=0; i<n-1; i++) {
        	map[train[i][0]-1][train[i][1]-1]=1;
        	map[train[i][1]-1][train[i][0]-1]=1;
        }// 연결되어있는 부분 처리,
        
        //n을 종착지로 했을 때 갈수 있는 곳   check
       visit=new boolean[n];
       dfs(0,passenger[0],passenger);
       visit[0]=true;
       answer[0]=index+1; answer[1]=result;
        return answer;
    }

	private static void dfs(int idx , int ans, int [] passenger) {
		//모든기차를 돌아다니면서 갈 수 있는 최대 치로 가기
		
		for(int i=1; i<N; i++) {
			//i번째 기차에서 갈 수 있는지
			if(!visit[i] && map[idx][i]==1) {
				//연결되어있는 애라면
                visit[i]=true;
				dfs(i, ans+passenger[i], passenger);
                visit[i]=false;
			}
			
		}
        
		//아무것도 못봘을 떄
		if(result<ans) {
			index=idx;
			result=ans;
		}else if(result==ans) {
			index=Math.max(idx, index);
		}
		
	}
