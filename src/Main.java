import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt(); // 将 n 修改为 int 类型，适应 Java 集合的索引
    ArrayList<Long> a = new ArrayList<>(); // 使用 ArrayList 存储长整型，方便后续操作
    long sum = 0;
    for (int i = 0; i < n; ++i) {
      long temp = scanner.nextLong();
      a.add(temp);
      sum += temp;
    }
    ArrayList<Long> b = new ArrayList<>(a);
    Collections.sort(b, Collections.reverseOrder());
    long s = n * b.get(0) - sum;
    for (int i = 0; i < n; ++i) {
      if (a.get(i).equals(b.get(0))) {
        System.out.println(sum);
        continue;
      }
      if (n == 2) {
        System.out.println("-1");
        continue;
      }
      long currentA = a.get(i) + 1;
      long diff = b.get(0) - currentA, cur = s - (b.get(0) - currentA + 1), ans = sum + 1;
      if (diff <= cur) {
        ans += 2 * diff;
        System.out.println(ans);
      } else {
        ans += 2 * cur;
        currentA += cur;
        long l = 1, r = 1000000000; // 用长整型表示 1e9
        while (l <= r) {
          long mid = (l + r) / 2;
          long t = mid / (n - 1);
          if (mid % (n - 1) != 0) t += 1;
          if (currentA + mid < t + b.get(0)) l = mid + 1;
          else r = mid - 1;
        }
        ans += 2 * l;
        System.out.println(ans);
      }
    }
    scanner.close();
  }
}
