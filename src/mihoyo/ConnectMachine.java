package mihoyo;

public class ConnectMachine {

  public int maxMachine(int[] machine){
    int n = machine.length;
    int[][] dp = new int[n][n];
    int res = 1;

    for (int i = 0; i < n; i++) {
      dp[i][i] = 1;
    }
    for (int i = 1; i < n; i++) {
      dp[0][i] = 2;
    }

    for (int j = 1; j < n; j++) {

      for (int i = 1; i < j; i++) {
        int curr = 2;
        for (int k = 0; k < i; k++) {

          if (machine[k]+machine[j] < machine[i]*2){
            curr = Math.max(curr,dp[k][i]+1);
          }
        }
        dp[i][j] = curr;
        res = Math.max(dp[i][j],res);
      }
    }

    return res;
  }

  public static void main(String[] args) {
    ConnectMachine connectMachine = new ConnectMachine();
    System.out.println(connectMachine.maxMachine(new int[]{1,99,100,2,3}));
  }

}
