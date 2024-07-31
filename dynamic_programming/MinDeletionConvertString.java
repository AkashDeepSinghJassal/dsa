import java.util.HashMap;
import java.util.Objects;

/**
 * This class provides methods to find the minimum deletions required to convert
 * a string
 * into a balanced string where no character is lexicographically smaller than
 * the character before it.
 */
public class MinDeletionConvertString {
    // Memoization table to store results of subproblems
    HashMap<Pair<String, Integer>, Integer> dp = new HashMap<>();

    public static void main(String[] args) {
        MinDeletionConvertString mdcs = new MinDeletionConvertString();
        String s = "bbaaababbb";
        // Uncomment the following lines to test the top-down dynamic programming
        // approach
        // int minDel = mdcs.minimumDeletions(s, 0);
        // System.out.println("Minimum deletions using DP: " + minDel);

        // Testing the prefix count approach
        int minDelPrefix = mdcs.minimumDeletions(s);
        System.out.println("Minimum deletions using prefix count: " + minDelPrefix);
    }

    /**
     * Top-down dynamic programming approach to find the minimum deletions required.
     * 
     * @param s     The input string.
     * @param index The current index being processed.
     * @return The minimum number of deletions required to make the string balanced.
     */
    public int minimumDeletions(String s, int index) {
        // Check if the result is already in the memoization table
        if (dp.containsKey(new Pair<>(s, index))) {
            return dp.get(new Pair<>(s, index));
        }

        // If the string is already balanced, no deletions are needed
        if (isBalanced(s)) {
            return 0;
        }

        // If the index reaches the end of the string, return a large value indicating
        // it's not possible
        if (index == s.length()) {
            return Integer.MAX_VALUE;
        }

        // Calculate the minimum deletions without deleting the current character
        int withoutDeleting = minimumDeletions(s, index + 1);

        // Calculate the minimum deletions with deleting the current character
        int withDeleting = minimumDeletions(s.substring(0, index) + s.substring(index + 1), index);

        // Determine the minimum deletions between the two options
        int minDel = Math.min(withoutDeleting == Integer.MAX_VALUE ? Integer.MAX_VALUE : withoutDeleting,
                withDeleting == Integer.MAX_VALUE ? Integer.MAX_VALUE : withDeleting + 1);

        // Store the result in the memoization table
        dp.put(new Pair<>(s, index), minDel);
        return minDel;
    }

    /**
     * Checks if the given string is balanced.
     * A string is balanced if no character is lexicographically smaller than the
     * character before it.
     * 
     * @param s The input string.
     * @return True if the string is balanced, otherwise false.
     */
    public boolean isBalanced(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * A generic Pair class to be used as keys in the HashMap for memoization.
     * 
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     */
    static class Pair<K, V> {
        public K key;
        public V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return (key != null ? key.equals(pair.key) : pair.key == null) &&
                    (value != null ? value.equals(pair.value) : pair.value == null);
        }
    }

    /**
     * Using prefix sum/count approach to find the minimum deletions required.
     * 
     * @param s The input string.
     * @return The minimum number of deletions required to make the string balanced.
     */
    public int minimumDeletions(String s) {
        int aC = 0; // Counter for 'a' characters to the right of the current position
        int bC = 0; // Counter for 'b' characters to the left of the current position
        int minDel = Integer.MAX_VALUE;

        // Calculate the initial count of 'a' characters assuming the string is all 'b's
        for (int i = 0; i < s.length(); i++) {
            aC += 'b' - s.charAt(i);
        }

        // Iterate over the string and update the counts to find the minimum deletions
        for (char c : s.toCharArray()) {
            aC -= 'b' - c; // Update the count of 'a' characters
            minDel = Math.min(minDel, aC + bC); // Calculate the minimum deletions
            bC += c - 'a'; // Update the count of 'b' characters
        }

        return minDel;
    }
}