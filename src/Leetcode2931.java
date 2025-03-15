import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Leetcode2931 {
    public long maxSpending(int[][] values) {
        long res = 0;
        int days = 1;
        Queue<Position> queue = new PriorityQueue<>((a,b)->values[a.shop][a.item]-values[b.shop][b.item]);
        for (int i = 0; i < values.length; i++) {
            queue.add(new Position(i, values[i].length - 1));
        }

        while (!queue.isEmpty()) {
            Position p = queue.poll();
            res += (long) values[p.shop][p.item] *days;
            if(p.item>0){
                queue.add(new Position(p.shop,p.item-1));
            }
            days++;
        }
        return res;
    }

    public class Position{
        int shop;
        int item;
        public Position(int shop, int item) {
            this.shop = shop;
            this.item = item;
        }
    }
}
