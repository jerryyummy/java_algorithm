import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import javafx.util.Pair;

class TrieNode1 {
  TrieNode1[] children = new TrieNode1[26];
  boolean isWord;
  public TrieNode1() {}
}

public class Leetcode720 {
  public String longestWord(String[] words) {
    TrieNode1 root = new TrieNode1();

    for (String word:words){
      TrieNode1 temp = root;
      for (char c:word.toCharArray()){
        if (temp.children[c-'a'] == null){
          temp.children[c-'a'] = new TrieNode1();
          temp = temp.children[c-'a'];
        }else {
          temp = temp.children[c-'a'];
        }
      }
      temp.isWord = true;
    }

    Queue<Pair<TrieNode1,String>> queue = new LinkedList<>();
    queue.add(new Pair<>(root,""));
    String res = "";
    while (!queue.isEmpty()){
      TrieNode1 temp = queue.peek().getKey();
      String s = queue.peek().getValue();
      queue.poll();
      res = s;
      for (int i = 25; i >= 0; i--) {
        if (temp.children[i]!=null && temp.children[i].isWord){
          queue.add(new Pair<>(temp.children[i],s+(char)('a'+i)));
        }
      }
    }

    return res;
  }

  public static void main(String[] args) {
    Leetcode720 solution = new Leetcode720();
    System.out.println(solution.longestWord(new String[]{"a","banana","app","appl","ap","apply","apple"}));
  }

}
