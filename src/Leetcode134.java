public class Leetcode134 {
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int hasGas = 0;
    int hasCost = 0;
    for (int i = 0; i < gas.length; i++) {
      hasGas+=gas[i];
      hasCost+=cost[i];
    }
    if (hasGas<hasCost) return -1;
    int startingIdx = 0, currentFuel = 0;
    for(int i = 0;i<gas.length;i++){
      if(currentFuel <0){
        //not possible to start from i-1th to reach ith index
        currentFuel = 0;
        startingIdx = i;
      }
      currentFuel += gas[i]-cost[i];
    }
    return startingIdx;
  }
}
