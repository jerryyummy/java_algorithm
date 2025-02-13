package amazon;

import java.util.Arrays;

public class Maximize_Total_Memory_Points {
    public long maximizeTotalMemoryPoints(int[] memory) {
        Arrays.sort(memory);
        long suffix = 0;
        long result = 0;
        for (int i = memory.length-1; i >= 0 ; i--) {
            suffix += memory[i];
            result += suffix;
        }
        return result;
    }

    public static void main(String[] args) {
        Maximize_Total_Memory_Points obj = new Maximize_Total_Memory_Points();
        System.out.println(obj.maximizeTotalMemoryPoints(new int[]{3,4,5}));
    }
}
