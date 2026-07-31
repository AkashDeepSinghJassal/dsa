class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if(sum % 2 == 1) {
            return false;
        }
        sum = sum/2;
        boolean[][] dp = new boolean[nums.length][sum + 1];
        for(int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }
        // initialize memoization

        // base condition
        // currSum + remainingSum = total
        // if currSum =0 condition true always remSum == total
        int i;
        for(i = 1; i < nums.length; i++) {
            for(int j = 1; j <= sum; j++) {
                if(j - nums[i] >= 0)
                    dp[i][j] = dp[i - 1][j - nums[i]];
                dp[i][j] = dp[i][j] || dp[i - 1][j];
            }
        }
        return dp[i - 1][sum];
    }
}