package amazon;

import java.util.Arrays;

public class MaximumThroughput {
    public static long amazonGetMaxThroughput(int[] host_throughput) {
        Arrays.sort(host_throughput);
        int n = host_throughput.length;
        int acc = 0;
        for (int i = n-1; i >= 0; i-=3){
            if (i - 3 + 1 >= 0){
                int med = host_throughput[i-1];
                acc += med;
            }
        }
        return acc;
    }

    public static void main(String[] args) {
        System.out.println(amazonGetMaxThroughput(new int[]{2,3,4,3,4}));
    }
}
