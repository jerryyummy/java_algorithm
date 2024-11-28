class Leetcode1898 {
    public boolean isSubsequence(String a, String b) {
        int i = 0;
        int j = 0;
        while (i < a.length() && j < b.length()) {
            if (a.charAt(i) == b.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == a.length();
    }

    // 检查在移除指定索引的字符后，p是否是s的子序列
    public boolean check(String s, String p, int[] v, int mid) {
        boolean[] removed = new boolean[s.length()];
        for (int i = 0; i < mid; i++) {
            removed[v[i]] = true;
        }

        StringBuilder filteredS = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!removed[i]) {
                filteredS.append(s.charAt(i));
            }
        }

        return isSubsequence(p, filteredS.toString());
    }

    // 找出最多能移除多少个字符使得p仍是s的子序列
    public int maximumRemovals(String s, String p, int[] v) {
        int n = v.length;
        int st = 0;
        int e = n;
        while (st <= e) {
            int mid = st + (e - st) / 2;
            if (check(s, p, v, mid)) {
                st = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return e;
    }
}
