import java.util.ArrayDeque;
import java.util.Deque;

public class Leetcode84 {
    public int largestRectangleArea(int[] heights){
        int[] tmp = new int[heights.length + 2];
        System.arraycopy(heights, 0, tmp, 1, heights.length);
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i < tmp.length; i++) {

            while (!stack.isEmpty() && tmp[stack.peek()]>tmp[i]){//保持递增
                int high = tmp[stack.pop()];
                res = Math.max((i-stack.peek()-1)*high,res);
            }
            stack.push(i);

        }
        return res;
    }
}
