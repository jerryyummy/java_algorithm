import java.util.Scanner;

/*
     象棋马走日路径
*/
public class HorseOfChess {

    public static void main(String[] args) {
        // 注意(0<=x<n && 0<=y<m)
        Scanner input=new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int x = 0;
        int y = 0;
        HorseOfChess rs = new HorseOfChess(n, m, x, y);
        rs.find(x, y, 2);
        if (count==0){
            System.out.println("impossible");
        }
    }

    // 棋盘行数
    private int n;
    // 棋盘列数
    private int m;
    // 马的起始x坐标
    private int x;
    // 马的起始y坐标
    private int y;
    // 棋盘坐标
    private int[][] a;
    // 求解总数
    static private int count;
    // "日"字x坐标
    public int[] fx = { 1, 2, 2, 1, -1, -2, -2, -1 };
    // "日"字y坐标
    public int[] fy = { 2, 1, -1, -2, -2, -1, 1, 2 };

    public HorseOfChess(int _n, int _m, int _x, int _y) {
        n = _n;
        m = _m;
        x = _x;
        y = _y;
        a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = 0;
            }
        }
        // 马的起点
        a[x][y] = 1;
        count = 0;
    }

    public void find(int x, int y, int dep) {
        int i, xx, yy;
        for (i = 0; i < 7; i++) {
            xx = x + fx[i];
            yy = y + fy[i];
            // 判断新坐标是否出界，是否已走过
            if (check(xx, yy) == 1) {
                a[xx][yy] = dep;
                if (dep == n * m) {
                    output();
                } else {
                    // 从新坐标出发，递归下一层
                    find(xx, yy, dep + 1);
                }
                // 回溯，恢复未走标志
                a[xx][yy] = 0;
            }
        }
    }

    /**
     * 判断新坐标是否出界，是否已走过
     */
    public int check(int xx, int yy) {
        if (xx >= n || yy >= m || xx < 0 || yy < 0 || a[xx][yy] != 0) {
            return 0;
        }
        return 1;
    }

    /**
     * 输出结果
     */
    public void output() {
        count++;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                System.out.print(a[y][x] + " ");
            }
        }
        System.out.println("");
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}