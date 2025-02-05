import java.util.Arrays;

public class Leetcode948 {
  public int bagOfTokensScore(int[] tokens, int power) {
    Arrays.sort(tokens);
    int res = 0;
    int i = 0, j = tokens.length - 1;
    int curr = power;
    int maxRes = 0; // 记录最大得分

    while (i <= j) {
      // 先尝试增加 score
      if (curr >= tokens[i]) {
        curr -= tokens[i];
        i++;
        res++;
        maxRes = Math.max(maxRes, res); // 记录最大得分
      }
      // 如果无法增加 score，尝试用 score 换取 power
      else if (res > 0) {
        curr += tokens[j];
        j--;
        res--;
      } else {
        break;
      }
    }
    return maxRes;
  }
}
