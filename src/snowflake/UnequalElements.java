package snowflake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UnequalElements {

  // Method to find the maximum length of subsequence with unequal adjacent elements
  public static int findMaxLength(List<Integer> skills, int k) {
    List<List<Integer>> merged = mergeConsecutiveSubsequences(skills);
    int[][] dp = new int[merged.size()][k+1];
    for (int i = 0; i < merged.size(); i++) {
      dp[i][0] = merged.get(i).get(1);
    }
    int res = 0;
    for (int i = 1; i < merged.size(); i++) {
      int[][] temp = dp.clone();
      for (int j = 0; j <= k; j++) {
        for (int l = 0; l < i; l++) {
          if (Objects.equals(merged.get(l).get(0), merged.get(i).get(0))){
            temp[i][j] = Math.max(temp[i][j], dp[l][j]+merged.get(i).get(1));
          }else{
            if (j>0){
              temp[i][j] = Math.max(temp[i][j], dp[l][j-1]+merged.get(i).get(1));
            }
          }
        }
      }
      dp = temp;
    }

    for (int i = 0; i < merged.size(); i++) {
      for (int j = 0; j <= k; j++) {
        res = Math.max(res, dp[i][j]);
      }
    }
    return res;
  }

  public static List<List<Integer>> mergeConsecutiveSubsequences(List<Integer> skills) {
    if (skills.isEmpty()) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();
    int currentNum = skills.get(0);
    int count = 1;
    for (int i = 1; i < skills.size(); i++) {
      if (skills.get(i) == currentNum) {
        count++;
      }else{
        res.add(Arrays.asList(currentNum, count));
        currentNum = skills.get(i);
        count = 1;
      }
    }
    res.add(Arrays.asList(currentNum, count));
    return res;
  }

  // Main method for testing the algorithm
  public static void main(String[] args) {
    int[] skills = {1, 1, 2, 3, 2, 1};
    int k = 2;
    System.out.println("Maximum length of subsequence: " + findMaxLength(Arrays.stream(skills).boxed().collect(
        Collectors.toList()), k));
  }
}


