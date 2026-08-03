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
        // int total = 0;
        // for(int i = 0; i < piles.length; i++) {
        //     total += piles[i];
        // }
        // int dp[][][] = new int[piles.length][piles.length][2];
        // int sum = stone(piles, 0, piles.length -  1, 0, dp);
        // System.out.println("max sum allice " + dp[0][piles.length -1][0]);
        // return sum > (total - sum);
    }

    public int stone(int[] nums, int i, int j, int turn, int dp[][][]) {
        if(dp[i][j][turn] != 0) {
            return dp[i][j][turn];
        }
        if(j - i == 1) {
            dp[i][j][turn] = nums[i] > nums[j] ? nums[i] : nums[j];
            return dp[i][j][turn];
        }
        
        dp[i][j][turn] =  Math.max(nums[i] + stone(nums, i + 1, j, (turn + 1) % 2, dp),  nums[j] + stone(nums, i , j - 1, (turn + 1) % 2, dp));
        return dp[i][j][turn];
        
    }
}