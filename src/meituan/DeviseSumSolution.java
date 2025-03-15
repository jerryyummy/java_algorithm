package meituan;

import java.io.*;
import java.util.*;

public class DeviseSumSolution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int l1 = Integer.parseInt(st.nextToken());
        int r1 = Integer.parseInt(st.nextToken());
        int l2 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());

        // For f(i,j) = 1 when i is divisible by j, and 0 otherwise
        // We need to count pairs (i,j) where i is divisible by j

        // Key observation: For each i, the number of j where i is divisible by j
        // is equal to the number of divisors of i that fall within range [l2, r2]

        long result = 0;

        // We'll iterate through values of i from l1 to r1
        // and count how many of its divisors fall within [l2, r2]
        for (int i = l1; i <= r1; i++) {
            // Find all divisors of i
            for (int j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    // j is a divisor of i
                    if (j >= l2 && j <= r2) {
                        result++;
                    }

                    // i/j is also a divisor of i (except when j*j = i)
                    if (j * j != i && (i / j) >= l2 && (i / j) <= r2) {
                        result++;
                    }
                }
            }
        }

        System.out.println(result);
    }
}
