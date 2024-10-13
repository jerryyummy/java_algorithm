package tt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindMinimumTripsByTikRouter {
  public static int findMinimumTripsByTikRouter(List<Double> packetSizeList) {
    List<Integer> packetSizesInInt = new ArrayList<>(packetSizeList.size());

    for (double size : packetSizeList) {
      packetSizesInInt.add((int) Math.round(size * 100));
    }

    Collections.sort(packetSizesInInt);

    int leftIndex = 0;
    int rightIndex = packetSizesInInt.size() - 1;
    int totalTrips = 0;

    while (leftIndex <= rightIndex) {
      if (packetSizesInInt.get(leftIndex) + packetSizesInInt.get(rightIndex) <= 300) {
        leftIndex++;
      }
      rightIndex--;
      totalTrips++;
    }

    return totalTrips;
  }

}
