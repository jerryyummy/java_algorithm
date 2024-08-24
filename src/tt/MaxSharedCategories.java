package tt;

import java.util.Arrays;
import java.util.List;

public class MaxSharedCategories {
  public static int maxSharedCategories(List<Integer> favoriteCategories) {
    int maxn = 1;
    int n = favoriteCategories.size();

    for (int i = 0; i < n; i++) {
      maxn = Math.max(maxn, favoriteCategories.get(i));
    }

    int[] divisorCount = new int[maxn + 1];

    for (int i = 0; i < n; i++) {
      int num = favoriteCategories.get(i);
      for (int j = 1; j * j <= num; j++) {
        if (num % j == 0) {
          divisorCount[j]++;
          if (j != num / j) {
            divisorCount[num / j]++;
          }
        }
      }
    }

    for (int i = maxn; i >= 1; i--) {
      if (divisorCount[i] >= 2) {
        return i;
      }
    }

    return 1;
  }

  public static void main(String[] args) {
    List<Integer> favoriteCategories = Arrays.asList(4, 2, 6, 8);
    System.out.println(maxSharedCategories(favoriteCategories));
  }
}