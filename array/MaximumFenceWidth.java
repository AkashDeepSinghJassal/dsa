import java.util.HashMap;
import java.util.Map;

/**
 * Given planks of various heights, builds a fence whose planks all share the
 * same height. A fence plank is either one original plank, or exactly two
 * distinct original planks glued together. Every original plank may be used at
 * most once, and leftovers are allowed.
 *
 * The answer is the largest number of equal-height planks that can be formed.
 */
public class MaximumFenceWidth {

    /**
     * Returns the maximum width of a fence made of equal-height planks.
     *
     * For a fixed target height h, a plank of height v can only ever be glued to
     * a plank of height h - v, so the choices for different values never
     * compete with each other. That makes the best count for h simply:
     *
     *   count(h)                                  planks already at height h
     * + min(count(v), count(h - v)) for v < h - v pairs of two different heights
     * + count(h / 2) / 2                          pairs of two equal heights
     *
     * Rather than testing every candidate height against every value, each pair
     * of distinct heights contributes its share to the bucket of their sum, so
     * the whole table is built in one pass over the value pairs.
     *
     * @param planks heights of the available planks.
     * @return the maximum number of equal-height planks that can be built.
     */
    public int maxFenceWidth(int[] planks) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int plank : planks)
            counts.merge(plank, 1, Integer::sum);

        int distinctCount = counts.size();
        int[] values = new int[distinctCount];
        int[] frequency = new int[distinctCount];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            values[index] = entry.getKey();
            frequency[index] = entry.getValue();
            index++;
        }

        // A single plank is always a valid fence, and using only planks that
        // already have the target height needs no gluing at all.
        int best = 1;

        // Sums can reach 2 * 10^9, which overflows an int, so key by long.
        Map<Long, Integer> pairsBySum = new HashMap<>();
        for (int i = 0; i < distinctCount; i++) {
            // Two planks of the same height glue into one of twice that height.
            if (frequency[i] >= 2) {
                long sum = 2L * values[i];
                pairsBySum.merge(sum, frequency[i] / 2, Integer::sum);
            }
            for (int j = i + 1; j < distinctCount; j++) {
                long sum = (long) values[i] + values[j];
                pairsBySum.merge(sum, Math.min(frequency[i], frequency[j]), Integer::sum);
            }
        }

        for (Map.Entry<Long, Integer> entry : pairsBySum.entrySet()) {
            long height = entry.getKey();
            int width = entry.getValue();
            // Planks that already stand at the target height join for free;
            // they can never be glued to anything since heights are positive.
            if (height <= Integer.MAX_VALUE)
                width += counts.getOrDefault((int) height, 0);
            best = Math.max(best, width);
        }

        for (int count : frequency)
            best = Math.max(best, count);

        return best;
    }

    /**
     * Runs the examples from the problem statement.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        MaximumFenceWidth solution = new MaximumFenceWidth();

        System.out.println(solution.maxFenceWidth(new int[] { 1, 3, 2, 5, 7, 5, 4, 2, 1 })); // 4
        System.out.println(solution.maxFenceWidth(new int[] { 2, 3, 7 })); // 1
        System.out.println(solution.maxFenceWidth(new int[] { 5 })); // 1
        System.out.println(solution.maxFenceWidth(new int[] { 4, 4, 4, 4 })); // 4
        System.out.println(solution.maxFenceWidth(new int[] { 1000000000, 1000000000 })); // 2
    }
}
