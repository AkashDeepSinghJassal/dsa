class Solution {
    int[] hash = new int[46];
    public int climbStairs(int n) {
        // return climb(0, n);
        return climbTabular(n);
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
    int climbTabular(int n ) {
        if(n == 1)
            return 1;
        if(n == 2)
            return 2;
        int last = 2;// step 2
        int secondLast = 1;
        int curr = 0;
        for(int i = 3; i <= n; i++) {
            curr = last + secondLast;
            secondLast = last;
            last = curr;
        }
        return curr;
    }
}