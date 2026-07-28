class Solution {
    int[] hash = new int[46];
    public int climbStairs(int n) {
        return climb(0, n);
    }
    int climb(int currLev, int n) {
        int count = 0;
        if(currLev == n) {
            return 1;
        }
        if(currLev > n ) {
            return 0;
        }
        
        if(hash[currLev] != 0) {
            return hash[currLev];
        }
        count += climb(currLev + 1, n);
        count += climb(currLev + 2, n);
        
        // System.out.println("curr lev " + currLev + " count " + count);
        hash[currLev] = count;
        return count;
    }
}