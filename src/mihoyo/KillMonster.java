package mihoyo;

import java.util.Arrays;
import java.util.Scanner;

public class KillMonster {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int t = scanner.nextInt();
    for (int i = 0; i < t; i++) {
      int n = scanner.nextInt();
      int[] life = new int[n];
      for (int j = 0; j < n; j++) {
        life[j] = scanner.nextInt();
      }
      int e = scanner.nextInt();
      int r = scanner.nextInt();
      int[] res = solve(life,e,r);
      System.out.println(res[0]+" "+res[1]);
    }

  }

  public static int[] solve(int[] life, int e, int r){
    Arrays.sort(life);
    int cntE = 0, cntR = 0;
    boolean[] state = new boolean[life.length];
    int[] origin = Arrays.copyOf(life,life.length);
    while (life[life.length-1] >0){
      int index = 0;
      for (int i = 0; i < life.length; i++) {
        if (life[i] <= 0) continue;
        if (!state[i] && life[i]-e < origin[i]/2) index++;
      }
      cntE+=1;
      cntR+=index;
      for (int i = 0; i < life.length; i++) {
        life[i] = life[i]-e-index*r;
      }
    }

    return new int[]{cntE,cntR};
  }
}
