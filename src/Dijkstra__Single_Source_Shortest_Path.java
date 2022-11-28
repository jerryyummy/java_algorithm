import java.util.Scanner;
public class Dijkstra__Single_Source_Shortest_Path {

    private static int N;//节点
    private static int max;//最大权重，表示无法联通
    private static int[] visit;//找到一个顶点的最短距离，就把它设为1，默认为0
    private static int[][] distance;//保存图中个边的值，两点间无边则设为max
    private static int[] minDistance;//保存源点到其他各点的最短距离
    private static String[] path;//保存输出路径
    private static int sum=0;//记录总的路径长度

    public static void Dijkstra() {
        visit[1] = 1;//源点到自己长度为0
        minDistance[1] = 0;

        //循环每个节点
        for(int l = 2; l <= N; l++) {
            int Dtemp = max;//最短路径初始化为最大值
            int k = -1;//k表示当前计算最短路径的节点

            //在一个循环内找到距离最短的点
            for(int i = 2; i <= N; i++) {
                if(visit[i] == 0 && distance[1][i] < Dtemp) {
                    Dtemp = distance[1][i];//更新权重长度
                    k = i;
                }
            }
            visit[k] = 1;//找到拥有最短路径的边
            minDistance[k] = Dtemp;//保存对应最短距离

            //以上一个循环找到的点为中心，通过一个循环更新所有visit[i]为0的点到源点的距离
            for(int i = 2; i <= N; i++) {
                if(visit[i] == 0 && (distance[1][k] + distance[k][i]) < distance[1][i]) {//如果源点到该点最短距离还没有找到而且找到该点的更短路径
                    distance[1][i] = distance[1][k] + distance[k][i];
                    path[i] = path[k] + "-->" + i;//更新路径上的节点
                }
            }
        }

        //输出路径
        for(int i=1;i<=N;i++) {
            System.out.println("从"+1+"出发到"+i+"的最短路径为："+path[i]);
        }
        System.out.println("=====================================");
        for(int i = 1; i <= N; i++) {
            System.out.println("从1出发到" + i + "点的最短距离为：" + minDistance[i]);
        }
        for (int i = 1; i <= N; i++) {
            sum+=minDistance[i];
        }
        System.out.println(sum);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Scanner input = new Scanner(System.in);
        System.out.print("请输入节点个数N，边总数M： ");
        N = input.nextInt();
        //边
        int m = input.nextInt();
        max = 10000;
        minDistance = new int[N+1];
        distance = new int [N+1][N+1];
        visit = new int[N+1];
        path=new String[N+1];

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i == j) {
                    distance[i][j] = 0;
                }else {
                    distance[i][j] = max;//默认边的权重为无穷
                }
            }
            minDistance[i] = max;//默认源点到其他点最短距离为无穷
            path[i] = "1-->" + i;
        }

        System.out.println("请输入" + m +"条数据x，y，z（表示x点到y点的距离为z）：");
        for(int i = 1; i <= m; i++) {
            int start = input.nextInt();
            int end = input.nextInt();
            int length = input.nextInt();
            distance[start][end] = length;
        }
        input.close();

        Dijkstra();
    }
}
