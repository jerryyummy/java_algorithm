import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
  static final int MOD = (int) (1e9 + 7);
  static final int MAX = 200000; // 假设输入的n不会超过这个数
  static Map<Integer, Integer> primeExponents = new HashMap<>();

  // 预处理质数的指数
  static {
    for (int i = 2; i <= MAX; i++) {
      int num = i;
      for (int factor = 2; factor <= num / factor; factor++) {
        while (num % factor == 0) {
          primeExponents.put(factor, primeExponents.getOrDefault(factor, 0) + 1);
          num /= factor;
        }
      }
      if (num > 1) {
        primeExponents.put(num, primeExponents.getOrDefault(num, 0) + 1);
      }
    }
  }

  public static long countFactors(Map<Integer, Integer> factors) {
    long count = 1;
    for (int exponent : factors.values()) {
      count = (count * (exponent + 1)) % MOD;
    }
    return count;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] array = new int[n];

    long totalSum = 0;
    for (int i = 0; i < n; i++) {
      array[i] = scanner.nextInt();
      totalSum += array[i];
    }

    long maxDecrease = 0;
    long currentDecrease = 0;


    int i = 0, j = 0;
    while (j < n){
      if (array[j]%2==0){
        currentDecrease+=array[j]/2;
        if (currentDecrease<maxDecrease){
          maxDecrease = currentDecrease;
        }
      }else {
        while (i < j){
          currentDecrease-=array[i]/2;
          if (currentDecrease<maxDecrease){
            maxDecrease = currentDecrease;
          }
          i++;
        }
        i= j+1;
      }
      j++;
    }

    while (i<j){
      currentDecrease-=array[i]/2;
      if (currentDecrease<maxDecrease){
        maxDecrease = currentDecrease;
      }
      i++;
    }
    maxDecrease = Math.min(maxDecrease, currentDecrease);

    long maxPossibleSum = totalSum + maxDecrease;

    System.out.println(maxPossibleSum);
  }
}
