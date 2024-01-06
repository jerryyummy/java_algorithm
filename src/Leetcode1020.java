public class Leetcode1020 {
    int[][] grid;
    int m;
    int n;
    public int numEnclaves(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) sum++;
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i==0 || i==m-1 || j==0 || j==n-1 &&grid[i][j] == 1){
                    res+=dfs(i,j);
                }
            }
        }
        return sum-res;
    }

    public int dfs(int x, int y){
        if (x<0 || x>=m || y<0 || y>=n) return 0;
        else if (grid[x][y] == 0) return 0;
        else{
            grid[x][y]  = 0;
            return 1+dfs(x+1,y)+dfs(x-1,y)+dfs(x,y+1)+dfs(x,y-1);
        }
    }
}
