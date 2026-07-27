class Solution {
    public int subarraySum(int[] nums, int k) {
        // 5 3 -1 1 1  1 -1  -2
        // 5 8  7 8 9  10
        // sum frequencies
        // sum <= k - sum <= k - 1
        // maintain map of prefix sum frequencies
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        int prefixSum = 0;
        for(int i = 0; i < nums.length; i++){
            prefixSum += nums[i];
            // if(map.containsKey(prefixSum - k)) {
                count += map.getOrDefault(prefixSum - k, 0);
            // }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}