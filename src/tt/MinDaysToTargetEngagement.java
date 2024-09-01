package tt;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/*
long minDaysToTargetEngagement(long initialEngagementScore, long targetEngagementScore, vector<long> trainingEngagementScore) {
    long long currentScore = initialEngagementScore;
    long long days = 0;
    multiset<long> scores(trainingEngagementScore.begin(), trainingEngagementScore.end());

    while (currentScore < targetEngagementScore) {
        days++;
        auto it = scores.lower_bound(currentScore + 1);
        if (it != scores.begin()) {
            --it;
            if (*it > days) {
                currentScore += *it;
                scores.erase(it);
                continue;
            }
        }
        currentScore += days;
    }

    return days;
}
 */
public class MinDaysToTargetEngagement {
  public static long minDaysToTargetEngagement(int initialEngagementScore, int targetEngagementScore, List<Integer> trainingEngagementScores) {
    long currentScore = initialEngagementScore;
    long days = 0;
    // 使用TreeMap模拟multiset的行为
    TreeMap<Integer, Integer> scores = new TreeMap<>();

    // 初始化TreeMap，记录每个分数的频率
    for (int score : trainingEngagementScores) {
      scores.put(score, scores.getOrDefault(score, 0) + 1);
    }

    while (currentScore < targetEngagementScore) {
      days++;
      Integer it = scores.floorKey((int) currentScore);

      if (it != null) {
        currentScore += it;
        if (scores.get(it) == 1) {
          scores.remove(it);
        } else {
          scores.put(it, scores.get(it) - 1);
        }
      } else {
        currentScore += days;
      }
    }

    return days;
  }

  public static void main(String[] args) {
    List<Integer> trainingScores = Arrays.asList(17, 3, 2);
    int initialScore = 1;
    int targetScore = 30;

    long days = minDaysToTargetEngagement(initialScore, targetScore, trainingScores);
    System.out.println("Minimum days to reach target engagement: " + days);
  }

}
