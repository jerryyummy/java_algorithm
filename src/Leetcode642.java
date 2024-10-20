import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode642 {

}

class TrieNode1 {
  Map<Character, TrieNode1> children;
  Map<String, Integer> sentences;
  public TrieNode1() {
    children = new HashMap<>();
    sentences = new HashMap<>();
  }
}

class AutocompleteSystem {
  TrieNode1 root;
  TrieNode1 currNode;
  TrieNode1 dead;
  StringBuilder currSentence;

  public AutocompleteSystem(String[] sentences, int[] times) {
    root = new TrieNode1();
    for (int i = 0; i < sentences.length; i++) {
      addToTrie(sentences[i], times[i]);
    }

    currSentence = new StringBuilder();
    currNode = root;
    dead = new TrieNode1();
  }

  public List<String> input(char c) {
    if (c == '#') {
      addToTrie(currSentence.toString(), 1);
      currSentence.setLength(0);
      currNode = root;
      return new ArrayList<String>();
    }

    currSentence.append(c);
    if (!currNode.children.containsKey(c)) {
      currNode = dead;
      return new ArrayList<String>();
    }

    currNode = currNode.children.get(c);
    List<String> sentences = new ArrayList<>(currNode.sentences.keySet());
    Collections.sort(sentences, (a, b) -> {
      int hotA = currNode.sentences.get(a);
      int hotB = currNode.sentences.get(b);
      if (hotA == hotB) {
        return a.compareTo(b);
      }

      return hotB - hotA;
    });

    List<String> ans = new ArrayList<>();
    for (int i = 0; i < Math.min(3, sentences.size()); i++) {
      ans.add(sentences.get(i));
    }

    return ans;
  }

  private void addToTrie(String sentence, int count) {
    TrieNode1 node = root;
    for (char c: sentence.toCharArray()) {
      if (!node.children.containsKey(c)) {
        node.children.put(c, new TrieNode1());
      }

      node = node.children.get(c);
      node.sentences.put(sentence, node.sentences.getOrDefault(sentence, 0) + count);
    }
  }
}