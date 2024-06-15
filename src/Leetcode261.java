public class Leetcode261 {
    private int find(int[] representative, int vertex) {
        while (vertex != representative[vertex]) {
            vertex = representative[vertex];
        }

        return representative[vertex];
    }

    private boolean combine(int[] representative, int[] size, int vertex1, int vertex2) {
        vertex1 = find(representative, vertex1);
        vertex2 = find(representative, vertex2);

        if (vertex1 == vertex2) {
            return false;
        } else {
            if (size[vertex1] > size[vertex2]) {
                size[vertex1] += size[vertex2];
                representative[vertex2] = vertex1;
            } else {
                size[vertex2] += size[vertex1];
                representative[vertex1] = vertex2;
            }
            return true;
        }
    }


    public boolean validTree(int n, int[][] edges) {
        if(edges.length<n-1) return false;
        int[] representative = new int[n];
        int[] size = new int[n];

        for (int i = 0; i < n; i++) {
            representative[i] = i;
            size[i] = 0;
        }

        for (int i = 0; i < edges.length; i++) {
            if(!combine(representative, size, edges[i][0], edges[i][1])) return false;
        }

        return true;
    }
}
