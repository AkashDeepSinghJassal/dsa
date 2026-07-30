class Solution {
    /**
        f(m, n) {
            if m == M && n == n 
            return 1;
            m > M || n > N return 0
            return f (m  + 1, n) + f (m, n + 1)
        }

        tabular 
        [0][n] = 1;
        [m][0] = 1;

     */
     public int uniquePaths(int m, int n) {
        return uniquePathsSpaceOp(m, n);
    }
    public int uniquePathsIterative(int m, int n) {
        int dp[][] = new int[m][n];
        // base 
        for(int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for(int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
    public int uniquePathsSpaceOp(int m, int n) {
        int dp[][] = new int[2][n];
        // base 
        for(int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for(int i = 0; i < 2; i++) {
            dp[i][0] = 1;
        }
        int i;
        for(i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i % 2][j] = dp[(i - 1) % 2][j] + dp[i % 2][j - 1];
            }
        }
        return dp[(i - 1) % 2][n - 1];
    }
}