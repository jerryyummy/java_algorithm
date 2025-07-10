import java.util.ArrayDeque;
import java.util.Deque;

public class Leetcode3191 {
    public int minOperations(int[] nums) {
        // 使用双端队列flipQueue存储翻转操作的位置
        Deque<Integer> flipQueue = new ArrayDeque<>();
        // 记录操作次数
        int count = 0;

        // 遍历数组中的每个元素
        for (int i = 0; i < nums.length; i++) {
            // 移除队列中已经超出当前元素影响范围的翻转操作
            while (!flipQueue.isEmpty() && i > flipQueue.peekFirst() + 2) {
                flipQueue.pollFirst();
            }

            // 计算当前元素经过队列中所有翻转操作后的实际值
            // nums[i]是原始值，flipQueue.size()表示影响当前元素的翻转操作次数
            // 如果实际值为偶数，则需要进行一次翻转操作
            if ((nums[i] + flipQueue.size()) % 2 == 0) {
                // 如果当前位置i加上2超出数组长度，说明无法进行有效的翻转操作
                if (i + 2 >= nums.length) {
                    return -1;
                }
                // 增加操作次数
                count++;
                // 将当前位置加入队列，表示在此处进行了一次翻转操作
                flipQueue.offerLast(i);
            }
        }

        // 返回最小操作次数
        return count;
    }
}
