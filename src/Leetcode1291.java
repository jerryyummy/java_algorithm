import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leetcode1291 {
    List<Integer> list = new ArrayList<>();

    public List<Integer> sequentialDigits(int low, int high) {
        for (int i = 1; i <= 9; i++) { // 起始数字从 1 到 9
            backtrack(low, high, i);
        }
        Collections.sort(list); // 最后排序
        return list;
    }

    public void backtrack(int low, int high, int curr) {
        if (curr > high) return;

        if (curr >= low && curr <= high) {
            list.add(curr);
        }

        int lastDigit = curr % 10;
        if (lastDigit < 9) { // 还可以往后添加递增的数
            int next = curr * 10 + (lastDigit + 1);
            backtrack(low, high, next);
        }
    }
}
