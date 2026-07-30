class Solution {
    public int rob(int[] nums) {
        return robNoSpace(nums);
    }
    public int robDp(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length + 1];
        // base case
        dp[0] = 0;
        dp[1] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            dp[i + 1] = Math.max(dp[i - 1] + nums[i], dp[i]);
            
        }
        return dp[nums.length];
    }
    public int robNoSpace(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }
        int prev = nums[0];
        int prev2 = 0;
        int curr = 0;
        for(int i = 1; i < nums.length; i++) {
            curr = Math.max(prev2 + nums[i], prev);
            prev2 = prev;
            prev = curr;

        }
        return curr;
    }
}