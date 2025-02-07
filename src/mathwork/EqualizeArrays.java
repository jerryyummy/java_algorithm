package mathwork;

public class EqualizeArrays {
  public static long getMinimumOperations(long[] source, long[] target) {
    int n = source.length;
    long prefixMost = Integer.MAX_VALUE;
    long suffixLeast = 0;
    for (int i = 0; i < n; i++) {
      long diff = target[i] - source[i];
      if (diff < 0 || diff < suffixLeast){
        return -1;
      }
      prefixMost = Math.min(prefixMost, diff-suffixLeast);
      suffixLeast = Math.max(suffixLeast, diff-prefixMost);
    }
    return target[0] - source[0] +suffixLeast;
  }

  public static void main(String[] args) {
    System.out.println(EqualizeArrays.getMinimumOperations(new long[7], new long[]{4,3,2,1,2,3,4}));
  }
}
