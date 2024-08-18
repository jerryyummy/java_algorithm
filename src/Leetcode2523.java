public class Leetcode2523 {

  public int[] closestPrimes(int left, int right) {
    int[] res = new int[]{-1, -1};
    boolean[] isPrime = isPrime(left, right);

    // Find the first prime number in the range [left, right]
    int startPrime = left;
    while (startPrime <= right && !isPrime[startPrime]) {
      startPrime++;
    }

    if (startPrime > right) {
      // No primes found in the range
      return res;
    }

    int lastPrime = startPrime;
    int currentPrime = startPrime + 1;

    while (currentPrime <= right) {
      while (currentPrime <= right && !isPrime[currentPrime]) {
        currentPrime++;
      }

      if (currentPrime > right) {
        break;
      }

      if (currentPrime - lastPrime < res[1] - res[0] || res[0] == -1) {
        res[0] = lastPrime;
        res[1] = currentPrime;
      }

      lastPrime = currentPrime;
      currentPrime++;
    }

    return res;
  }

  public boolean[] isPrime(int start, int end) {
    boolean[] res = new boolean[end + 1];

    // Initialize all numbers as prime except 0 and 1.
    for (int i = 2; i <= end; i++) {
      res[i] = true;
    }
    res[0] = false;
    res[1] = false;

    // Sieve algorithm
    for (int p = 2; p * p <= end; p++) {
      if (res[p]) {
        // Mark multiples of p as non-prime
        for (int i = p * p; i <= end; i += p) {  // Increment by p instead of 1
          res[i] = false;
        }
      }
    }

    return res;
  }
}
