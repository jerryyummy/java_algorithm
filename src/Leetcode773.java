import java.util.HashMap;
import java.util.HashSet;

public class Leetcode773 {
  // 3-Bit Binary representation of 123450
  private static final int FINAL_STATE = 0b001010011100101000;
  // Possible destination of zero. Here DIRS[0] represents the most significant
  // 3-Bit number in the state. And, DIRS[5] represents the least significant
  // 3-Bit number in the state.
  private static final int[][] DIRS = { { 1, 3 }, { 0, 2, 4 }, { 1, 5 }, { 0, 4 }, { 1, 3, 5 }, { 2, 4 } };

  public int slidingPuzzle(int[][] board) {
    int zeroIdx = -1;
    int curState = 0;
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        // Inserting the num at the end of integer
        curState = (curState << 3) | board[i][j];
        if (board[i][j] == 0) {
          zeroIdx = i * 3 + j;
        }
      }
    }

    if (FINAL_STATE == curState) {
      return 0;
    }

    HashSet<Integer> visited = new HashSet<>();
    int moves = 0;

    HashMap<Integer, Integer> begin = new HashMap<>();
    begin.put(curState, zeroIdx);
    visited.add(curState);

    HashMap<Integer, Integer> end = new HashMap<>();
    end.put(FINAL_STATE, 5);
    visited.add(FINAL_STATE);

    while (!begin.isEmpty()) {//只要begin这个哈希映射不为空，就继续搜索。当begin为空时，表示没有更多的状态可以探索，搜索失败
      if (begin.size() > end.size()) {//这是双端BFS的一个优化，目的是总是扩展较小的集合，以减少搜索空间
        HashMap<Integer, Integer> tempSet = begin;
        begin = end;
        end = tempSet;
      }

      HashMap<Integer, Integer> next = new HashMap<>();//创建一个新的哈希映射next，用于存储下一轮的状态
      moves++;
      for (int cur : begin.keySet()) {
        zeroIdx = begin.get(cur);

        for (int d : DIRS[zeroIdx]) {
          int newState = swap(cur, zeroIdx, d);
          if (end.containsKey(newState)) {//如果新状态在end集合中，意味着找到了从初始状态到目标状态的路径，返回移动次数
            return moves;
          }
          if (visited.add(newState)) {//如果新状态未被访问过，则添加到visited集合和next映射中
            next.put(newState, d);
          }
        }
      }
      begin = next;//将next映射设置为下一轮迭代的begin，继续搜索
    }

    return -1;
  }

  private int swap(int state, int zeroIdx, int destIdx) {//在给定状态中交换'0'瓦片和目标索引位置上的瓦片
    // Generate mask for destIdx
    int mask = 0b111 << ((5 - destIdx) * 3);//生成了一个掩码，用于提取位于destIdx位置的数字
    //使用掩码与状态进行位与操作，以提取位于destIdx位置的数字
    int num = state & mask;
    // 根据zeroIdx和destIdx的相对位置，将提取的数字左移或右移相应的位数，以将其移动到'0'瓦片的位置
    if (zeroIdx < destIdx) {
      num <<= (destIdx - zeroIdx) * 3;
    } else {
      num >>>= (zeroIdx - destIdx) * 3;
    }

    // Setting zero at destIdx
    state &= ~mask;
    // Setting num at zeroIdx
    return state | num;
  }
}
