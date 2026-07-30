class Solution {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {

            dp[i] = nums[i];
            for(int j = i - 2; j >= 0; j--) {
                dp[i] = Math.max(dp[i], dp[j] + nums[i]);
                
            }
            sum = Math.max(sum, dp[i]);
        }
        return sum;
    }
}