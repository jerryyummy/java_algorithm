import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Leetcode140 {
  List<String> res = new ArrayList<>();

  public List<String> wordBreak(String s, List<String> wordDict) {
    HashSet<String> set = new HashSet<>(wordDict);
    dfs(s, new ArrayList<>(), set);
    return res;
  }

  public void dfs(String s, List<String> cur, Set<String> set) {
    if (s.isEmpty()) {
      res.add(cur.stream().collect(Collectors.joining(" ")));
      return;
    }
    for (int i = 1; i <= s.length(); i++) {
      if (set.contains(s.substring(0, i))) {
        // 直接添加当前匹配的单词到cur列表，而不是创建新的temp
        List<String> nextCur = new ArrayList<>(cur);
        nextCur.add(s.substring(0, i));
        dfs(s.substring(i), nextCur, set); // 继续搜索剩余部分
      }
    }
  }
}
