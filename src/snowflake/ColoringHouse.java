package snowflake;

public class ColoringHouse {
  int mod = 1000000007;

  int countWaysColorHouse(int n){
    int res = 6;
    if(n == 2) return res;
    while(n>2){
      res = (res*3)%mod;
      n = n/2;
    }
    return res;
  }
}
