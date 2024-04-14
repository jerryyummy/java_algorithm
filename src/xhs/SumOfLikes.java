package xhs;

/*
本题可以抽象为最大值最小化的问题，采用二分法解决
对于ai，如果想要变成最大值而且操作次数尽可能小，最优的策略是当前帖子点一下，然后找到最小的帖子点一下重复这个操作
定义所有帖子点赞数是sum，ai能达到的最大值是x
则对于当前帖子而言，操作次数c1 = x - ai
假设其他帖子都达到x，则对应操作次数是c2 = （n-1）* x-（sum-ai）
我们可以先点击当前帖子，因此如果当前的x满足条件，则一定有c1-1 <= c2
通过二分法计算得到最小的x，可以得到最小的总和sum+（x-ai）*2 - 1
考虑特殊情况n=2
 */

import java.util.Scanner;

public class SumOfLikes {
  public static boolean check(long like, long mid, long n, long sum){
    return mid-like <= mid*(n-1)-(sum-like)+1;
  }

  public static void minSum(int[] likes){
    long sum = 0;
    int maxv = 0;
    for(int like:likes){
      sum = sum+like;
      maxv = Math.max(maxv,like);
    }

    if (likes.length==2){
      for (int i = 0; i < likes.length; i++) {
        if (likes[i] == maxv) {
          System.out.println(likes[i]);
        }else {
          System.out.println(-1);
        }
      }
    }

    for (int i = 0; i < likes.length; i++) {
      long low = maxv, high = (long)1e12;
      while (low<high){
        long mid = low + (high-low)/2;
        if (check(likes[i],mid,likes.length,sum)) high = mid;
        else low = mid+1;
      }
      long res = sum+low-likes[i]+Math.max(0,low-likes[i]-1);
      System.out.println(res);
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] likes = new int[n];
    for (int i = 0; i < n; i++) {
      likes[i] = scanner.nextInt();
    }
    minSum(likes);
  }
}
