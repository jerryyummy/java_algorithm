public class Leetcode1011 {
  public int shipWithinDays(int[] weights, int days) {
    int sum = 0;
    int max = 0;
    for (int weight:weights){
      sum += weight;
      max = Math.max(max,weight);
    }
    int low = max, high = sum;
    while(low < high){
      int capacity = (low+high)/2;
      int day = 1;
      int temp = 0;
      for(int weight:weights){
        if (temp+weight<=capacity){
          temp+=weight;
        }else{
          temp = weight;
          day++;
        }
      }
      if (day>days){
        low = capacity+1;
      }else{
        high = capacity;
      }
    }
    return low;
  }
}
