public class Leetcode2519 {
    public int kBigIndices(int[] nums, int k){
        int n = nums.length;
        FenwickTree pre = new FenwickTree(n);
        FenwickTree suff = new FenwickTree(n);
        int[][] count = new int[n+1][2];
        for (int i = 0; i < n; i++) {
            count[i][0] = pre.totalSum(nums[i]-1);
            pre.update(nums[i],1);
            count[n-i-1][1] = suff.totalSum(nums[n-i-1]-1);
            suff.update(nums[n-i-1],1);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (count[i][0]>=k && count[i][1]>=k) res++;
        }

        return res;
    }
}
