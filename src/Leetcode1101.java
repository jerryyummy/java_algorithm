import java.util.Arrays;

public class Leetcode1101 {
    public int earliestAcq(int[][] logs, int n) {

        Arrays.sort(logs, (a,b)->a[0]-b[0]);
        int groupCount = n;
        UnionFind1 uf = new UnionFind1(n);

        for (int[] log : logs) {
            int timestamp = log[0], friendA = log[1], friendB = log[2];
            if (uf.union(friendA, friendB)) {
                groupCount -= 1;
            }
            if (groupCount == 1) {
                return timestamp;
            }
        }
        return -1;
    }
}

class UnionFind1 {
    private int[] group;
    private int[] rank;

    public UnionFind1(int size) {
        this.group = new int[size];
        this.rank = new int[size];
        for (int person = 0; person < size; ++person) {
            this.group[person] = person;
            this.rank[person] = 0;
        }
    }

    public int find(int person) {
        if (this.group[person] != person)
            this.group[person] = this.find(this.group[person]);
        return this.group[person];
    }

    public boolean union(int a, int b) {
        int groupA = this.find(a);
        int groupB = this.find(b);
        boolean isMerged = false;

        if (groupA == groupB)
            return isMerged;

        isMerged = true;
        if (this.rank[groupA] > this.rank[groupB]) {
            this.group[groupB] = groupA;
        } else if (this.rank[groupA] < this.rank[groupB]) {
            this.group[groupA] = groupB;
        } else {
            this.group[groupA] = groupB;
            this.rank[groupB] += 1;
        }
        return isMerged;
    }
}
