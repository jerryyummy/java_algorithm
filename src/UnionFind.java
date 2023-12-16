public class UnionFind {
    int[] parent;
    int[] rank;

    public UnionFind(int n){
        this.parent = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int n){
        while (parent[n]!=n){
            n = parent[n];
        }
        return parent[n];
    }

    public boolean union(int a, int b){
        if(parent[a]==parent[b]){
            return false;
        }else if (rank[a]>rank[b]){
            parent[b] = a;
        }else if (rank[a]<rank[b]){
            parent[a] = b;
        }else{
            parent[b] = a;
            rank[a]+=1;
        }
        return true;
    }
}
