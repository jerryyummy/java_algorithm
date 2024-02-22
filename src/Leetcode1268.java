import java.util.ArrayList;
import java.util.List;

class TrieNode2 {
  TrieNode2[] children = new TrieNode2[26];
  boolean isProduct;
  String word;
  public TrieNode2() {}
}

public class Leetcode1268 {
  TrieNode2 root = new TrieNode2();
  public List<List<String>> suggestedProducts(String[] products, String searchWord) {
    List<List<String>> res = new ArrayList<>();
    for (String product:products){
      TrieNode2 temp = root;
      for (char c:product.toCharArray()){
        if(temp.children[c-'a']==null){
          temp.children[c-'a'] = new TrieNode2();
        }
        temp = temp.children[c-'a'];
      }
      temp.isProduct = true;
      temp.word = product;
    }
    for (char c:searchWord.toCharArray()){
      if (root !=null) root = root.children[c-'a'];
      res.add(find(root));
    }
    return res;
  }

  public List<String> find(TrieNode2 node) {
    List<String> res = new ArrayList<>();
    findHelper(node, res);
    return res;
  }

  private void findHelper(TrieNode2 node, List<String> res) {
    if (node == null) return; // 如果节点为空，直接返回
    if (res.size() >= 3) return; // 如果已经找到3个产品，就停止搜索
    if (node.isProduct) {
      res.add(node.word); // 如果当前节点是一个产品，添加它
    }
    for (int i = 0; i < 26; i++) { // 按字典序遍历子节点
      if (node.children[i] != null) {
        findHelper(node.children[i], res);
      }
    }
  }
}
