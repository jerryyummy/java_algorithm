package alibaba;

public class BigNum {

  public static String nextBigNumber(long n) {
    n = n + 1;
    String s = String.valueOf(n);
    char[] arr = s.toCharArray();

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] < '5') {
        arr[i] = '5';
        for (int j = i + 1; j < arr.length; j++) {
          arr[j] = '5';
        }
        return new String(arr);
      }
    }
    return new String(arr);
  }

  public static void main(String[] args) {
    System.out.println(BigNum.nextBigNumber(199));
  }
}
