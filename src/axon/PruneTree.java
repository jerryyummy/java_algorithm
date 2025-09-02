package axon;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int v) { val = v; }
}

public class PruneTree {
    public List<Integer> prune(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        // 1) 计算每个结点的高度（后序，迭代以避免深递归）
        Map<TreeNode, Integer> height = new HashMap<>();
        Deque<TreeNode> s1 = new ArrayDeque<>(), s2 = new ArrayDeque<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            TreeNode x = s1.pop();
            s2.push(x);
            if (x.left  != null) s1.push(x.left);
            if (x.right != null) s1.push(x.right);
        }
        while (!s2.isEmpty()) {
            TreeNode x = s2.pop();
            int hl = x.left  == null ? 0 : height.get(x.left);
            int hr = x.right == null ? 0 : height.get(x.right);
            height.put(x, Math.max(hl, hr) + 1);
        }

        // 2) 中序编号：确定“左右”顺序
        Map<TreeNode, Integer> inIdx = new HashMap<>();
        Deque<TreeNode> st = new ArrayDeque<>();
        TreeNode cur = root;
        int idx = 0;
        while (cur != null || !st.isEmpty()) {
            while (cur != null) {
                st.push(cur);
                cur = cur.left;
            }
            TreeNode x = st.pop();
            inIdx.put(x, idx++);
            cur = x.right;
        }

        // 3) 大根堆：高度高者先；并列取更靠左（中序下标小）
        PriorityQueue<TreeNode> pq = new PriorityQueue<>((a, b) -> {
            int ha = height.get(a), hb = height.get(b);
            if (ha != hb) return hb - ha;                // 高度降序
            return inIdx.get(a) - inIdx.get(b);          // 左到右
        });

        pq.offer(root);
        while (!pq.isEmpty()) {
            TreeNode x = pq.poll();
            ans.add(x.val);               // 修剪顺序
            if (x.left  != null) pq.offer(x.left);
            if (x.right != null) pq.offer(x.right);
        }
        return ans;
    }
}
