/**
 * Length of the longest strictly increasing subsequence of an integer array.
 */
public class LongestIncreasingSubsequence {

    /*
     * Longest Increasing Subsequence (LeetCode 300) — Optimized
     *
     * Length of the longest strictly increasing subsequence of nums.
     *
     * Approach:
     * - Maintain a tails list where tails[k] is the smallest ending value of any
     *   increasing subsequence of length k + 1 found so far.
     * - For each number, binary-search the first tails entry that is >= it.
     * - If the number is larger than every tail, append it — a longer subsequence
     *   has been found. Otherwise replace that entry, keeping a smaller ending.
     * - The length of tails at the end is the answer.
     *
     * Pattern:  patience sorting / tails array + binary search
     * Insight:  a smaller ending for a given length never closes off a future
     *           extension that a larger ending would have allowed, so greedily
     *           keeping the smallest tail per length preserves the global LIS length.
     * Time:     O(n log n)  where n = nums.length
     * Space:    O(n)
     */
    public int lengthOfLIS(int[] nums) {
        // tails[k] = smallest ending value among all increasing subsequences of length k+1
        int[] tails = new int[nums.length];
        int size = 0;

        for (int num : nums) {
            // find the leftmost position where tails[pos] >= num (lower_bound)
            int lo = 0;
            int hi = size;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (tails[mid] < num)
                    lo = mid + 1;
                else
                    hi = mid;
            }

            // place num at that position; if lo == size it extends the longest length
            tails[lo] = num;
            if (lo == size)
                size++;
        }
        return size;
    }

    /*
     * Longest Increasing Subsequence (LeetCode 300) — Brute force
     *
     * Length of the longest strictly increasing subsequence of nums.
     *
     * Approach:
     * - Walk the array left to right; for each element decide include or skip.
     * - Include only when the element is strictly larger than the last chosen one.
     * - Track the count of chosen elements along each branch.
     * - Return the maximum count over every include/skip path.
     *
     * Pattern:  exhaustive recursion over include / skip decisions
     * Insight:  every subsequence is reachable by a unique include/skip path, and
     *           the strict-increase rule is applied verbatim — no pruning, no DP.
     * Time:     O(2^n)  where n = nums.length; deliberately slow; only ever run on n <= 8
     * Space:    O(n)    recursion depth
     */
    public int lengthOfLISBrute(int[] nums) {
        // start with no previous value chosen; sentinel smaller than any nums[i] (>= -1e4)
        return lisRecurse(nums, 0, Integer.MIN_VALUE);
    }

    private int lisRecurse(int[] nums, int index, int last) {
        // no elements left — empty continuation contributes length 0
        if (index == nums.length)
            return 0;

        // always allowed: skip the current element
        int best = lisRecurse(nums, index + 1, last);

        // include it only when it strictly increases the subsequence so far
        if (nums[index] > last)
            best = Math.max(best, 1 + lisRecurse(nums, index + 1, nums[index]));

        return best;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();

        System.out.println(solution.lengthOfLIS(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 })); // 4
        System.out.println(solution.lengthOfLIS(new int[] { 0, 1, 0, 3, 2, 3 })); // 4
        System.out.println(solution.lengthOfLIS(new int[] { 7, 7, 7, 7, 7, 7, 7 })); // 1
        System.out.println(solution.lengthOfLIS(new int[] { 1 })); // 1
        System.out.println(solution.lengthOfLIS(new int[] { 1, 2, 3, 4 })); // 4
        System.out.println(solution.lengthOfLIS(new int[] { 4, 3, 2, 1 })); // 1
    }
}
