import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sort by least frequency first; on a tie, smaller value first.
 *
 * HashMap counts occurrences; an ArrayList bucket per frequency holds the
 * matching values. Values are inserted during a linear sweep from min → max
 * so each bucket is already ascending — no TreeMap (that would be O(n log D)).
 *
 * Time:  O(n + V)  where V = max - min + 1
 * Space: O(n + D)  where D = number of distinct values
 */
public class SortByFreqCountSort {

    public int[] sortByFrequency(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return new int[0];
        }

        int min = nums[0];
        int max = nums[0];
        Map<Integer, Integer> freq = new HashMap<>();
        for (int v : nums) {
            min = Math.min(min, v);
            max = Math.max(max, v);
            freq.merge(v, 1, Integer::sum);
        }

        // buckets.get(f) = distinct values that appear exactly f times
        List<List<Integer>> buckets = new ArrayList<>(n + 1);
        for (int f = 0; f <= n; f++) {
            buckets.add(new ArrayList<>());
        }

        // O(V) sweep keeps values sorted without a TreeMap
        for (int v = min; v <= max; v++) {
            Integer f = freq.get(v);
            if (f != null) {
                buckets.get(f).add(v);
            }
        }

        int[] result = new int[n];
        int pos = 0;
        for (int f = 1; f <= n; f++) {
            for (int v : buckets.get(f)) {
                for (int c = 0; c < f; c++) {
                    result[pos++] = v;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SortByFreqCountSort solver = new SortByFreqCountSort();

        System.out.println(Arrays.toString(solver.sortByFrequency(new int[] {3, 1, 4, 4, 2, 2})));
        // [1, 3, 2, 2, 4, 4]
        System.out.println(Arrays.toString(solver.sortByFrequency(new int[] {1, 1, 2, 2, 2, 3})));
        // [3, 1, 1, 2, 2, 2]
        System.out.println(Arrays.toString(solver.sortByFrequency(new int[] {2, 3, 1, 3, 2})));
        // [1, 2, 2, 3, 3]
        System.out.println(Arrays.toString(solver.sortByFrequency(new int[] {7})));
        // [7]
        System.out.println(Arrays.toString(solver.sortByFrequency(new int[] {5, 5, 5})));
        // [5, 5, 5]
        System.out.println(Arrays.toString(solver.sortByFrequency(new int[] {0, -1, 0, -1, 2})));
        // [2, -1, -1, 0, 0]
    }
}
