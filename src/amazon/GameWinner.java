package amazon;

import java.util.*;

public class GameWinner {
    private static final int MOD = (int) (1e9 + 7);

    public static int countGamesWonByGroup1(List<Integer> group1, List<Integer> group2) {
//        int n = group1.size();
//        int count = 0;
//
//        // Iterate through all possible pairs (i, j) where 0 <= i < j < n
//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j < n; j++) {
//                int sum1 = group1.get(i) + group1.get(j);
//                int sum2 = group2.get(i) + group2.get(j);
//                if (sum1 > sum2) {
//                    count = (count + 1) % MOD;
//                }
//            }
//        }
//
//        return count;

        int n = group1.size();

        // 1) Build D array
        int[] D = new int[n];
        for (int i = 0; i < n; i++) {
            D[i] = group1.get(i) - group2.get(i);
        }

        // 2) Sort D
        Arrays.sort(D);

        // 3) Count pairs (i, j) with D[i] + D[j] > 0 using two-pointer
        int l = 0, r = n - 1;
        int count = 0;
        while (l < r) {
            if (D[l] + D[r] > 0) {
                count = ((r - l) + count) % MOD;  // all indices from l..(r-1) form valid pairs with r
                r--;
            } else {
                l++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        List<Integer> firstTeam = Arrays.asList(1,2,3,4);
        List<Integer> secondTeam = Arrays.asList(1,3,2,2);
        System.out.println(countGamesWonByGroup1(firstTeam, secondTeam));
    }
}
