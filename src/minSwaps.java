/*
Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.
 */
public class minSwaps {
    public int minSwaps(int[] data) {
        int ones = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i]==1) ones++;
        }
        int res = Integer.MAX_VALUE,temp = 0,i=0,j=0;
        while (j< data.length){
            while (j<data.length && j-i<ones){
                if (data[j++]==0) temp++;
            }
            res = Math.min(res,temp);
            if (j== data.length) break;
            if (data[i++]==0) temp--;
        }
        return res;
    }
}
