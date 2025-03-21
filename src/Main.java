import java.util.*;

public class Main {
    static int res = 0; // 记录符合条件的排列数量

    public static void main(String[] args) {
        System.out.println(solve(1, 1, 1)); // Output: 2
        System.out.println(solve(1, 2, 1)); // Output: 1
    }

    public static int solve(int one, int two, int limit) {
        res = 0; // 重要：每次调用 solve 需要重置 res
        List<Integer> current = new ArrayList<>();
        backtrack(one, two, limit, current);
        return res;
    }

    public static void backtrack(int leftOne, int leftTwo, int limit, List<Integer> current) {
        // 递归终止条件：如果所有 1 和 2 都用完
        if (leftOne == 0 && leftTwo == 0) {
            res++;
            return;
        }

        // 尝试放置 "1"
        if (leftOne > 0) {
            current.add(1);
            if (isValid(current, limit)) {
                backtrack(leftOne - 1, leftTwo, limit, current);
            }
            current.remove(current.size() - 1);
        }

        // 尝试放置 "2"
        if (leftTwo > 0) {
            current.add(2);
            if (isValid(current, limit)) {
                backtrack(leftOne, leftTwo - 1, limit, current);
            }
            current.remove(current.size() - 1);
        }
    }

    public static boolean isValid(List<Integer> array, int limit) {
        int n = array.size();
        if (n <= limit) return true;

        // 检查长度超过 limit 的子数组是否符合要求
        for (int i = 0; i <= n - limit - 1; i++) {
            boolean hasOne = false, hasTwo = false;
            for (int j = i; j < i + limit + 1; j++) {
                if (array.get(j) == 1) hasOne = true;
                if (array.get(j) == 2) hasTwo = true;
            }
            if (!hasOne || !hasTwo) return false;
        }
        return true;
    }
}
