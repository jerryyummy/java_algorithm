package shopee;

//保持单词相对位置不变，其余非小写字母位置不变，如“Shoppee 1a3c abc”，应翻转为"Seepph 1c3a cba"，先用空格分割，再对单个单词进行处理。Java内置的String类封装的各种方法比较方便，并且拼接时注意使用StringBuilder提高性能

public class LowercaseLettersFlipped{
    /**
     * Note: 类名、方法名、参数名已经指定，请勿修改
     * <p>
     * <p>
     * 反转字符串中的小写字母，并且保证单个单词的顺序不变，其他字符的位子不变
     *
     * @param original_str string字符串
     * @return string字符串
     */
    public String reverses(String original_str) {
      // write code here
      String[] strings = original_str.split(" ");
      StringBuilder builder = new StringBuilder();
      for (String str : strings) {
        builder.append(getReverseLowStr(str));
        builder.append(" ");
      }
      builder.deleteCharAt(builder.length() - 1);
      return builder.toString();
    }

    private String getReverseLowStr(String str) {
      StringBuilder sb = new StringBuilder();
      StringBuilder strBuilder = new StringBuilder(str);
      for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        if (ch >= 'a' && ch <= 'z') {
          sb.append(ch);
        }
      }
      sb.reverse();
      String s = sb.toString();
      int point = 0;
      for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        if (ch >= 'a' && ch <= 'z') {
          strBuilder.setCharAt(i, s.charAt(point));
          point++;
        }
      }
      return strBuilder.toString();
    }

}
