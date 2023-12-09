import java.util.Arrays;

public class HeapSort {
    public void sort(int[] array){
        for (int i = array.length/2-1; i >=0 ; i--) {
            adjust(array,i, array.length);
        }
        for (int i = array.length-1; i >=0 ; i--) {
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            adjust(array,0,i);
        }
        System.out.println(Arrays.toString(array));
    }

    public void adjust(int[] array, int index, int length){
        int temp = array[index];
        for (int i = index*2+1; i < length; i=i*2+1) {
            if(i+1< length && array[i]<array[i+1]){
                i++;
            }
            if(array[i]>temp){
                array[index] = array[i];
                index = i;
            }else {
                break;
            }
        }
        array[index] = temp;
    }

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        heapSort.sort(new int[]{2,4,6,9,3,7});
    }
}
