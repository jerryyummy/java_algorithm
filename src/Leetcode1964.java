import java.util.ArrayList;
import java.util.List;

public class Leetcode1964 {
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int n = obstacles.length;
        int[] result = new int[n];
        // 用于存储每个长度的障碍课程的最小结尾高度
        List<Integer> tails = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int height = obstacles[i];

            // 找到tails中第一个大于当前高度的位置
            // 这表示我们可以在所有小于等于当前高度的序列后添加当前元素
            int left = 0, right = tails.size();
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails.get(mid) > height) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            // left就是最长的可以继续的序列长度
            result[i] = left + 1;

            // 如果left等于tails的长度，说明我们可以形成一个更长的序列
            if (left == tails.size()) {
                tails.add(height);
            } else {
                // 否则更新对应长度的最小结尾高度
                tails.set(left, height);
            }
        }

        return result;
    }
}
