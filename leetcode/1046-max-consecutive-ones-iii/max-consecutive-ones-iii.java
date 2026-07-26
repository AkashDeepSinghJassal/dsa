class Solution {
    public int longestOnes(int[] nums, int k) {
        int l, r;
        l = 0;
        r = 0;
        int max = 0;
        int zeroSum = 0;
        // loop
        while(r < nums.length) {
            if(1 == nums[r]) {
                // expand if condition tree ie 0 count is <= k and val is 1
                r++;
            } else {
                if(zeroSum < k) {
                    zeroSum++;
                    r++;
                } else {
                    // shrink if condition false ie 0 count is > k
                    while(nums[l] != 0) {
                        l++;
                    }
                    zeroSum--;
                    l++;
                }
            }
            max = Math.max(r - l, max);
        }
        return max;
    }
}