/**
 * Minimum initial strength to defeat all monsters with temporary range boosts.
 */
public class MinimumInitialStrength {

    /*
     * Minimum Initial Strength to Defeat All Monsters (LeetCode-style)
     *
     * Minimum starting strength to beat every monster left-to-right, where
     * overlapping range boosts add a temporary bonus used only for the check.
     *
     * Pattern:  difference array + backward greedy
     * Insight:  after computing bonus[i], walk from the right. If the suffix still
     *           needs positive strength req, clamp-to-zero cannot satisfy it, so
     *           you must enter with req + monsters[i]. If the suffix needs 0, you
     *           only need max(0, monsters[i] - bonus[i]) for the local check.
     * Time:     O(n + m)  where n = monsters.length, m = boosts.length
     * Space:    O(n)
     */
    public long minInitialStrength(int[] monsters, int[][] boosts) {
        int n = monsters.length;
        long[] bonus = new long[n + 1];
        for (int[] boost : boosts) {
            bonus[boost[0]] += boost[2];
            bonus[boost[1] + 1] -= boost[2];
        }
        for (int i = 1; i < n; i++)
            bonus[i] += bonus[i - 1];

        long req = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (req > 0)
                req += monsters[i];
            else
                req = Math.max(0L, monsters[i] - bonus[i]);
        }
        return req;
    }

    // Exhaustive search over initial strength; kept for validation against the optimized version.
    public long minInitialStrengthBrute(int[] monsters, int[][] boosts) {
        int n = monsters.length;
        long[] bonus = new long[n];
        for (int[] boost : boosts) {
            for (int i = boost[0]; i <= boost[1]; i++)
                bonus[i] += boost[2];
        }

        long upper = 0;
        for (int monster : monsters)
            upper += monster;

        for (long start = 0; start <= upper; start++) {
            if (canDefeat(monsters, bonus, start))
                return start;
        }
        return upper;
    }

    private boolean canDefeat(int[] monsters, long[] bonus, long strength) {
        for (int i = 0; i < monsters.length; i++) {
            if (strength + bonus[i] < monsters[i])
                return false;
            strength = Math.max(0L, strength - monsters[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        MinimumInitialStrength solution = new MinimumInitialStrength();

        System.out.println(solution.minInitialStrength(
                new int[] { 5, 10, 15 },
                new int[][] { { 1, 1, 10 } })); // 30

        System.out.println(solution.minInitialStrength(
                new int[] { 5, 10, 15 },
                new int[][] { { 1, 2, 10 }, { 1, 2, 5 } })); // 5

        System.out.println(solution.minInitialStrength(
                new int[] { 5 },
                new int[][] {})); // 5

        System.out.println(solution.minInitialStrength(
                new int[] { 10 },
                new int[][] { { 0, 0, 15 } })); // 0

        System.out.println(solution.minInitialStrength(
                new int[] { 3, 5 },
                new int[][] { { 1, 1, 10 } })); // 3
    }
}
