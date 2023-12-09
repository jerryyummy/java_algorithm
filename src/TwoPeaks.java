import java.util.*;

/*
In an array of integers, a peak is an element that is strictly the maximum element among all other elements and is not at the first or last index.
For example, in [1, 3, 2], 3 is a peak. In [5, 1, 2], there is no peak because the maximum value is the first element.
Finally, [3, 5, 5, 2] has no peak because no element is strictly greater than the others.
There is an array of n integers, hills[n]. For each hills[i], determine if two subsequences can be created where hills[i] is a peak,
the other subsequence has a peak, and the two subsequences contain all elements of the array. Create a string where the ith value is '1' if they can be created, or 'O' otherwise.
Note: A subsequence is derived from a sequence by deleting zero or more elements without changing
the order of the remaining elements, for example [3, 4] is a subsequence of [5, 3, 2, 4],
while [2, 3, 1] is not.

Example:
n = 6
hills = [1, 5, 2, 4, 2, 3]

Test each hills[i]:
• The first value cannot be a peak since it is a border. ans = '0'
• Divide hills into [1, 5, 3] and [2, 4, 2]. The second hill is the peak in both groups. ans = '01'
• hills[2] cannot be a peak of its group, e.g., [1, 2, 2] has no peak. ans = '010'
• Divide hills into [1, 5, 3] and [2, 4, 2]. The second hill is the peak in both groups. ans = '0101'
• hills/4] cannot be a peak of its group, e.g., in [1, 2, 3], 2 is not a peak. ans = '01010'
• The last value cannot be a peak since it is a border. ans = '010100'
There may be more than one way to divide the array. These are just representative. Return '010100'.

Function Description:
Complete the function twoPeaks in the editor below. The function must return a string that represents the beautiful hills.
twoPeaks has the following parameter:
int hills[n]: the array to analyze

Returns:
string: Each character string[i] is 1 if hills[i] is a peak, or 0 if not.
 */
public class TwoPeaks {
    public static String twoPeaks(List<Integer> hills) {
        Map<Integer, Integer> mp = new HashMap<>(), L = new HashMap<>(), R = new HashMap<>();
        StringBuilder res = new StringBuilder("0");
        int n = hills.size();
        List<Integer> l = new ArrayList<>(Collections.nCopies(n, 0));
        List<Integer> r = new ArrayList<>(Collections.nCopies(n, 0));
        int maxv = 0;
        for (int i = 0; i < n; i++) {
            maxv = Math.max(maxv, hills.get(i));
            mp.put(hills.get(i), mp.getOrDefault(hills.get(i), 0) + 1);
            for (int j = 0; j < i; j++)
                if (hills.get(j) < hills.get(i)) {
                    l.set(i, l.get(i) + 1);
                    L.put(i, hills.get(j));
                }
            for (int j = i + 1; j < n; j++)
                if (hills.get(j) < hills.get(i)) {
                    r.set(i, r.get(i) + 1);
                    R.put(i, hills.get(j));
                }
        }
        for (int i = 1; i < n - 1; i++) {
            boolean flag = false;
            for (int j = 1; j < n - 1; j++) {
                if (i != j) {
                    int li = l.get(i);
                    int ri = r.get(i);
                    int lj = l.get(j);
                    int rj = r.get(j);

                    if (Math.max(hills.get(i), hills.get(j)) == maxv) {
                        if (mp.get(maxv) > 2)
                            continue;
                        if (mp.get(maxv) == 2 && Math.min(hills.get(i), hills.get(j)) < maxv)
                            continue;
                        if (i < j) {
                            if (hills.get(i) < hills.get(j))
                                lj--;
                            if (hills.get(j) < hills.get(i))
                                ri--;
                        } else {
                            if (hills.get(i) < hills.get(j))
                                rj--;
                            if (hills.get(j) < hills.get(i))
                                li--;
                        }
                        if (Math.min(li, lj) == 0)
                            continue;
                        if (li <= 1 && lj <= 1 && Objects.equals(L.get(i), L.get(j)))
                            continue;
                        if (Math.min(ri, rj) == 0)
                            continue;
                        if (ri <= 1 && rj <= 1 && Objects.equals(R.get(i), R.get(j)))
                            continue;

                        flag = true;
                        break;
                    }
                }
            }
            res.append(flag ? '1' : '0');
        }
        res.append('0');
        return res.toString();
    }
}
