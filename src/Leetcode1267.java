public class Leetcode1267 {
    public int countServers1(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int[] rowCounts = new int[grid[0].length];
        int[] colCounts = new int[grid.length];

        // Count servers in each row and each column
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rowCounts[col]++;
                    colCounts[row]++;
                }
            }
        }

        int communicableServersCount = 0;

        // Count servers that can communicate (in the same row or column)
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    if (rowCounts[col] > 1 || colCounts[row] > 1) {
                        communicableServersCount++;
                    }
                }
            }
        }

        return communicableServersCount;
    }

    public int countServers2(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int communicableServersCount = 0;

        for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
            int rowCounts = 0;
            int serverColumnIndex = -1;

            // Count the number of servers in the current row and record the
            // first server's column index.
            for (int colIndex = 0; colIndex < cols; ++colIndex) {
                if (grid[rowIndex][colIndex] == 1) {
                    if (rowCounts == 0) {
                        serverColumnIndex = colIndex;
                    }
                    rowCounts++;
                }
            }

            // If more than one server in the row, it can communicate
            boolean canCommunicate = (rowCounts != 1);

            // If there's only one server in the row, check if there's a server
            // in the same column in another row.
            if (!canCommunicate) {
                for (int i = 0; i < rows; ++i) {
                    if (i != rowIndex && grid[i][serverColumnIndex] == 1) {
                        canCommunicate = true;
                        break;
                    }
                }
            }

            // If the server can communicate, add rowCounts to the result.
            if (canCommunicate) {
                communicableServersCount += rowCounts;
            }
        }

        return communicableServersCount;
    }

    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] connectedCounts = new int[m + n];
        DisjointUnionSet dus = new DisjointUnionSet(m + n);

        int canCommunicateCount = 0;

        for (int rowIndex = 0; rowIndex < m; rowIndex++) {
            for (int colIndex = 0; colIndex < n; colIndex++) {
                if (grid[rowIndex][colIndex] == 1) {
                    int rowId = dus.find(rowIndex);
                    int colId = dus.find(m + colIndex);
                    int connectionsByRowCount = connectedCounts[rowId];
                    int connectionsByColCount = connectedCounts[colId];

                    int joinedId = dus.join(rowId, colId);
                    int joinedCount = (rowId != colId) ?
                            connectionsByRowCount + connectionsByColCount + 1 :
                            connectionsByRowCount + 1;

                    connectedCounts[joinedId] = joinedCount;

                    if (joinedCount > 1) {
                        canCommunicateCount++;

                        if (connectionsByRowCount == 1) {
                            canCommunicateCount++;
                        }

                        if (connectionsByColCount == 1) {
                            canCommunicateCount++;
                        }
                    }
                }
            }
        }

        return canCommunicateCount;
    }

    private static class DisjointUnionSet {
        private final int[] roots;
        private final int[] sizes;

        public DisjointUnionSet(int size) {
            roots = new int[size];
            sizes = new int[size];
            for (int i = 0; i < size; i++) {
                roots[i] = i;
                sizes[i] = 1;
            }
        }

        public int find(int node) {
            if (roots[node] != node) {
                roots[node] = find(roots[node]);
            }
            return roots[node];
        }

        public int join(int aNode, int bNode) {
            int rootA = find(aNode);
            int rootB = find(bNode);

            if (rootA == rootB) return rootA;

            if (sizes[rootA] >= sizes[rootB]) {
                roots[rootB] = rootA;
                sizes[rootA] += sizes[rootB];
                return rootA;
            } else {
                roots[rootA] = rootB;
                sizes[rootB] += sizes[rootA];
                return rootB;
            }
        }
    }
}
