package meituan;

import java.util.Arrays;

public class MinSubSum {

  public long getEvenSubSum(int[] array){
    int n = array.length;
    long res = 0, sum = 0;
    int N = (int)1E5+10;
    res = sum;  // 答案初始化

    for(int i = 0; i < n; i++){
      if(array[i] % 2 != 0) continue;  // 跳过奇数
      int j = i;  // 找到一段连续的偶数区间
      while(j < n && array[j] % 2 == 0) j++;  // 定位区间[i,j-1]
      long cur = 0, minv = Long.MAX_VALUE;  // 求区间最小值
      for(int k = i; k < j; k++){
        cur = Math.min(cur, 0) + array[k];
        minv = Math.min(minv, cur);
      }
      res = Math.max(res, sum - minv / 2);
      i = j - 1;  // 双指针
    }
    return res;
  }

}
