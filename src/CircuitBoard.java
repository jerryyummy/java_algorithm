import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
     最短电路板排列
*/
public class CircuitBoard {

    private static class BoardNode{
        int[] x;
        int s,cd;
        int[] low,high;

        private int len(){
            int tmp = 0;
            for(int k=1; k<=m; k++)
                if(low[k]<=n && high[k]>0 && tmp<high[k]-low[k])
                    tmp = high[k]-low[k];

            return tmp;
        }
    }

    private static int n,m;
    private static int[] bestx;
    private static int[][] B;

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        while (true){
            n = input.nextInt();
            m = input.nextInt();

            B = new int[n+1][m+1];
            bestx =new int[n+1];

            for(int i=1; i<=n; i++)
                for(int j=1; j<=m; j++)
                    B[i][j] = input.nextInt();

            int bestd = FIFOBoards();

            System.out.println(bestd);
            for(int i=1; i<=n; i++)
                System.out.print(bestx[i]+" ");
        }
    }

    private static int FIFOBoards(){
        Queue<BoardNode> Q = new LinkedList<>();
        BoardNode E = new BoardNode();
        E.x = new int[n+1];
        E.s=0; E.cd=0; E.low=new int[m+1]; E.high=new int[m+1];
        for(int i=1; i<=m; i++){E.high[i]=0; E.low[i]=n+1;}
        for(int i=1; i<=n; i++) E.x[i]=i;
        int bestd = n+1;
        bestx = null;
        while (true){
            if(E.s == n-1){
                for(int j=1; j<=m; j++)
                    if(B[E.x[n]][j]>0 && n>E.high[j]) E.high[j]=n;
                int ld=E.len();
                if(ld < bestd){
                    bestx = E.x;
                    bestd = ld;
                }
            }else {
                int curr = E.s+1;
                for(int i=E.s+1; i<=n; i++){
                    BoardNode N = new BoardNode();
                    N.low = new int[m+1];
                    N.high = new int[m+1];
                    for(int j=1; j<=m; j++){
                        N.low[j] = E.low[j]; N.high[j]=E.high[j];
                        if(B[E.x[i]][j] > 0){
                            if(curr < N.low[j]) N.low[j]=curr;
                            if(curr > N.high[j]) N.high[j]=curr;
                        }
                    }
                    N.cd = N.len();
                    if(N.cd < bestd){
                        N.x = new int[n+1];
                        N.s = E.s+1;
                        for(int j=1; j<=n; j++) N.x[j]=E.x[j];
                        N.x[N.s] = E.x[i];
                        N.x[i] = E.x[N.s];
                        Q.add(N);
                    }
                }
            }
            if(Q.isEmpty()) break;
            else{
                E = Q.poll();
            }
        }

        return bestd;
    }
}
