class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if(sum % 2 == 1) {
            return false;
        }
        boolean[][] dp = new boolean[2][sum + 1];
        for(int i = 0; i < 2; i++) {
            dp[i][0] = true;
        }
        // initialize memoization

        // base condition
        // currSum + remainingSum = total
        // if currSum =0 condition true always remSum == total
        int i;
        sum = sum/2;
        if(nums[0] > sum )
            return false;
        dp[0][nums[0]] = true; 
        
        for(i = 1; i < nums.length; i++) {
            for(int j = 1; j <= sum; j++) {
                if(j - nums[i] >= 0)
                    dp[i%2][j] = dp[(i - 1) % 2][j - nums[i]];
                dp[i % 2][j] = dp[i % 2][j] || dp[(i -1) % 2][j];
            }
        }
        return dp[(i - 1) % 2][sum];
    }
}