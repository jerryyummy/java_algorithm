public class Leetcode3234 {
//  public int numberOfSubstrings(String s) {
//    int ans = 0;
//    for (int zero = 0;zero*zero<s.length(); zero++) {
//      int lastPos = -1;
//      int[] count = new int[2];
//      int left = 0, right = 0;
//      while (right < s.length()) {
//        count[s.charAt(right)-'0']++;
//        while (left<right){
//          if (s.charAt(left) == '0' && count[0]>zero){
//            count[0]--;
//            lastPos = left;
//          }else if(s.charAt(left)=='1' && count[1]-1 >= zero*zero){
//            count[1]--;
//          }else{
//            break;
//          }
//          left++;
//        }
//        if (count[0] == zero && count[1] >= zero*zero){
//          ans+= (left-lastPos);
//        }
//        right++;
//      }
//    }
//    return ans;
//  }

  public int numberOfSubstrings(String S) {
    char[] s = S.toCharArray(); // 将字符串转换为字符数组
    int n = s.length; // 获取字符串长度
    int m = 0; // 初始化 `'0'` 的数量
    int[] a = new int[n + 1]; // 创建数组用于存储 `'0'` 的位置

    // 找出所有 `'0'` 的位置
    for (int i = 0; i < n; i++) {
      if (s[i] == '0') {
        a[m++] = i; // 存储 `'0'` 的位置，并更新 `'0'` 的数量
      }
    }

    int tot1 = n - m; // 计算 `'1'` 的总数
    a[m] = n; // 添加哨兵，方便后续处理

    int ans = 0; // 初始化答案

    // 遍历每个可能的左端点
    int i = 0; // 初始化 `'0'` 的索引
    for (int left = 0; left < n; left++) {
      if (s[left] == '1') {
        ans += a[i] - left; // 如果左端点为 `'1'`，计算符合条件的子串数量
      }

      // 对于每个 `'0'` 的位置，检查 `'1'` 的数量是否满足条件
      for (int k = i; k < m; k++) {
        int cnt0 = k - i + 1; // 当前 `'0'` 的数量
        if (cnt0 * cnt0 > tot1) {
          break; // 如果 `'0'` 的数量平方大于 `'1'` 的总数，则停止检查
        }
        int cnt1 = a[k] - left - (k - i); // 当前 `'1'` 的数量
        ans += Math.max(a[k + 1] - a[k] - Math.max(cnt0 * cnt0 - cnt1, 0), 0); // 计算符合条件的子串数量
      }

      if (s[left] == '0') {
        i++; // 如果左端点为 `'0'`，更新 `'0'` 的索引
      }
    }

    return ans; // 返回答案
  }

}
