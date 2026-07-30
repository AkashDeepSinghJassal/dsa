class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return uniquePathsWithObstaclesSpaceOp(obstacleGrid);
    }

    public int uniquePathsWithObstaclesSpaceOp(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int dp[][] = new int[2][n];
        // base 
        for(int i = 0; i < n; i++) {
            if(obstacleGrid[0][i] == 1)
                break;
            dp[0][i] = 1;
        }
        for(int i = 0; i < 2 && i < m; i++) {
            if(obstacleGrid[i][0] == 1)
                break;
            dp[i][0] = 1;
        }
        int i;
        System.out.println(Arrays.deepToString(dp));
        for(i = 1; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // adjust condition
                if(obstacleGrid[i][j] == 1) {
                    dp[i % 2][j] = 0;
                }
                else {
                    dp[i % 2][j] = dp[(i - 1) % 2][j];
                    if(j != 0) {
                        dp[i % 2][j] += dp[i % 2][j - 1];
                    }
                     
                }
            }
            System.out.println("when i " + i + " " + Arrays.deepToString(dp));
        }
        return dp[(i - 1) % 2][n - 1];
    }
}