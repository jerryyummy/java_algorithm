import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class TrieNode3 {
  HashMap<Character, TrieNode3> children = new HashMap<Character, TrieNode3>();
  String word = null;
  public TrieNode3() {}
}

public class Leetcode212 {
  char[][] _board = null;
  ArrayList<String> _result = new ArrayList<String>();

  public List<String> findWords(char[][] board, String[] words) {
    TrieNode3 root = new TrieNode3();
    for(String word:words){
      TrieNode3 node = root;
      for (int i = 0; i < word.length(); i++) {
        if (!node.children.containsKey(word.charAt(i))){
          TrieNode3 newNode = new TrieNode3();
          node.children.put(word.charAt(i),newNode);
          node = newNode;
        }else {
          node = node.children.get(word.charAt(i));
        }
      }
      node.word = word;
    }

    this._board = board;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (root.children.containsKey(board[i][j])) {
          dfs(i, j, root);
        }
      }
    }
    return this._result;
  }

  private void dfs(int row, int col, TrieNode3 parent) {
    Character letter = this._board[row][col];
    TrieNode3 currNode = parent.children.get(letter);
    // check if there is any match
    if (currNode.word != null) {
      this._result.add(currNode.word);
      currNode.word = null;
    }

    // mark the current letter before the EXPLORATION
    this._board[row][col] = '#';
    int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    for (int[] direction:directions) {
      int x = row + direction[0];
      int y = col + direction[1];
      if (x < 0 || x >= this._board.length || y < 0
          || y >= this._board[0].length) {
        continue;
      }
      if (currNode.children.containsKey(this._board[x][y])) {
        dfs(x, y, currNode);
      }
    }
    this._board[row][col] = letter;
    // Optimization: incrementally remove the leaf nodes
    if (currNode.children.isEmpty()) {
      parent.children.remove(letter);
    }
  }

}
