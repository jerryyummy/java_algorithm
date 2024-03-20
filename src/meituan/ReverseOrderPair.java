package meituan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class FenwickTree {

  private int[] tree;
  private int n;

  public FenwickTree(int n) {
    this.n = n;
    this.tree = new int[n + 1];
  }

  public void add(int x, int value) {
    x++; // Fenwick tree index starts from 1
    while (x <= n) {
      tree[x] += value;
      x += x & (-x);
    }
  }

  public int sum(int x) {
    int sum = 0;
    while (x > 0) {
      sum += tree[x];
      x -= x & (-x);
    }
    return sum;
  }

  public int rangeSum(int l, int r) {
    return sum(r) - sum(l);
  }
}

public class ReverseOrderPair {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt() - 1; // Adjusting indices to be 0-based
    }

    FenwickTree fenwickTree = new FenwickTree(n);
    long ans = 0;
    int[] l = new int[n];
    for (int i = 0; i < n; i++) {
      l[i] = fenwickTree.rangeSum(a[i], n);
      ans += l[i];
      fenwickTree.add(a[i], 1);
      System.out.println(l[i]);
    }

    FenwickTree fenwickTree1 = new FenwickTree(n);
    int[] r = new int[n];
    for (int i = n - 1; i >= 0; i--) {
      r[i] = n - 1 - i - fenwickTree1.rangeSum(a[i], n);
      fenwickTree1.add(a[i], 1);
    }

    for (int i = 0; i < n; i++) {
      System.out.print((ans - r[i] - l[i] + i) + " ");
    }
  }


}
