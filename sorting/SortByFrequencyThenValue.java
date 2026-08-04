import java.util.Arrays;

public class SortByFrequencyThenValue {

    /*
     * Sort Array by Increasing Frequency, ties ascending (LeetCode 1636 variant) — Optimized
     *
     * Returns the numbers reordered so rarer numbers come first, and equally frequent
     * numbers appear smallest-first.
     *
     * Approach:
     * - Count how many times each number occurs, indexing a counting array by number.
     * - Group the distinct numbers by their occurrence count, walking the numbers from
     *   smallest to largest so every group ends up in ascending numeric order.
     * - Emit the groups from the rarest count up to the most frequent, repeating each
     *   number as many times as it occurred.
     * - An empty input yields an empty result; a single number is its own answer.
     *
     * Pattern:  counting sort keyed on frequency, tie-broken by an ascending value sweep
     * Insight:  both sort keys have a bounded integer range — the value spans max-min+1
     *           slots and the frequency can never exceed n — so neither key needs
     *           comparisons. Because the distinct numbers are placed into their frequency
     *           groups during a single ascending sweep, and counting placement is stable,
     *           the tie-break comes for free rather than from a second sort.
     * Time:     O(n + V)  where n = nums.length, V = max - min + 1
     * Space:    O(n + V)
     */
    public int[] sortByFrequency(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return new int[0];
        }

        // The counting array is indexed by value, so it only needs to span the values
        // actually present rather than the whole -100..100 constraint window.
        int min = nums[0];
        int max = nums[0];
        for (int v : nums) {
            min = Math.min(min, v);
            max = Math.max(max, v);
        }
        int range = max - min + 1;

        // Phase 1: occurrences of each value, shifted by min so negatives index safely.
        int[] freq = new int[range];
        for (int v : nums) {
            freq[v - min]++;
        }

        // Phase 2: how many distinct values share each frequency. A frequency is at
        // least 1 and at most n, which bounds this second counting array by n + 1.
        int[] distinctPerFreq = new int[n + 1];
        for (int i = 0; i < range; i++) {
            if (freq[i] > 0) {
                distinctPerFreq[freq[i]]++;
            }
        }

        // Phase 3: prefix sums turn those counts into the first output slot of each
        // frequency group, laid out from the rarest group to the most frequent one.
        int[] groupStart = new int[n + 1];
        int running = 0;
        for (int f = 1; f <= n; f++) {
            groupStart[f] = running;
            running += distinctPerFreq[f];
        }

        // Phase 4: sweep values in ascending order and drop each into its frequency
        // group. Ascending input order plus append-at-cursor placement means each group
        // is already sorted smallest-first, which is exactly the tie-break rule.
        int[] distinctOrdered = new int[running];
        for (int i = 0; i < range; i++) {
            if (freq[i] > 0) {
                distinctOrdered[groupStart[freq[i]]++] = i + min;
            }
        }

        // Phase 5: expand the ordered distinct values back into the full multiset.
        int[] result = new int[n];
        int pos = 0;
        for (int v : distinctOrdered) {
            for (int c = 0; c < freq[v - min]; c++) {
                result[pos++] = v;
            }
        }
        return result;
    }

    /*
     * Sort Array by Increasing Frequency, ties ascending (LeetCode 1636 variant) — Brute force
     *
     * Returns the numbers reordered so rarer numbers come first, and equally frequent
     * numbers appear smallest-first.
     *
     * Approach:
     * - Repeatedly pick the single best number still left to place.
     * - Best means the smallest occurrence count; on a tie, the smaller number.
     * - Occurrence counts are recomputed by rescanning the whole input every time.
     * - Stop once every position of the output has been filled.
     *
     * Pattern:  selection sort driven by a direct reading of the ordering rule
     * Insight:  it cannot be wrong because it never assumes any structure — at each step
     *           it compares the candidate against every remaining element using the rule
     *           exactly as stated, and recounts frequencies from scratch rather than
     *           trusting a cached map.
     * Time:     O(n^3)  deliberately slow; only ever run on n <= 8
     * Space:    O(n)
     */
    public int[] sortByFrequencyBrute(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        boolean[] placed = new boolean[n];

        // Fill the output one slot at a time, front to back.
        for (int out = 0; out < n; out++) {
            int best = -1;
            // Scan every element that has not been placed yet and keep the winner.
            for (int i = 0; i < n; i++) {
                if (placed[i]) {
                    continue;
                }
                if (best == -1) {
                    best = i;
                    continue;
                }
                int freqI = countOccurrences(nums, nums[i]);
                int freqBest = countOccurrences(nums, nums[best]);
                // Rarer wins outright; equal frequency defers to the smaller number.
                if (freqI < freqBest || (freqI == freqBest && nums[i] < nums[best])) {
                    best = i;
                }
            }
            placed[best] = true;
            result[out] = nums[best];
        }
        return result;
    }

    // Counts occurrences by walking the whole array, taking no shortcut.
    private int countOccurrences(int[] nums, int target) {
        int count = 0;
        for (int v : nums) {
            if (v == target) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        SortByFrequencyThenValue solver = new SortByFrequencyThenValue();

        System.out.println(Arrays.toString(solver.sortByFrequency(new int[] {3, 1, 4, 4, 2, 2})));
        // [1, 3, 2, 2, 4, 4]
        System.out.println(Arrays.toString(solver.sortByFrequency(new int[] {1, 1, 2, 2, 2, 3})));
        // [3, 1, 1, 2, 2, 2]
        System.out.println(Arrays.toString(solver.sortByFrequency(new int[] {2, 3, 1, 3, 2})));
        // [1, 2, 2, 3, 3]   (LeetCode 1636 would give [1, 3, 3, 2, 2])
        System.out.println(
                Arrays.toString(solver.sortByFrequency(new int[] {-1, 1, -6, 4, 5, -6, 1, 4, 1})));
        // [-1, 5, -6, -6, 4, 4, 1, 1, 1]

        System.out.println(Arrays.toString(solver.sortByFrequency(new int[] {7})));
        // [7]
        System.out.println(Arrays.toString(solver.sortByFrequency(new int[] {5, 5, 5})));
        // [5, 5, 5]
        System.out.println(Arrays.toString(solver.sortByFrequency(new int[] {3, 1, 2})));
        // [1, 2, 3]
        System.out.println(Arrays.toString(solver.sortByFrequency(new int[] {0, -1, 0, -1, 2})));
        // [2, -1, -1, 0, 0]
        System.out.println(Arrays.toString(solver.sortByFrequency(new int[] {-100, 100, -100})));
        // [100, -100, -100]
    }
}
