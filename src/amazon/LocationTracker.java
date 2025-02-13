package amazon;

import java.util.*;

public class LocationTracker {
    public static List<Integer> getFinalLocations(List<Integer> locations, List<Integer> movedFrom, List<Integer> movedTo) {
        Set<Integer> locationSet = new HashSet<>(locations);

        int transferLen = movedFrom.size();
        for (int i = 0; i < transferLen; i++) {
            locationSet.remove(movedFrom.get(i));
            locationSet.add(movedTo.get(i));
        }

        List<Integer> sortedLocations = new ArrayList<>(locationSet);
        Collections.sort(sortedLocations);
        return sortedLocations;
    }

    public static void main(String[] args) {
        List<Integer> locations = Arrays.asList(1, 7, 6, 8);
        List<Integer> movedFrom = Arrays.asList(1, 7, 2);
        List<Integer> movedTo = Arrays.asList(2, 9, 5);

        System.out.println(getFinalLocations(locations, movedFrom, movedTo));
    }
}
