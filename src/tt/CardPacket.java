package tt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardPacket {
  public int cardPackets(List<Integer> cardTypes){
    int n = cardTypes.size();
    int maxCard = Collections.max(cardTypes);
    int addtional = Integer.MAX_VALUE;
    for (int target = 2; target<= maxCard; target++) {
      int res = 0;
      for (int i = 0; i < n; i++) {
        if(cardTypes.get(i) % target != 0) {
          res += target - (cardTypes.get(i) % target);
        }
      }
      addtional = Math.min(addtional, res);
    }
    return addtional;
  }

  public static void main(String[] args) {
    CardPacket packet = new CardPacket();
    List<Integer> cardTypes = new ArrayList<>(Arrays.asList(4,7,5,11,15));
    System.out.println(packet.cardPackets(cardTypes));
  }
}
