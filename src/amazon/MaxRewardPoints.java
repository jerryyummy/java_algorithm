package amazon;

import java.util.PriorityQueue;

public class MaxRewardPoints {
    public long getMaxRewardPoints(int[] reward) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int r : reward) {
            if (r > 0) {
                maxHeap.add(r);
            }
        }

        long totalPoints = 0;

        while (!maxHeap.isEmpty()) {
            int maxReward = maxHeap.poll();
            totalPoints += maxReward;
            PriorityQueue<Integer> tempHeap = new PriorityQueue<>((a, b) -> b - a);
            while (!maxHeap.isEmpty()) {
                int reducedReward = maxHeap.poll() - 1;
                if (reducedReward > 0) {
                    tempHeap.add(reducedReward);
                }
            }

            maxHeap = tempHeap;
        }

        return totalPoints;
    }

    public static void main(String[] args) {
        MaxRewardPoints solution = new MaxRewardPoints();
        int[] reward = {5, 5,5};
        System.out.println(solution.getMaxRewardPoints(reward)); // 输出 7
    }
}
