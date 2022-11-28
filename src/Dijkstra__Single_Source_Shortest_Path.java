import java.util.Scanner;
public class Dijkstra__Single_Source_Shortest_Path {

    private static int N;//�ڵ�
    private static int max;//���Ȩ�أ���ʾ�޷���ͨ
    private static int[] visit;//�ҵ�һ���������̾��룬�Ͱ�����Ϊ1��Ĭ��Ϊ0
    private static int[][] distance;//����ͼ�и��ߵ�ֵ��������ޱ�����Ϊmax
    private static int[] minDistance;//����Դ�㵽�����������̾���
    private static String[] path;//�������·��
    private static int sum=0;//��¼�ܵ�·������

    public static void Dijkstra() {
        visit[1] = 1;//Դ�㵽�Լ�����Ϊ0
        minDistance[1] = 0;

        //ѭ��ÿ���ڵ�
        for(int l = 2; l <= N; l++) {
            int Dtemp = max;//���·����ʼ��Ϊ���ֵ
            int k = -1;//k��ʾ��ǰ�������·���Ľڵ�

            //��һ��ѭ�����ҵ�������̵ĵ�
            for(int i = 2; i <= N; i++) {
                if(visit[i] == 0 && distance[1][i] < Dtemp) {
                    Dtemp = distance[1][i];//����Ȩ�س���
                    k = i;
                }
            }
            visit[k] = 1;//�ҵ�ӵ�����·���ı�
            minDistance[k] = Dtemp;//�����Ӧ��̾���

            //����һ��ѭ���ҵ��ĵ�Ϊ���ģ�ͨ��һ��ѭ����������visit[i]Ϊ0�ĵ㵽Դ��ľ���
            for(int i = 2; i <= N; i++) {
                if(visit[i] == 0 && (distance[1][k] + distance[k][i]) < distance[1][i]) {//���Դ�㵽�õ���̾��뻹û���ҵ������ҵ��õ�ĸ���·��
                    distance[1][i] = distance[1][k] + distance[k][i];
                    path[i] = path[k] + "-->" + i;//����·���ϵĽڵ�
                }
            }
        }

        //���·��
        for(int i=1;i<=N;i++) {
            System.out.println("��"+1+"������"+i+"�����·��Ϊ��"+path[i]);
        }
        System.out.println("=====================================");
        for(int i = 1; i <= N; i++) {
            System.out.println("��1������" + i + "�����̾���Ϊ��" + minDistance[i]);
        }
        for (int i = 1; i <= N; i++) {
            sum+=minDistance[i];
        }
        System.out.println(sum);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Scanner input = new Scanner(System.in);
        System.out.print("������ڵ����N��������M�� ");
        N = input.nextInt();
        //��
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
                    distance[i][j] = max;//Ĭ�ϱߵ�Ȩ��Ϊ����
                }
            }
            minDistance[i] = max;//Ĭ��Դ�㵽��������̾���Ϊ����
            path[i] = "1-->" + i;
        }

        System.out.println("������" + m +"������x��y��z����ʾx�㵽y��ľ���Ϊz����");
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
