import java.util.ArrayList;
import java.util.List;

/**
 * Generates every subsequence of a string, including the empty one.
 */
public class AllSubsequences {

    /*
     * Print All Subsequences of a String (LeetCode-style / GFG) — Optimized
     *
     * Every subsequence of s, including the empty string, preserving character order.
     *
     * Approach:
     * - Walk the string left to right; at each character decide include or skip.
     * - When including, append the character to the path; when the decision is undone,
     *   delete it (backtrack) so the sibling branch sees a clean path.
     * - At the end of the string, record a copy of the current path.
     * - Empty path is recorded once — that is the empty subsequence.
     *
     * Pattern:  pick / don't-pick recursion with backtracking
     * Insight:  every subsequence corresponds to exactly one include/skip path, so the
     *           recursion tree enumerates the power set without duplicates or omissions.
     * Time:     O(n * 2^n)  where n = s.length(); 2^n leaves, each copying up to n chars
     * Space:    O(n)        recursion depth and path buffer, excluding the output list
     */
    public List<String> allSubsequences(String s) {
        List<String> result = new ArrayList<>();
        // mutable path shared across branches; backtracking restores it after each include
        StringBuilder path = new StringBuilder();
        generate(s, 0, path, result);
        return result;
    }

    private void generate(String s, int index, StringBuilder path, List<String> result) {
        // every character has been decided — path is one complete subsequence
        if (index == s.length()) {
            result.add(path.toString());
            return;
        }

        // include s[index]: extend the path, recurse, then undo the append
        path.append(s.charAt(index));
        generate(s, index + 1, path, result);
        path.deleteCharAt(path.length() - 1);

        // skip s[index]: path is unchanged
        generate(s, index + 1, path, result);
    }

    /*
     * Print All Subsequences of a String (LeetCode-style / GFG) — Brute force
     *
     * Every subsequence of s, including the empty string, preserving character order.
     *
     * Approach:
     * - There are exactly 2^n candidate masks, one bit per character position.
     * - For each mask from 0 to 2^n - 1, build the string of characters whose bits are set.
     * - Mask 0 produces the empty subsequence; every other mask a non-empty one.
     * - Collect every built string — the full power set.
     *
     * Pattern:  exhaustive bitmask enumeration over every subset of positions
     * Insight:  each mask is a unique subset of indices, so every subsequence is built
     *           exactly once with no recursive branching or pruning.
     * Time:     O(n * 2^n)  where n = s.length(); deliberately slow; only ever run on n <= 8
     * Space:    O(n)        builder for one subsequence, excluding the output list
     */
    public List<String> allSubsequencesBrute(String s) {
        int n = s.length();
        List<String> result = new ArrayList<>();
        int total = 1 << n; // 2^n masks

        // each mask encodes which positions are kept
        for (int mask = 0; mask < total; mask++) {
            StringBuilder sub = new StringBuilder();
            for (int i = 0; i < n; i++) {
                // bit i set → include s[i], preserving left-to-right order
                if ((mask & (1 << i)) != 0)
                    sub.append(s.charAt(i));
            }
            result.add(sub.toString());
        }
        return result;
    }

    public static void main(String[] args) {
        AllSubsequences solution = new AllSubsequences();

        print(solution, "abc"); // 8 subsequences including ""
        print(solution, "a"); // 2 subsequences: "a" and ""
        print(solution, ""); // 1 subsequence: ""
        print(solution, "ab"); // 4 subsequences including ""
    }

    private static void print(AllSubsequences solution, String s) {
        List<String> result = solution.allSubsequencesBrute(s);
        System.out.println("s=\"" + s + "\" count=" + result.size() + " " + result);
    }
}
