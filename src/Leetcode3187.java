import java.util.ArrayList;
import java.util.List;

public class Leetcode3187 {
  private int[] segTree;
  private int n;
  private int[] nums;

  public List<Integer> countOfPeaks(int[] nums, int[][] queries) {
    this.n = nums.length;
    this.nums = nums;
    this.segTree = new int[4 * n];
    build(0, 0, n - 1);

    List<Integer> result = new ArrayList<>();
    for (int[] query : queries) {
      if (query[0] == 1) {
        int li = query[1];
        int ri = query[2];
        if (li + 1 <= ri - 1) {
          result.add(query(0, 0, n - 1, li + 1, ri - 1));
        } else {
          result.add(0);
        }
      } else if (query[0] == 2) {
        int index = query[1];
        int value = query[2];
        nums[index] = value;
        update(0, 0, n - 1, index);
        if (index > 0) {
          update(0, 0, n - 1, index - 1);
        }
        if (index < n - 1) {
          update(0, 0, n - 1, index + 1);
        }
      }
    }
    return result;
  }

  private void build(int node, int start, int end) {
    if (start == end) {
      segTree[node] = isPeak(start) ? 1 : 0;
    } else {
      int mid = (start + end) / 2;
      int leftChild = 2 * node + 1;
      int rightChild = 2 * node + 2;
      build(leftChild, start, mid);
      build(rightChild, mid + 1, end);
      segTree[node] = segTree[leftChild] + segTree[rightChild];
    }
  }

  private boolean isPeak(int i) {
    if (i == 0 || i == n - 1) return false;
    return nums[i] > nums[i - 1] && nums[i] > nums[i + 1];
  }

  private void update(int node, int start, int end, int idx) {
    if (start == end) {
      segTree[node] = isPeak(start) ? 1 : 0;
    } else {
      int mid = (start + end) / 2;
      int leftChild = 2 * node + 1;
      int rightChild = 2 * node + 2;
      if (idx <= mid) {
        update(leftChild, start, mid, idx);
      } else {
        update(rightChild, mid + 1, end, idx);
      }
      segTree[node] = segTree[leftChild] + segTree[rightChild];
    }
  }

  private int query(int node, int start, int end, int L, int R) {
    if (R < start || end < L) return 0;
    if (L <= start && end <= R) return segTree[node];
    int mid = (start + end) / 2;
    int leftChild = 2 * node + 1;
    int rightChild = 2 * node + 2;
    int leftQuery = query(leftChild, start, mid, L, R);
    int rightQuery = query(rightChild, mid + 1, end, L, R);
    return leftQuery + rightQuery;
  }
}
