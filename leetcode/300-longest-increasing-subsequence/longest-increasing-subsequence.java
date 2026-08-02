class Solution {
    /**
    
    f(idx, last)
        if idx == 0 
            if last > arr[idx]
                return 1;
            else 
                return 0;

        int not take = f(idx - 1), last);
        last > arr[idx]
            int take = 1 + f(idx - 1, arr[idx])
        return max(take, nottake);
     */
    public int lengthOfLIS(int[] nums) {
        // int last = 10000;
        // for(int i = 0; i < nums.length; i++)
        //     nums[i] += last;
        // int[][] dp = new int[nums.length][2 * last + 1];
        // return lengthRecursive(nums.length - 1, 2 * last, nums, dp);
        return lengthLISIterativeOpMain(nums);
    }
    public int lengthRecursive(int idx, int last, int[]nums, int[][] dp) {
        if(idx == 0) {
            if(last > nums[idx])
                return 1;
            else 
                return 0;
        }
        if(dp[idx][last] != 0)
            return dp[idx][last];
        int nottake = lengthRecursive(idx - 1, last, nums, dp);
        int take = 0;
        if(last > nums[idx])
            take = 1 + lengthRecursive(idx - 1, nums[idx], nums, dp);
        dp[idx][last] = Math.max(take, nottake);
        return dp[idx][last];
    }

    public int lengthLISRecOpmain(int[] nums) {
        int max = 0;
        for(int i = 0; i < nums.length; i++) {
            max = Math.max(max, lengthLISRecOp(nums, i));
        }
        return max;
    }
    public int lengthLISRecOp(int[] nums, int idx) {
        if(idx == 0) {
            return 1;
        }
        int max = 1;
        for(int i = idx - 1; i >= 0; i--) {
            if(nums[i] < nums[idx])  {
                max = Math.max(max, 1 + lengthLISRecOp(nums, i));
            }
        }
        return max;
    }
    public int lengthLISIterativeOpMain(int[] nums) {
        
        int dp[] = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for(int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for(int j = i - 1; j >= 0; j--) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}