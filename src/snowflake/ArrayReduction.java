package snowflake;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ArrayReduction {
  public int[] getMaxArray(int[] arr) {
    if (arr == null || arr.length == 0) return new int[0];

    List<Integer>[] indices = new List[arr.length];
    for (int i = 0; i < arr.length; i++) {
      int num = arr[i];
      if (num < 0 || num >= arr.length) continue;
      if (indices[num] == null) indices[num] = new ArrayList<>();
      indices[num].add(i);
    }
    // from now on arr[] is useless

    List<Integer> list = new ArrayList<>();
    int low = 0;
    while (low < indices.length) {
      int k = low;
      int i = 0;
      // we can keep track of a upper bound rather than indices.length, that is the previous MEX, but that won't help much
      while (i < indices.length) {
        if (indices[i] == null) break;
        int index = binarySearchCeiling(indices[i], low);
        if (index == -1) break;
        k = Math.max(k, indices[i].get(index));
        i++;
      }
      list.add(i);
      low = k + 1;
    }

    int[] result = new int[list.size()];
    for (int i = 0; i < list.size(); i++) result[i] = list.get(i);
    return result;
  }

  public static int binarySearchCeiling(final List<Integer> list, final int key) {
    if (key > list.get(list.size() - 1)) return -1;
    if (key <= list.get(0)) return 0;

    int low = 0;
    int high = list.size() - 1;

    while (low < high) {
      final int mid = low + (high - low) / 2;
      final int entry = list.get(mid);
      if (entry < key) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }

    return low;
  }

}
