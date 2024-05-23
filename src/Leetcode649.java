public class Leetcode649 {
  public String predictPartyVictory(String senateStr) {
    // R = true表示本轮循环结束后，字符串里依然有R。D同理
    Boolean R = true, D = true;
    // 当flag大于0时，R在D前出现，R可以消灭D。当flag小于0时，D在R前出现，D可以消灭R
    int flag = 0;
    byte[] senate =  senateStr.getBytes();
    while (R && D) { // 一旦R或者D为false，就结束循环，说明本轮结束后只剩下R或者D了
      R = false;
      D = false;
      for (int i = 0; i < senate.length; i++) {
        if (senate[i] == 'R') {
          if (flag < 0) senate[i] = 0; // 消灭R，R此时为false
          else R = true; // 如果没被消灭，本轮循环结束有R
          flag++;
        }
        if (senate[i] == 'D') {
          if (flag > 0) senate[i] = 0;
          else D = true;
          flag--;
        }
      }
    }
    // 循环结束之后，R和D只能有一个为true
    return R == true ? "Radiant" : "Dire";
  }

  /*
  public String predictPartyVictory(String senate) {
        int n = senate.length();
        Queue<Integer> radiant = new LinkedList<Integer>();
        Queue<Integer> dire = new LinkedList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (senate.charAt(i) == 'R') {
                radiant.offer(i);
            } else {
                dire.offer(i);
            }
        }
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int radiantIndex = radiant.poll(), direIndex = dire.poll();
            if (radiantIndex < direIndex) {
                radiant.offer(radiantIndex + n);
            } else {
                dire.offer(direIndex + n);
            }
        }
        return !radiant.isEmpty() ? "Radiant" : "Dire";
    }
   */
}
