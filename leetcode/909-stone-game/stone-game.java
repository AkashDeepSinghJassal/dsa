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
        // return true;
        // int total = 0;
        // for(int i = 0; i < piles.length; i++) {
        //     total += piles[i];
        // }
        // int dp[][][] = new int[piles.length][piles.length][2];
        // int sum = stone(piles, 0, piles.length -  1, 0, dp);
        // System.out.println("max sum allice " + dp[0][piles.length -1][0]);
        // return sum > (total - sum);
        int dp[][] = new int[piles.length][piles.length];
        int adv = stone(piles, 0, piles.length -  1, dp);
        return adv > 0;
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