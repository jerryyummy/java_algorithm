import java.util.*;

class LockingTree {

    private final int n;
    private final int[] parent;
    private final List<Integer>[] children;

    // 欧拉序
    private final int[] tin, tout, nodeAtTin;
    private int timer = 0;

    // 锁状态
    private final int[] lockedBy;          // -1 表示未锁，否则为 user
    private final TreeSet<Integer> lockedTins = new TreeSet<>(); // 存已锁节点的 tin

    @SuppressWarnings("unchecked")
    public LockingTree(int[] parent) {
        this.n = parent.length;
        this.parent = parent;
        this.children = new ArrayList[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) children[parent[i]].add(i);

        this.tin = new int[n];
        this.tout = new int[n];
        this.nodeAtTin = new int[n];
        this.lockedBy = new int[n];
        Arrays.fill(lockedBy, -1);

        dfs(0); // 题目规定 root 的 parent 为 -1
    }

    private void dfs(int u) {
        tin[u] = timer;
        nodeAtTin[timer] = u;
        timer++;
        for (int v : children[u]) dfs(v);
        tout[u] = timer - 1; // 子树闭区间 [tin[u], tout[u]]
    }

    public boolean lock(int num, int user) {
        if (lockedBy[num] != -1) return false;
        lockedBy[num] = user;
        lockedTins.add(tin[num]);
        return true;
    }

    public boolean unlock(int num, int user) {
        if (lockedBy[num] != user) return false;
        lockedBy[num] = -1;
        lockedTins.remove(tin[num]);
        return true;
    }

    public boolean upgrade(int num, int user) {
        // 1) 自己未被锁
        if (lockedBy[num] != -1) return false;

        // 2) 祖先都未被锁
        for (int p = parent[num]; p != -1; p = parent[p]) {
            if (lockedBy[p] != -1) return false;
        }

        // 3) 至少有一个被锁的子孙
        NavigableSet<Integer> sub = lockedTins.subSet(tin[num], true, tout[num], true);
        if (sub.isEmpty()) return false;

        // 4) 解锁所有被锁子孙（只遍历被锁的几点，避免整棵子树）
        List<Integer> toRemove = new ArrayList<>(sub); // 先拷贝，避免并发修改
        for (int t : toRemove) {
            int v = nodeAtTin[t];
            lockedBy[v] = -1;
            lockedTins.remove(t);
        }

        // 5) 锁定当前节点
        lockedBy[num] = user;
        lockedTins.add(tin[num]);
        return true;
    }
}
