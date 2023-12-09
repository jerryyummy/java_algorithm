import java.util.*;

/*
Implement a prototype of a time-to-live cache.
Given a 2-d array of integers of size n × 2 data, data[i] represents that the ith data point was inserted into the cache at time data[i][0]. It lives in the cache for data[i][1] time. For an array of q queries, queries[g), find the number of data items in the cache at each time query[i].
Example:
Suppose n = 3, data = [105231, 183], [105334, 34], [105198, 543]], q = 2, and queries = [105338, 105410].

Function Description:
Complete the function getCacheSize in the editor below.
getCacheSize has the following parameters:
int data[n][2]: the insertion time and TTL score of each data inserted in the cache
int queries[g]: the queries

Returns:
int arr[q]: the answers to the queries

Constraints:
• 1<=n<=1e5
• 1<=n<=1e5
• 1 ≤ data [i][O]. data [i][1] =1e9
• 1 s queries/il  = 1e9
 */
public class CacheSize {
    public static List<Integer> getCacheSize(List<List<Integer>> data, List<Integer> queries) {
        List<Map.Entry<Integer, Integer>> a = new ArrayList<>();
        Map<Integer, Integer> mp = new HashMap<>();
        List<Integer> ans = new ArrayList<>(Collections.nCopies(queries.size(), 0));
        for (List<Integer> d : data) {
            a.add(new AbstractMap.SimpleEntry<>(d.get(0), 1));
            a.add(new AbstractMap.SimpleEntry<>(d.get(0) + d.get(1) + 1, -1));
        }
        a.sort(Map.Entry.comparingByKey());
        List<Integer> b = new ArrayList<>(queries);
        Collections.sort(b);
        int j = 0;
        int tmp = 0;
        for (int q : b) {
            while (j < a.size() && a.get(j).getKey() <= q) {
                tmp += a.get(j).getValue();
                j++;
            }
            mp.put(q, tmp);
        }
        for (int i = 0; i < queries.size(); i++) {
            ans.set(i, mp.get(queries.get(i)));
        }
        return ans;
    }
}

