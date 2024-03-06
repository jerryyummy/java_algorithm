import java.util.PriorityQueue;
import java.util.Queue;

class Leetcode295 {
    Queue<Integer> smallqueue;
    Queue<Integer> bigqueue;

    public Leetcode295() {
        smallqueue = new PriorityQueue<>((a,b)->a-b);//存放较大的一半
        bigqueue = new PriorityQueue<>((a,b)->b-a);//存放较小的一半
    }

    public void addNum(int num) {
        if (smallqueue.size()== bigqueue.size()){
            if(!bigqueue.isEmpty() && bigqueue.element()>num){
                smallqueue.offer(bigqueue.poll());
                bigqueue.offer(num);
            }else{
                smallqueue.offer(num);
            }

        }else{
            if (smallqueue.element()<num){
                bigqueue.offer(smallqueue.poll());
                smallqueue.offer(num);
            }else{
                bigqueue.offer(num);
            }
        }
    }

    public double findMedian() {
        if (smallqueue.size()==bigqueue.size()){
            return (double) (smallqueue.element() + bigqueue.element()) /2;
        }else return smallqueue.element();
    }
}
