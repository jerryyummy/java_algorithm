import java.util.Scanner;

public class Circular_competition_schedule {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.println("please enter a number:");
        int numberOfTeam=input.nextInt();
        int array[][]=new int[numberOfTeam+1][numberOfTeam+1];
        gameTable(numberOfTeam,array);

    }
    public static void gameTable(int numberOfTeam, int[][] array){
        int time= (int) (Math.log(numberOfTeam)/Math.log(2));//计算要循环的次数
        int n=2;//初始规模为2x2
        array[1][1]=1;array[1][2]=2;array[2][1]=2;array[2][2]=1;
        for (int t = 1; t < time; t++) {//依次处理2^t队伍的赛程
            int temp=n;//复制数组元素时的区间段
            n=n*2;//循环一次扩大一倍规模
            for (int i = temp+1; i <=n; i++) {//处理左下角元素
                for (int j = 1; j <=temp; j++) {
                    array[i][j]=array[i-temp][j]+temp;//左下角和右上角元素的对应关系
                }
            }
            for (int i = 1; i <=temp ; i++) {//处理右上角元素
                for (int j = temp+1; j <=n ; j++) {
                    array[i][j]=array[i+temp][(j-temp)];
                }
            }
            for (int i = temp+1; i <=n ; i++) {//处理右下角的元素
                for (int j = temp+1; j <=n ; j++) {
                    array[i][j]=array[i-temp][j-temp];
                }
            }

        }

        printTable(array,numberOfTeam);
    }
    public static void printTable(int[][] array, int numberOfTeam){
        for (int i = 1; i <=numberOfTeam; i++) {
            for (int j = 1; j <=numberOfTeam; j++) {
                System.out.print(array[i][j]+" ");
            }
            System.out.println("");
        }
    }
}
