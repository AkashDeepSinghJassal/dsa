import java.util.Arrays;
import java.util.IntSummaryStatistics;

public class FrequencySort {
    public static void main(String[] args) {
        /**
         * 1,1,2,2,2,-3,4,5
         * find frequency
         * 0, 2, 3, 1, 1, 1
         * sort frequency
         * 0, 1, 1, 1, 2, 3
         * prefix frequency arr
         * 0, 1, 2, 3, 5, 8
         * traverse main array
         * like n = 5, freq = 1
         */
        int[] nums = {1,1,2,2,2,-3,4,5};
        FrequencySort frequencySort = new FrequencySort();
        int result[] = frequencySort.frequencySort(nums);
        Arrays.stream(result).forEach(s -> System.out.print(s + " "));
    }

    public int[] frequencySort(int[] nums) {

        // find frequency
        // HashMap<Integer, Integer> freq = new HashMap<>();

        // for(int i = 0; i < nums.length; i++){
        //     freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        // }

        // bubble sort based on frequency and higher number
        // use merge sort to improve complexity
        // for(int i = 0; i < nums.length; i++){
        //     for(int j = 0; j < nums.length - i - 1; j++){
        //         // check if frequency of nums[j] is bigger than freq of nums[j + 1]
        //         // or if freq is same then nums[j] is less then nums[j + 1]
        //         if(freq.get(nums[j]) > freq.get(nums[j + 1]) || 
        //         (freq.get(nums[j]) == freq.get(nums[j + 1]) && nums[j] < nums[j + 1] )){
        //             // swap
        //             int temp = nums[j];
        //             nums[j] = nums[j + 1];
        //             nums[j + 1] = temp;
        //         }
        //     }
        // }
        // return nums;
        // use inbuild sort function with comparator
        // return Arrays.stream(nums).boxed()
        // .sorted((a, b) -> {
        //     return freq.get(a) == freq.get(b) ? b -a : freq.get(a) - freq.get(b);
        // })
        // .mapToInt(Integer::intValue).toArray();
        // apply count sort
        IntSummaryStatistics statistics = Arrays.stream(nums).summaryStatistics();
        int min = statistics.getMin();
        int max = statistics.getMax();
        int[] freq = new int[max - min + 1];
        int[] result = new int[nums.length];
        // find freq
        for (int i = 0; i < nums.length; i++) {
            freq[nums[i] - min]++;
        }
        int[] sortedFreq = countSort(freq, min, max);
        int[] sortedPrefixFreq = new int[max - min + 1];

        sortedPrefixFreq[0] = sortedFreq[0];
        for (int i = 1; i < sortedFreq.length; i++) {
            sortedPrefixFreq[i] = sortedFreq[i] + sortedPrefixFreq[i - 1];
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            int freqOfNum = freq[num - min];
            // find freqOf Num in sorted Prefix
            for (int x = sortedFreq.length - 1; x >= 0; x--) {
                if(sortedFreq[x] == freqOfNum){
                    result[--sortedPrefixFreq[x]] = num;
                    break;
                }
                
            }
        }
        return result;
    }


    public int[] countSort(int[] nums, int min, int max) {
        int[] freq = new int[max - min + 1];
        // find freq
        for (int i = 0; i < nums.length; i++) {
            freq[nums[i] - min]++;
        }
        // find prefix freq
        for (int i = 1; i < freq.length; i++) {
            freq[i] += freq[i - 1];
        }

        int[] result = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            // find position from freq ie freq[nums[i]] - 1
            result[--freq[nums[i] - min]] = nums[i];
        }
        return result;
    }
}
