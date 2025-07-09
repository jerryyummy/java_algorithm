import java.util.*;

public class Leetcode2467 {
    private List<List<Integer>> adjacencyList;
    private Map<Integer, Integer> bobArrivalTime;
    private boolean[] visited;

    public int mostProfitablePath(int[][] edges, int bobNode, int[] amount) {
        int n = amount.length;
        initializeGraph(n, edges);
        findBobPath(bobNode);

        return bfsToFindMaxProfit(amount);
    }

    private void initializeGraph(int n, int[][] edges) {
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
    }

    private void findBobPath(int bobNode) {
        bobArrivalTime = new HashMap<>();
        visited = new boolean[adjacencyList.size()];
        dfsForBob(bobNode, 0);
    }

    private boolean dfsForBob(int currentNode, int time) {
        visited[currentNode] = true;
        bobArrivalTime.put(currentNode, time);

        if (currentNode == 0) {
            return true;
        }

        for (int neighbor : adjacencyList.get(currentNode)) {
            if (!visited[neighbor]) {
                if (dfsForBob(neighbor, time + 1)) {
                    return true;
                }
            }
        }

        bobArrivalTime.remove(currentNode);
        return false;
    }

    private int bfsToFindMaxProfit(int[] amount) {
        int maxProfit = Integer.MIN_VALUE;
        visited = new boolean[adjacencyList.size()];
        Queue<NodeState> queue = new LinkedList<>();
        queue.offer(new NodeState(0, 0, 0));

        while (!queue.isEmpty()) {
            NodeState current = queue.poll();
            int node = current.node;
            int time = current.time;
            int profit = current.profit;

            // Calculate current node's profit based on Alice and Bob's arrival times
            profit += calculateNodeProfit(node, time, amount);

            // Check if this node is a leaf (excluding root)
            if (isLeafNode(node) && node != 0) {
                maxProfit = Math.max(maxProfit, profit);
            }

            // Explore neighbors
            for (int neighbor : adjacencyList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(new NodeState(neighbor, time + 1, profit));
                }
            }
        }

        return maxProfit;
    }

    private int calculateNodeProfit(int node, int time, int[] amount) {
        if (!bobArrivalTime.containsKey(node)) {
            return amount[node]; // Bob never visits this node
        }

        int bobTime = bobArrivalTime.get(node);
        if (time < bobTime) {
            return amount[node]; // Alice arrives first
        } else if (time == bobTime) {
            return amount[node] / 2; // Both arrive at the same time
        } else {
            return 0; // Bob has already visited and reset the amount
        }
    }

    private boolean isLeafNode(int node) {
        return adjacencyList.get(node).size() == 1;
    }

    private static class NodeState {
        int node;
        int time;
        int profit;

        NodeState(int node, int time, int profit) {
            this.node = node;
            this.time = time;
            this.profit = profit;
        }
    }
}
