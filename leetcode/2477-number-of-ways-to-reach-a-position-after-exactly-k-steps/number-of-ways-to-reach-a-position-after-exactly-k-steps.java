class Solution {
    /**
        difference in odd steps required k to be odd, vice versa with even

        Get all possible outcomes
        - 1 -> 2 -> 3 -> 2.
        - 1 -> 2 -> 1 -> 2.
        - 1 -> 0 -> 1 -> 2
        [index][steps_left]

        f[i][k] = f[i - 1][k+1] + f[i + 1][k+1]

        start pos = 2 end pos = 4 k = 2
        f[startpos][2] = f[start pos - 1][1] + f[start pso + 1][1]
        size is 2k , start pos is center 
        for i - > k

        start pos = 0 
        end pos = difference
        base case ??
        [2][3] = [1][2] [3][2]


        diff <= k


        pos in K number of steps

        f(pos, k) {

            if(k == k && pos == pos) {
            return 1}
            return f(pos - 1, k + 1) + ff(pos + 1, k + 1);
        }

     */
    int dp[][];
    public int numberOfWaysIterative(int startPos, int endPos, int k) {
        int pos = Math.abs(startPos - endPos);
        int mod = 1000000007;
        if(pos > k || (pos % 2) != (k % 2)) {
            return 0;
        }
        int[][] arr = new int[k + 1][2];
        arr[0][0] = 1;
        int i;
        for(i = 1; i <= k; i++) {
            for(int j = 0; j <= k; j++) {
                arr[j][i % 2] = 0;
                if (j +1 <= k)
                    arr[j][i % 2] += arr[j + 1][(i - 1) % 2];

                arr[j][i % 2] += arr[Math.abs(j - 1)][(i - 1) % 2];

                arr[j][i % 2] = arr[j][i % 2] % mod;
            }
        }
        return arr[pos][(i - 1) % 2];
    }
    public int numberOfWays(int startPos, int endPos, int k) {
        return numberOfWaysIterative(startPos, endPos, k);
        // int diff = Math.abs(startPos - endPos);
        // if(diff > k) {
        //     return 0;
        // }
        // dp = new int[2 * k + 1][k + 1];
        // return numberOfWaysRecursive(k, k + diff, k);
    }
    public int numberOfWaysRecursive(int startPos, int endPos, int k) {
        if(k < 0) {
            return 0;
        }
        if(dp[startPos][k] > 0) {
            return dp[startPos][k];
        }
        if(startPos == endPos && k == 0) {
            return 1;
        }
        dp[startPos][k] =  numberOfWaysRecursive(startPos + 1, endPos, k - 1) + numberOfWaysRecursive(startPos - 1, endPos, k - 1);
        return dp[startPos][k];
    }
}