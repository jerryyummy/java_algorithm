package tt;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinimumTransaction {
  public int minimumTransaction(List<Integer> transaction) {
    int cnt0 = Collections.frequency(transaction, 0);
    int cnt1 = transaction.size() - cnt0;

    int ans0 = 0;
    for (int i = 0; i < cnt0; i++) {
      if (transaction.get(i) == 1) {
        ans0++;
      }
    }

    int ans1 = 0;
    for (int i = 0; i < cnt1; i++) {
      if (transaction.get(i) == 0) {
        ans1++;
      }
    }

    return Math.min(ans0, ans1);
  }

  public static void main(String[] args) {
    MinimumTransaction mt = new MinimumTransaction();
    System.out.println(mt.minimumTransaction(Arrays.asList(1,0,1,0,1)));
  }
}
