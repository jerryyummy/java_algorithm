package dingding;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SubmissionPenalty {

  public static int minPenalty(String s, int k) {
    Map<Character, Integer> firstSuccess = new HashMap<>();
    Map<Character, Integer> failuresBeforeSuccess = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (!firstSuccess.containsKey(c)) {
        firstSuccess.put(c, i + 1);
        failuresBeforeSuccess.put(c, 0);
      } else {
        if (!failuresBeforeSuccess.containsKey(c)) {
          failuresBeforeSuccess.put(c, 0);
        }
      }
    }

    return calculatePenalty(firstSuccess, failuresBeforeSuccess, k, true);
  }

  public static int maxPenalty(String s, int k) {
    Map<Character, Integer> lastSuccess = new HashMap<>();
    Map<Character, Integer> failuresBeforeSuccess = new HashMap<>();

    for (int i = s.length() - 1; i >= 0; i--) {
      char c = s.charAt(i);
      if (!lastSuccess.containsKey(c)) {
        lastSuccess.put(c, i + 1);
        failuresBeforeSuccess.put(c, 0);
      } else {
        if (!failuresBeforeSuccess.containsKey(c)) {
          failuresBeforeSuccess.put(c, 1);
        } else {
          failuresBeforeSuccess.put(c, failuresBeforeSuccess.get(c) + 1);
        }
      }
    }

    return calculatePenalty(lastSuccess, failuresBeforeSuccess, k, false);
  }

  public static int calculatePenalty(Map<Character, Integer> successMap, Map<Character, Integer> failureMap, int k, boolean min) {
    if (k==0) return 0;
    int penalty = 0;
    PriorityQueue<Integer> queue = new PriorityQueue(min ? Comparator.naturalOrder() : Comparator.reverseOrder());

    for (char c : successMap.keySet()) {
      queue.add(successMap.get(c) + failureMap.get(c));
    }

    while (!queue.isEmpty() && k > 0) {
      penalty += queue.poll();
      k--;
    }

    return penalty;
  }
}
