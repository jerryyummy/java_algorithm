import java.util.Arrays;

public class Main {
  public int maxTotalReward(int[] rewardValues) {
    Arrays.sort(rewardValues);
    int totalReward = 0;
    int i = 0;
    while (i < rewardValues.length-1) {
      if (totalReward+rewardValues[i] < rewardValues[i + 1]) {
        totalReward += rewardValues[i];
        i++;
      }else{
        i++;
        while (rewardValues[i] == rewardValues[i - 1]) {
          i++;
        }
      }
    }
    if (i==rewardValues.length-1){
      totalReward+=rewardValues[i];
    }
    return totalReward;
  }
}

