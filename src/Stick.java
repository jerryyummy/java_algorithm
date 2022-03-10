import java.util.Scanner;
/*
     木棍复原
*/
public class Stick{

    public static int g;//每组拼接完毕后，所拼接好的木棒数
    public static int len;//每组满足要求的拼接后的木棒的长度，每组木棒总长度 = g * len；

    /*
      深度搜索
      a:每组木棒的长度数组
      visited:为数组中每一个木棒长度设置一个访问标志，默认初值为0
      n:表示每组木棒数组的长度
      nowlen:表示当前已拼接成的木棒的长度
      nowget：表示现在拼接成的木棒的总数，当nowGet = g时找到符合要求的木棒长
      cnt：拼接过程中查找剩下符合要求的木棒应该从哪个下标开始查找
     */
    public static boolean dfs(int[] a,int[] visited,int n,int nowlen,int nowget,int cnt){
        if(cnt >= n) return false; //下标超过木棒总数n时，显然不满足
        if(nowget == g) return true;//当前长度下获取的总个数与g相等说明符合条件

        //开始遍历查找
        for (int i = cnt; i < n; i++) {
            //访问没有被用过的木棒
            if(visited[i] == 0){
                //当加入当前的木棒后，恰好能拼接成需要的长度时
                if(nowlen + a[i] == len){
                    visited[i] = 1;//标记当前木棒已被使用
                    //在此组满足后，进行下一组的寻找，此时下一组的nowlen=0，nowget=nowget+1;
                    if(dfs(a,visited,n,0,nowget+1,nowget)){
                        //递归，直到最后一组都满足需要的长度时说明查找成功
                        return true;
                    }
                    //查找失败，说明此木棒不可选
                    visited[i] = 0;//将visited重新置为0，进行回溯
                    return false;
                }
                //当找到的一组木棒还小于拼接成需要的长度时
                if(nowlen + a[i] < len){
                    visited[i] = 1;//标记当前木棒已被使用
                    //接着递归，查找下一个，即从i的下一个i+1开始
                    if(dfs(a,visited,n,nowlen+a[i],nowget,i+1)){
                        //在当前满足的基础上，依次向后递归
                        return true;//查找成功
                    }
                    //查找失败
                    visited[i] = 0;//回溯
                    //剪枝，在当前搜索时，前面长度为0，并且第一根没有被成功使用，说明第一跟始终要被放弃的，所以这种组合不会成功
                    if(nowlen == 0) return false;
                    //如果有一根木棒加上去已经知道不满足要求，则与它同长度的木棒都可以跳过
                    for ( ;a[i] == a[i+1] && i + 1 < n;i++);
                }
            }
        }
        return false;
    }

    //由大到小排序
    public static void sort(int[] arr,int num){
        int temp;
        for (int i = 0; i < num-1; i++) {
            for (int j = i+1; j < num; j++) {
                if(arr[i] < arr[j]){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int[][] maxti = new int[100][100];//用于存储输入每组木棒的长度
        int[] sumArr = new int[100];//用于存储每组木棒的总长度
        int[] eachCounts = new int[100];//用于存储每组木棒的数量
        int[] a = new int[100];//用与存储每组木棒的长度序列
        int n;//每组木棒总数

        //用户输入每组的木棒数
        int inc = 0;//设置自增变量，便于去二维数组maxti中取值
        int sum;//用于计算每组木棒总长的中间变量
        while(true){
            n = input.nextInt();//每组木棒的总个数
            eachCounts[inc] = n;
            if(n == 0){//输入为0时，结束输入
                break;
            }
            sum = 0;
            for (int i = 0; i < n; i++) {
                maxti[inc][i] = input.nextInt();//二维数组maxti的每一行表示一组数据
                sum += maxti[inc][i];
            }
            sumArr[inc] = sum;//存储每一组数据的总和
            inc++;
        }

        //对输入的每组木棒序列进行递归搜索
        for (int i = 0; i < inc; i++) {//对二维数组一行一行访问
            int[] visited = new int[100];//设置每组木棒的访问标志量
            for (int j = 0; j < eachCounts[i]; j++) {
                a[j] = maxti[i][j];
            }

            sort(a,eachCounts[i]);//从大到小排序得到的序列，a[0]显然是序列中的最大值
            for (len = a[0];len <= sumArr[i];len++) {//对原木棒可能的长度进行遍历查找
                if(sumArr[i] % len != 0) continue;//有题意可知，最后选的木棒长度一定是能被总木棒长度整除的

                g = sumArr[i] / len;//拼接后的木棒数
                //剪枝，满足要求退出循环
                if(dfs(a,visited,eachCounts[i],0,0,0)){
                    break;
                }
            }
            //输出满足要求得木棒长
            System.out.println(len);
        }
    }
}