package snowflake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class JobExcution {
  public int getMinimumOperations(int[] executionTime, int x, int y){
    int n = executionTime.length;
    Arrays.sort(executionTime);
    int i = 0;
    while (executionTime[n-1]>0){
      i++;
      executionTime[n-1]-=x;
      int left = 0, right = n-2;
      while (left<right){
        int mid = (left+right)/2;
        if(executionTime[mid]<0) left = mid+1;
        else right = mid;
      }
      for (int j = left; j < n-1; j++) {
        executionTime[j]-=y;
      }
      Arrays.sort(executionTime);
    }
    return i;
  }

  public static void main(String[] args) {
    JobExcution solution = new JobExcution();
    int[] temp = new int[100000];
    Random random = new Random();
    for (int i = 0; i < 100000; i++) {
      temp[i] = random.nextInt((int) 1e9);
    }
    int x = random.nextInt((int) 1e9);
    int y = x-2>0? x-2:1;
    System.out.println(solution.getMinimumOperations(temp, x, y));
  }

}
