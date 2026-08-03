class Solution {
    public boolean predictTheWinner(int[] nums) {
        return solve(nums) >= 0;
    }

    public int solve(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++ ) {
            dp[i][i] = nums[i];
        }

        for(int len = 2; len <= n; len++) {
            
            for(int i= 0; i <= n - len; i++) {
                int j = i + len - 1;

                int left = nums[i] - dp[i + 1][j];
                int right = nums[j] - dp[i][j - 1];
                dp[i][j] = Math.max(left, right);
            }
        }
        return dp[0][nums.length - 1];
    }
}