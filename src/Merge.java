import java.util.Scanner;

public class Merge {
    private static int nixuNum = 0;
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.println("input the length of the array:");
        int number=input.nextInt();
        int array[]=new int[number];
        System.out.println("input the array");
        input.nextLine();
        String string=input.nextLine();
        String[] temp = string.split(" ");
        for(int i = 0; i <temp.length; i++){
            array[i] = Integer.parseInt(temp[i]);
        }
        mergesort(array, 0, array.length-1);
        System.out.println(nixuNum);
    }

    public static void mergesort(int arr[], int left, int right) {
        if (left<right) {
            mergesort(arr, left, (left+right) / 2);//对左边进行排序
            mergesort(arr, (left+right) / 2 + 1, right);//对右边进行排序
            merge(arr, left, (left+right) / 2, right);//对整个数组进行归并
        }
    }

    private static void merge(int arr[], int left, int mid, int right) {
        int temp[] = new int[arr.length];//创建临时数组
        int s = 0;//临时数组指针
        int i = left;
        int j = mid + 1;
        while (i<= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[s++] = arr[i++];
            } else {
                temp[s++] = arr[j++];
                nixuNum += mid - i + 1;//计算这次比较得出的逆序数
            }
        }
        while (i<= mid) {
            temp[s++] = arr[i++];
        }
        while (j <= right) {
            temp[s++] = arr[j++];
        }
        int t = 0;
        for (int a = left; a <=right ; a++) {
            arr[a] = temp[t++];//将数组给定位置的数字排序后通过临时数组重新写入array
        }
    }

}
