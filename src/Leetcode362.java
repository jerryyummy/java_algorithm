import java.util.ArrayList;
import java.util.List;

public class Leetcode362 {
    List<Integer> list;

    public Leetcode362() {
        list = new ArrayList<>();

    }

    public void hit(int timestamp) {
        list.add(timestamp);
    }

    public int getHits(int timestamp) {
        int end = binarySearch(timestamp+1);
        int start = binarySearch(timestamp-299);
        return end - start;
    }

    public int binarySearch(int target) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) == target) {
                if (mid == 0 || list.get(mid - 1) != target) {
                    return mid;
                }else{
                    right = mid - 1;
                }
            }else if (list.get(mid) > target) {
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Leetcode362 leetcode362 = new Leetcode362();
        leetcode362.hit(1);
        leetcode362.hit(2);
        leetcode362.hit(3);
        System.out.println(leetcode362.getHits(4));
        leetcode362.hit(300);
        System.out.println(leetcode362.getHits(300));
        System.out.println(leetcode362.getHits(301));
    }
}
