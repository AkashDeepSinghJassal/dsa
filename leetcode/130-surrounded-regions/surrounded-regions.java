class Solution {
    public void solve(char[][] board) {
        
        // loop every cell
        // take a visited array 
        // move horizontally or vertically
        int m = board.length;
        int n = board[0].length;
        int[][] visited = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {

                char c = board[i][j];
                if(visited[i][j] == 1 || c == 'X')
                    continue;
                List<Integer> indices = bfs(i, j, board, visited);
                if(indices != null) {
                    for(Integer idx : indices) {
                        board[idx / n][idx % n] = 'X';
                    }
                }
            }
        }
    }

    List<Integer> bfs(int i, int j, char[][] board, int[][] visited) {
        visited[i][j] = 1;
        int m = board.length;
        int n = board[0].length;
        boolean isValid = true;
        if((i == 0 || i == m - 1 || j == 0 || j == n - 1))
            isValid = false;
        Queue<Integer> queue = new LinkedList<>();
        // we add cell index to queue ie i * m + j
        queue.add(i * n + j);
        int[][] moves = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        List<Integer> indices = new ArrayList<>();
        while(!queue.isEmpty()) {
            int idx = queue.remove();
            int a = idx / n;
            int b = idx % n;
            indices.add(idx);
            // top
            for(int[] move : moves) {
                int newA = a + move[0];
                int newB = b + move[1];
                if(newA < 0 || newA >= m || newB < 0 || newB >= n) {
                    continue;
                }
                if(board[newA][newB] == 'O' && (newA == 0 || newA == m - 1 || newB == 0 || newB == n - 1)) {
                    isValid = false;
                }
                if(board[newA][newB] == 'O' && visited[newA][newB] == 0) {
                    queue.add(newA * n + newB);
                    visited[newA][newB] = 1;
                }
            }
        }
        if(!isValid)
            return null;
        return indices;
    }
}