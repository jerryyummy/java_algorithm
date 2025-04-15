import java.util.Arrays;

public class Leetcode2326 {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] res = new int[m][n];
        for (int[] row : res) Arrays.fill(row, -1); // 默认填 -1

        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;
        ListNode cur = head;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                if (cur != null) {
                    res[top][i] = cur.val;
                    cur = cur.next;
                }
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                if (cur != null) {
                    res[i][right] = cur.val;
                    cur = cur.next;
                }
            }
            right--;

            for (int i = right; i >= left; i--) {
                if (cur != null) {
                    res[bottom][i] = cur.val;
                    cur = cur.next;
                }
            }
            bottom--;

            for (int i = bottom; i >= top; i--) {
                if (cur != null) {
                    res[i][left] = cur.val;
                    cur = cur.next;
                }
            }
            left++;
        }

        return res;
    }
}
