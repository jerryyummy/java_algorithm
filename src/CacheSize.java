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
        List<Map.Entry<Integer, Integer>> a = new ArrayList<>();//存储时间点和该时间点上的增减量（开始时间增加，结束时间减少）
        Map<Integer, Integer> mp = new HashMap<>();//存储每个查询时间点的缓存大小
        List<Integer> ans = new ArrayList<>(Collections.nCopies(queries.size(), 0));//初始化为与查询列表等长且填充为0的列表，用于存储最终的答案
        for (List<Integer> d : data) {//对每个时间段的开始和结束时间创建条目，结束时间是开始时间加上持续时间后的下一个时间点（因为结束时间是不包含在内的）
            a.add(new AbstractMap.SimpleEntry<>(d.get(0), 1));
            a.add(new AbstractMap.SimpleEntry<>(d.get(0) + d.get(1) + 1, -1));
        }
        a.sort(Map.Entry.comparingByKey());//对 a 进行排序，以确保时间点按升序排列
        List<Integer> b = new ArrayList<>(queries);
        Collections.sort(b);
        int j = 0;
        int tmp = 0;
        for (int q : b) {
            while (j < a.size() && a.get(j).getKey() <= q) {
                tmp += a.get(j).getValue();//遍历列表 a，累加到当前查询时间点的所有增减量
                j++;
            }
            mp.put(q, tmp);//将累加结果存储在 mp 中，键为查询时间点，值为累加的结果
        }
        for (int i = 0; i < queries.size(); i++) {
            ans.set(i, mp.get(queries.get(i)));
        }
        return ans;
    }
}

