import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Count endpoints with no request in [checkTime - lookbackPeriod, checkTime] per query.
 */
public class InactiveEndpointCount {

    /*
     * Inactive CloudFront Endpoints (Amazon OA) — Optimized
     *
     * For each check time t, returns how many endpoints have zero accesses in
     * the inclusive window [t - lookbackPeriod, t].
     *
     * Approach:
     * - Sort every access by time, and sort the queries by check time (remembering
     *   each query's original index).
     * - Walk the queries from earliest to latest, sliding a window over the access
     *   log so it always holds exactly the requests whose times fall inside the
     *   current lookback interval.
     * - Maintain a frequency map of endpoints inside that window; the map's size is
     *   the number of endpoints that received at least one request.
     * - Inactive count is numEndpoints minus that size (zero when every endpoint
     *   was hit).
     *
     * Pattern:  sort + sliding window + distinct-count map
     * Insight:  as check times only move forward, the valid access window also
     *           slides forward — each log is inserted and removed at most once, so
     *           all queries share one linear pass instead of rescanning the log.
     * Time:     O((m + q) log(m + q))  where m = accessLogs.length, q = checkTimes.length
     * Space:    O(m + q + E)  where E = distinct endpoints present in the current window
     *
     * Constraint assumption: m is on the same order as the stated bounds (≤ 10^5).
     */
    public int[] getInactiveEndpointCount(
            int numEndpoints, int[][] accessLogs, int[] checkTimes, int lookbackPeriod) {
        int m = accessLogs.length;
        int q = checkTimes.length;

        // Sort copies so the caller's arrays stay untouched.
        int[][] logs = Arrays.copyOf(accessLogs, m);
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int[][] queries = new int[q][2]; // [checkTime, originalIndex]
        for (int i = 0; i < q; i++) {
            queries[i][0] = checkTimes[i];
            queries[i][1] = i;
        }
        Arrays.sort(queries, (a, b) -> Integer.compare(a[0], b[0]));

        int[] answers = new int[q];
        Map<Integer, Integer> inWindow = new HashMap<>();
        int left = 0;
        int right = 0;

        for (int[] query : queries) {
            int t = query[0];
            int windowStart = t - lookbackPeriod;

            // Grow the right edge: include every access at time <= t.
            while (right < m && logs[right][1] <= t) {
                int endpoint = logs[right][0];
                inWindow.merge(endpoint, 1, Integer::sum);
                right++;
            }

            // Shrink the left edge: drop every access strictly before windowStart.
            while (left < right && logs[left][1] < windowStart) {
                int endpoint = logs[left][0];
                int count = inWindow.get(endpoint) - 1;
                if (count == 0) {
                    inWindow.remove(endpoint);
                } else {
                    inWindow.put(endpoint, count);
                }
                left++;
            }

            answers[query[1]] = numEndpoints - inWindow.size();
        }
        return answers;
    }

    public static void main(String[] args) {
        InactiveEndpointCount solver = new InactiveEndpointCount();

        System.out.println(Arrays.toString(solver.getInactiveEndpointCount(
                3,
                new int[][] {{1, 3}, {2, 6}, {1, 5}},
                new int[] {10, 11},
                5)));
        // [1, 2]

        System.out.println(Arrays.toString(solver.getInactiveEndpointCount(
                6,
                new int[][] {{3, 2}, {4, 3}, {2, 6}, {6, 3}},
                new int[] {3, 2, 6},
                2)));
        // [3, 5, 5]  — statement interval for t=6 is [4,6] (only endpoint 2 active).
        //              Sample text that says 4 for the third query looks like a typo.

        System.out.println(Arrays.toString(solver.getInactiveEndpointCount(
                1,
                new int[][] {{1, 1}},
                new int[] {1},
                1)));
        // [0]

        System.out.println(Arrays.toString(solver.getInactiveEndpointCount(
                2,
                new int[][] {},
                new int[] {5},
                3)));
        // [2]

        System.out.println(Arrays.toString(solver.getInactiveEndpointCount(
                4,
                new int[][] {{1, 10}, {2, 10}, {3, 10}, {4, 10}},
                new int[] {10, 11, 15},
                5)));
        // [0, 0, 0]
    }
}
