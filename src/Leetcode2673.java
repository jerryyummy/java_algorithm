import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode2673 {
    public int minIncrements(int n, int[] cost) {
        int res = 0;

        // 从最后一个父节点开始自底向上调整
        for (int i = n / 2; i >= 1; i--) {
            int left = 2 * i - 1;   // 左子节点索引
            int right = 2 * i;      // 右子节点索引
            int leftCost = cost[left];
            int rightCost = (right < n) ? cost[right] : 0;  // 右子节点可能不存在

            res += Math.abs(leftCost - rightCost);  // 计算需要的增量
            cost[i - 1] += Math.max(leftCost, rightCost);  // 更新当前父节点的值
        }

        return res;
    }
}
