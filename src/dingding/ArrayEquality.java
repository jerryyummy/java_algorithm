package dingding;

import java.util.HashMap;
import java.util.Map;

public class ArrayEquality {
  private static boolean canMakeAllElementsEqual(int[] array) {
    Map<Integer, Integer> factorCountMap = new HashMap<>();

    // 对数组中的每个元素进行质因数分解
    for (int element : array) {
      Map<Integer, Integer> primeFactors = getPrimeFactors(element);
      for (Map.Entry<Integer, Integer> entry : primeFactors.entrySet()) {
        factorCountMap.put(entry.getKey(), factorCountMap.getOrDefault(entry.getKey(), 0) + entry.getValue());
      }
    }

    // 检查每个质因子的指数是否都是偶数
    for (int count : factorCountMap.values()) {
      if (count % 2 != 0) {
        return false; // 如果有一个质因子的指数不是偶数，则返回 false
      }
    }
    return true; // 所有质因子的指数都是偶数
  }

  private static Map<Integer, Integer> getPrimeFactors(int number) {
    Map<Integer, Integer> factors = new HashMap<>();
    for (int i = 2; i <= number / i; i++) {
      while (number % i == 0) {
        factors.put(i, factors.getOrDefault(i, 0) + 1);
        number /= i;
      }
    }
    factors.put(number, 1);
    if (number > 1) {
    }
    return factors;
  }
}
