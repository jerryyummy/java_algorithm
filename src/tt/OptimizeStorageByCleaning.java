package tt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OptimizeStorageByCleaning {
  public static List<String> optimizeStorageByCleaning(List<String> forYouList, List<String> followingList) {
    int size = forYouList.size();
    List<String> result = new ArrayList<>(Collections.nCopies(size, ""));

    for (int i = 0; i < size; i++) {
      Set<Character> forYouChars = new HashSet<>();
      Set<Character> followingChars = new HashSet<>();

      for (char c : forYouList.get(i).toCharArray()) {
        forYouChars.add(c);
      }

      for (char c : followingList.get(i).toCharArray()) {
        followingChars.add(c);
      }

      forYouChars.retainAll(followingChars);

      result.set(i, !forYouChars.isEmpty() ? "YES" : "NO");
    }

    return result;
  }

}
