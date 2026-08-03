class Solution {
    public int[] frequencySort(int[] nums) {
        // using bucket sort

        
        int max = nums[0];
        int min = nums[0];

        for(int num: nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        int range = max - min + 1;
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        List<List<Integer>> bucket = new ArrayList<>(range);
        for(int i = 0; i <= nums.length; i++) {
            bucket.add(new ArrayList() );
        }
        for(int i = max; i >= min; i--) {
            Integer freqOfIdx = freq.get(i);
            if(freqOfIdx == null)
                continue;
            List<Integer> list = bucket.get(freqOfIdx);
            list.add(i);
        }

        // traverse bucket
        int[] res = new int[nums.length];
        int pos = 0;
        for(int f = 1; f <= nums.length; f++) {
            List<Integer> list = bucket.get(f);
            for(int val : list) {
                for(int c = 0; c < f; c++){
                    res[pos++] = val;

                }
            }
        }
        return res;
    }
}