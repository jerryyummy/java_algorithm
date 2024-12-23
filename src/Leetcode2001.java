import java.util.HashMap;
import java.util.Map;

public class Leetcode2001 {
    public static long interchangeableRectangles(int[][] rectangles) {
        long result = 0;
        Map<Pair, Integer> mp = new HashMap<>();

        for (int[] rect : rectangles) {
            int gcdValue = gcd(rect[0], rect[1]);
            Pair key = new Pair(rect[0] / gcdValue, rect[1] / gcdValue);
            if (mp.containsKey(key)) {
                result += mp.get(key);
            }

            mp.put(key, mp.getOrDefault(key, 0) + 1);
        }

        return result;
    }

    private static int gcd(int a, int b) {
        while (b!= 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass()!= o.getClass()) return false;
            Pair pair = (Pair) o;
            return first == pair.first && second == pair.second;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + first;
            result = 31 * result + second;
            return result;
        }
    }
}
