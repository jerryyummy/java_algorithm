import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transaction {
  public static int getMinTransactions(int n, List<List<Integer>> debts) {
    Map<Integer,Long> balanceMap = new HashMap<>();
    for(List<Integer> debt:debts){
      balanceMap.put(debt.get(0),balanceMap.getOrDefault(debt.get(0),0L)-debt.get(2));
      balanceMap.put(debt.get(1),balanceMap.getOrDefault(debt.get(1),0L)+debt.get(2));
    }

    long[] netAmounts = new long[balanceMap.size()];
    int index = 0;
    for (long amount:balanceMap.values()) {
      netAmounts[index++] = amount;
    }

    return minTranHelper(netAmounts, 0);

  }

  private static int minTranHelper(long[] netAmounts, int start){
    while(start<netAmounts.length && netAmounts[start]==0){
      start++;
    }
    if(start==netAmounts.length) return 0;
    int minTran = Integer.MAX_VALUE;
    for (int i = start+1; i < netAmounts.length; i++) {
      if((netAmounts[i]<0L && netAmounts[start]>0L) || (netAmounts[start]<0L && netAmounts[i]>0L)){
        netAmounts[i]+=netAmounts[start];
        minTran = Math.min(minTran,1+minTranHelper(netAmounts, start+1));
        netAmounts[i]-=netAmounts[start];
      }
    }
    return minTran;
  }
}
