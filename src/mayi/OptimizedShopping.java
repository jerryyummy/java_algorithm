package mayi;

import java.util.*;

public class OptimizedShopping {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int k = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    int[] prices = new int[n];
    for (int i = 0; i < n; i++) {
      prices[i] = scanner.nextInt();
    }

    scanner.nextLine(); // Consume newline
    String isOff = scanner.next();

    ArrayList<Double> offPrices = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      offPrices.add(prices[i] * (isOff.charAt(i) == '0' ? 1.0 : 0.95));
    }

    Collections.sort(offPrices);

    int ans = 0;
    double cost = 0;
    for (double p : offPrices) {
      if (cost + p > k) break;
      cost += p;
      ans++;
    }

    System.out.println(ans);
  }
}

