package citadel;

import java.util.*;

public class ConsistentLogs {
  public static int findConsistentLogs(List<Integer> userEvents) {
    if (userEvents == null || userEvents.isEmpty()) {
      return 0;
    }

    // Step 1: Count frequency of each event in the entire list.
    Map<Integer, Integer> totalFreq = new HashMap<>();
    for (Integer event : userEvents) {
      totalFreq.put(event, totalFreq.getOrDefault(event, 0) + 1);
    }

    // Find the minimum frequency among all events.
    int minFreq = Collections.min(totalFreq.values());

    // Initialize variables for sliding window.
    int windowStart = 0, maxLength = 0;
    Map<Integer, Integer> windowFreq = new HashMap<>();

    // Step 2: Use a sliding window to find the shortest subsequence.
    for (int windowEnd = 0; windowEnd < userEvents.size(); windowEnd++) {
      int currentEvent = userEvents.get(windowEnd);
      windowFreq.put(currentEvent, windowFreq.getOrDefault(currentEvent, 0) + 1);

      // Ensure no frequency in the window exceeds the minimum frequency.
      while (Collections.max(windowFreq.values()) > minFreq) {
        int startEvent = userEvents.get(windowStart);
        windowFreq.put(startEvent, windowFreq.get(startEvent) - 1);
        if (windowFreq.get(startEvent) == 0) {
          windowFreq.remove(startEvent);
        }
        windowStart++;
      }

      // Update maxLength if the current window size meets the criteria.
      maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
    }

    return maxLength;
  }

}
