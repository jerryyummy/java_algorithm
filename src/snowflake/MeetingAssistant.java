package snowflake;

import java.util.Map;
import java.util.TreeMap;

public class MeetingAssistant {

  public static String getEarliestMeetTime(String[] events, int k) {
    TreeMap<Integer, Integer> map = new TreeMap<>();

    // Fill the map with busy intervals, taking into account closed intervals
    for (String event : events) {
      String[] parts = event.split(" ");
      int start = toMinutes(parts[2]);
      int end = toMinutes(parts[3]);
      map.put(start, end);
    }

    // The day starts at 0 minutes and ends at 1440 minutes (exclusive)
    int earliestStart = 0; // The earliest possible start time for the meeting in minutes

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      int busyStart = entry.getKey();
      int busyEnd = entry.getValue();

      // If there is enough time between the earliest start time and the beginning of a busy interval
      if (busyStart - earliestStart >= k) {
        return toHHMM(earliestStart);
      }

      // Update the earliest start time to be after the current busy interval (considering closed interval)
      if (busyEnd + 1 > earliestStart) {
        earliestStart = busyEnd + 1;
      }
    }

    // Check for availability between the last busy time and the end of the day
    if (1440 - earliestStart >= k) {
      return toHHMM(earliestStart);
    }

    // If no slot is found
    return "-1";
  }

  // Helper function to convert HH:MM to minutes
  private static int toMinutes(String time) {
    String[] parts = time.split(":");
    return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
  }

  // Helper function to convert minutes to HH:MM format
  private static String toHHMM(int minutes) {
    return String.format("%02d:%02d", minutes / 60, minutes % 60);
  }

  public static void main(String[] args) {
    String[] events = {"Alex sleep 00:00 08:00", "Sam sleep 09:00 16:00", "Alex lunch 12:30 14:59"};
    int k = 60;
    System.out.println(getEarliestMeetTime(events, k));
  }
}
