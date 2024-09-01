package xhs;

import java.util.Arrays;

// 对应小红整理物品
public class MoveItems {
  public int minimumScore(int[] a, int[] b){
    int count1 = 0;
    int count2 = 0;
    for(int i=0; i<a.length; i++){
      if(b[i] == 1){
        if(a[i] == 1){
          count1++;
        }else{
          count2++;
        }
      }
    }

    int totalCountTYPE = count1+1;
    int[][] dp1 = new int[a.length][totalCountTYPE];
    int[][] dp2 = new int[a.length][totalCountTYPE];
    for (int i = 0; i < a.length; i++) {
      Arrays.fill(dp1[i], Integer.MAX_VALUE);
      Arrays.fill(dp2[i], Integer.MAX_VALUE);
    }

    if (b[0] == 1){
      if(a[0] == 1){
        dp1[0][0] = 0;
      }else {
        dp2[0][0] = 0;
      }
    }else {
      dp1[0][1] = 0;
      dp2[0][0] = 0;
    }

    for (int i = 1; i < a.length; i++) {
      if (b[i] == 0){
        if(a[i] == 1){
          for(int j=0; j<totalCountTYPE; j++){
            dp1[i][j] = Math.min(dp1[i-1][j],dp2[i-1][j]+1);
          }
        }else {
          for(int j=0; j<totalCountTYPE; j++){
            dp2[i][j] = Math.min(dp2[i-1][j],dp1[i-1][j]+1);
          }
        }
      }else{
        for(int j=0; j<totalCountTYPE; j++){
          if (j-1>=0){
            dp1[i][j] = Math.min(dp1[i-1][j-1], dp2[i-1][j-1]+1);
          }
          dp2[i][j] = Math.min(dp2[i-1][j], dp2[i-1][j]+1);
        }
      }
    }
    return Math.min(dp1[a.length-1][totalCountTYPE-1],dp2[b.length-1][totalCountTYPE-1]);
  }
}
