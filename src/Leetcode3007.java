public class Leetcode3007 {
    private long getCurrSum(long num, int x) {
        long count = 0;
        for (int b = x - 1; b < 60; b += x) {
            //表示第x位的数字从0->1->0 一共需要的完整周期有多少个
            long fullCycles = num / (1L << (b + 1));
            //  位为1的个数是周期长度的一半.
            count += fullCycles * (1L << b);
            // Calculate the remaining bits after the full cycles
            long remaining = num % (1L << (b + 1));
            // This part ensures that only the relevant remaining bits are considered, and any excess bits are ignored.
            count += Math.max(0, remaining - (1L << b));
        }
        return count;
    }

    public long findMaximumNumber(long k, int x) {
        long l = 1, r = (long) 1e15, result = 0;
        while (l <= r) {
            long mid = (r +l) / 2;
            if (getCurrSum(mid, x) <= k) {
                result = mid;
                l = mid+1;
            } else {
                r = mid-1;
            }
        }
        return result-1;
    }
}
