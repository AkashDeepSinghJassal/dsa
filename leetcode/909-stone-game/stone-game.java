class Solution {
    /**
    problem dp  
    
    idxi, idxj
    if(idxi - idx j == 1) {
    return return sum1 > sum2;
    }
    f(idxi + 1, idxj, turn) || f(idx, idxj - 1)
    
    */
    public boolean stoneGame(int[] piles) {
        return true;
       
        // int dp[][] = new int[piles.length][piles.length];
        // int adv = stone(piles, 0, piles.length -  1, dp);
        // return adv > 0;
    }

    public int stone(int[] nums, int i, int j, int dp[][]) {
        if(i == j) {
            return nums[i];
        }
        if(dp[i][j] != 0)
            return dp[i][j];
        // find advantage
        int left = nums[i] - stone(nums, i + 1, j, dp);
        int right = nums[j] - stone(nums, i, j - 1, dp);
        dp[i][j] = Math.max(left, right);
        return dp[i][j];
        
    }
}