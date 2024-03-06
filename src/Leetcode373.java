import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Leetcode373 {
  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    List<List<Integer>> res = new ArrayList<>();
    if (nums1.length == 0 || nums2.length == 0 || k == 0) return res;

    // 使用优先队列根据两数之和进行排序
    Queue<int[]> queue = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));

    // 仅从nums1中选取前k个元素与nums2的第一个元素形成对，并添加到优先队列中
    for (int i = 0; i < nums1.length && i < k; i++) {
      queue.offer(new int[]{nums1[i], nums2[0], 0});
    }

    while (k-- > 0 && !queue.isEmpty()) {
      int[] cur = queue.poll();
      res.add(Arrays.asList(cur[0], cur[1])); // JDK 8兼容的列表创建方式
      if (cur[2] == nums2.length - 1) continue; // 如果当前nums1元素的配对已经到达nums2的末尾，则跳过
      queue.offer(new int[]{cur[0], nums2[cur[2] + 1], cur[2] + 1}); // 将下一个nums2元素与当前nums1元素形成对，并加入队列
    }

    return res;
  }
}
