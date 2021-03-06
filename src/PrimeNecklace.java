import java.util.Scanner;

public class PrimeNecklace {

    //判断某整数是不是质数
    public static boolean isp(int n){
        for(int i = 2; i < Math.sqrt(n)+1;i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    public static int n;
    public static boolean[] vis = new boolean[20];//存储某数是否被使用
    public static int[] arr = new int[20];//存放素数序列

    public static void dfs(int cur){
        if(cur == n+1 && isp(arr[1]+arr[n])){//如果最后一个数放进去了，并且最后一个数和第一个的和为质数，并且第一个数是1（因为我们只输出开头是1的，避免重复）
            for(int i = 1; i <= n; i++){//打印出数列
                System.out.print(arr[i]+" ");
            }
            System.out.println();
        }else{
            for(int i = 1; i <= n; i++){//尝试放置每个数i
                if(!vis[i] && isp(i+arr[cur-1])){//如果这个数没有被使用，并且与前一个数的和是质数
                    vis[i] = true;//标明这个数被使用
                    arr[cur] = i;//将这个数加入队列
                    dfs(cur+1);//求下一个数
                    vis[i] = false;//取消这个数
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        n=input.nextInt();
        arr[0] = 0;
        for(int i = 0; i <= n;i++){
            vis[i] = false;//初始化所有数字都没有使用
        }
        dfs(1);//查找第一个数字
    }
}

