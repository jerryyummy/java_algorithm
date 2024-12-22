import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Leetcode3399 {
    public int minLength(String s, int numOps) {
        List<Integer> A = s.chars().mapToObj(Character::getNumericValue).collect(Collectors.toList());

        List<Integer> L = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < A.size(); i++) {
            if (A.get(i).equals(A.get(i - 1))) {
                count++;
            } else {
                L.add(count);
                count = 1;
            }
        }
        L.add(count);

        return binarySearch(A, numOps, L);
    }

    private int binarySearch(List<Integer> A, int numOps, List<Integer> L) {
        int l = 1;
        int r = 100000;
        while (l < r) {
            int m = (l + r) / 2;
            int need = check(A, m, L);
            if (need <= numOps) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    private int check(List<Integer> A, int k, List<Integer> L) {
        if (k == 1) {
            int res = 0;
            for (int i = 0; i < A.size(); i++) {
                if (A.get(i) == (i % 2)) {
                    res++;
                }
            }
            return Math.min(res, A.size() - res);
        }
        int sum = 0;
        for (int l : L) {
            sum += l / (k + 1);
        }
        return sum;
    }
}
