public class FenwickTree {int[] array;

    public FenwickTree(int n) {
        array = new int[n + 1];
    }

    void update(int index, int diff) {
        while (index < array.length) {
            array[index] += diff;
            index += index & (-index);
        }
    }

    int totalSum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += array[index];
            index -= index & (-index);
        }
        return sum;
    }

}
