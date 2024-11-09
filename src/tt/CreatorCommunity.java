package tt;

import java.util.List;

public class CreatorCommunity {
  public static int countCreatorCommunities(List<String> related) {
    int n = related.size();
    boolean[] visited = new boolean[n];
    int communityCount = 0;

    // 遍历每个 creator，查找未访问过的，并启动 DFS
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        communityCount++;
        dfs(related, visited, i);
      }
    }

    return communityCount;
  }

  private static void dfs(List<String> related, boolean[] visited, int creator) {
    visited[creator] = true;
    for (int i = 0; i < related.size(); i++) {
      // 如果当前 creator 和 i 是连接的且未访问过，则递归调用
      if (related.get(creator).charAt(i) == '1' && !visited[i]) {
        dfs(related, visited, i);
      }
    }
  }

}
