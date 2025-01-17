import java.util.HashMap;
import java.util.Map;

public class Leetcode1396{
  Map<Integer, CheckInData> arrival;
  Map<String, TravelData> averageTime;

  public Leetcode1396() {
    arrival = new HashMap<>();
    averageTime = new HashMap<>();
  }

  public void checkIn(int id, String stationName, int t) {
    arrival.put(id, new CheckInData(stationName, t));
  }

  public void checkOut(int id, String stationName, int t) {
    CheckInData checkInData = arrival.get(id);
    if (checkInData == null) return;

    String startStation = checkInData.stationName;
    int checkInTime = checkInData.time;
    int travelTime = t - checkInTime;

    String key = startStation + "->" + stationName;
    TravelData travelData = averageTime.getOrDefault(key, new TravelData(0, 0));
    travelData.totalTime += travelTime;
    travelData.tripCount += 1;
    averageTime.put(key, travelData);

    arrival.remove(id);
  }

  public double getAverageTime(String startStation, String endStation) {
    // Get the average travel time between the stations
    String key = startStation + "->" + endStation;
    TravelData travelData = averageTime.get(key);
    if (travelData == null || travelData.tripCount == 0) return 0.0;
    return (double) travelData.totalTime / travelData.tripCount;
  }

  private static class CheckInData {
    String stationName;
    int time;

    public CheckInData(String stationName, int time) {
      this.stationName = stationName;
      this.time = time;
    }
  }

  private static class TravelData {
    int totalTime;
    int tripCount;

    public TravelData(int totalTime, int tripCount) {
      this.totalTime = totalTime;
      this.tripCount = tripCount;
    }
  }
}
