import java.util.Arrays;
import java.util.IntSummaryStatistics;

public class CountSort {
    public static void main(String[] args) {
        // how to count sort
        // use frequency and insert elements from low to high

        /**
         * arr = [2, 5, 3, 3, 3, 6]
         * freq = [0, 0, 1, 3, 0, 1, 1]
         * freq[0]--;
         * prefix freq = [0, 0, 1, 4, 4, 5, 6]
         * 
         * low = 1, high = 6, size = 7
         * freq = int[size]
         * loop, to find low high and freq arr
         * then loop freq to insert elements in order
         */

        int[] nums = { 5, 4, 9, 0, -100, -2, -5, 2140, 190, 3, 8, 34 };
        IntSummaryStatistics statistics = Arrays.stream(nums).summaryStatistics();
        CountSort countSort = new CountSort();
        int[] result = countSort.countSort(nums, statistics.getMin(), statistics.getMax());
        Arrays.stream(result).forEach(s -> System.out.print(s + " "));
        System.out.println();

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

        System.out.println();
        int[] result = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            // find position from freq ie freq[nums[i]] - 1
            result[--freq[nums[i] - min]] = nums[i];
        }
        return result;
    }
}
