package databrick.splitString;

import java.util.ArrayList;
import java.util.List;

public class Solution {
  public static List<String> solution(String panel, List<String> codes) {
    List<String> ans = new ArrayList<>();
    int n = codes.size();

    for (int i = 0; i < n; i++) {
      String code = codes.get(i);

      for (int j = 0; j < code.length()-1; j++) {
        int index = Integer.parseInt(code.substring(0, j + 1));
        String tar = code.substring(j + 1);

        if (index + tar.length() <= panel.length()) {
          if (tar.equals(panel.substring(index, index + tar.length()))) {
            ans.add(tar);
            continue; // 找到匹配项后跳出循环
          }
        }

        ans.add("not found"); // 如果没有找到匹配项，添加“not found”
      }

    }

    return ans;
  }

  public static void main(String[] args) {
    String panel = "2311453915";
    List<String> codes = new ArrayList<>();
    codes.add("0211");
    codes.add("639");

    List<String> res = solution(panel, codes);
    System.out.println(res);
  }
}
