package snowflake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxUpgradedServers {

  public static List<Integer> getMaxUpEradedservers (List<Integer> num_servers, List<Integer> money, List<Integer> sell,
          List<Integer> upgrade){
    int n = num_servers.size();
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      res.add(solve(num_servers.get(i), money.get(i), sell.get(i), upgrade.get(i)));
    }
    return res;
  }

  private static int solve(int servers, int money, int sell, int upgrade) {
    while (money < upgrade*servers){
      servers--;
      money+=sell;
    }
    return servers;
  }

  public static void main(String[] args) {
    System.out.println(getMaxUpEradedservers(new ArrayList<>(Arrays.asList(4,3)),
        new ArrayList<>(Arrays.asList(8,9)),
        new ArrayList<>(Arrays.asList(4,2)),
        new ArrayList<>(Arrays.asList(4,5))));
  }

}
