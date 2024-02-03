public class Leetcode2158 {
  public int[] amountPainted(int[][] paint) {
    int[] line = new int[50001];//记录每个点到最远哪个点已经涂了
    int[] amountPainted = new int[paint.length];
    for (int day = 0; day < paint.length; day++) {
      int start = paint[day][0], end = paint[day][1], amount = 0;
      while (start < end) {
        int nextJump = Math.max(start + 1, line[start]);//查看当前点最远涂到的地方
        amount += line[start] == 0 ? 1 : 0;//如果还没有涂过，加一，说明下一步到start+1
        line[start] = Math.max(line[start], end);//更新最远涂到的距离
        start = nextJump;
      }
      amountPainted[day] = amount;
    }
    return amountPainted;
  }

}
