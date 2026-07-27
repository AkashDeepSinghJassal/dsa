class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        if(goal == 0) {
            return numSubarrays(nums, goal);
        }
        return numSubarrays(nums, goal) - numSubarrays(nums, goal - 1);
    }
    public int numSubarrays(int[] nums, int goal) {
        // find for <= goal 
        int l, r;
        l = r = 0;
        int count = 0;
        int sum = 0;
        while(r < nums.length) {
            count += nums[r];
            System.out.println(" r " + r + " num r " + nums[r]);
            while(count > goal) {
                System.out.println("count " + count + " nums l : " + l + "  " + nums[l] );
                count -= nums[l];
                l++;
            
            }
            r++;
            sum += r - l ;
        }
        return sum;
    }
}