class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        // explain brute force
        /**
            loop through all substring
            calculate substring 
            for -> i
                for -> j
                    for k -> i to j
                        check for unique char == k

            O n ^ 3
            space O (n)
            optimized 
            for -> i
                for -> j
                stop when unique count is more than K
            O n ^ 2
            O n 

            try sliding window of variable size to find subarry count
            doens't work 

            we can find subarray with <= k different 
            if we do F k = f <= k - f <= k - 1


         */
        // explain 

        return subarrays(nums, k) - subarrays(nums, k - 1);
    }
    int subarrays(int[] nums, int k) {
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int l = 0;
        for(int r = 0; r < nums.length; r++) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            // map size tell unique char
            while(map.size() > k) {
                // remove character until it's <= k
                map.put(nums[l], map.get(nums[l]) - 1);
                if(map.get(nums[l]) == 0)
                    map.remove(nums[l]);
                l++;
            }
            sum += r - l + 1;
        }
        return sum;
    }
}