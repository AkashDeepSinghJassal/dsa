
import java.util.ArrayList;
import java.util.Arrays;

public class MergeIntervals {
    public static void main(String[] args) {
        MergeIntervals mi = new MergeIntervals();
        int[][] intervals = {
                { 1, 3 },
                { 6, 7 },
                { 9, 10 },
                { 18, 22 }
        };
        int[][] mergedIntervals = mi.insert(intervals, new int[] { 4, 5 });
        Arrays.stream(mergedIntervals).forEach(i -> System.err.println(i[0] + " " + i[1]));
    }

    /**
     * 
     * case 1 : total left
     * case 2 : total right
     * case 3 : single merge
     * case 4 : multiple merge
     * sorted by [0]
     * given no overlapping
     * 
     * check interval
     * 1, 10 and 4, 6 or 5, 11 11, 15, -5, -1
     * 4, 6
     * 
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> list = new ArrayList<>();
        if (intervals.length == 0) {
            list.add(newInterval);
            return list.toArray(new int[0][]);
        }
        boolean merged = false;
        for (int[] interval : intervals) {
            if (Math.max(interval[0], newInterval[0]) <= Math.min(interval[1], newInterval[1])) {

                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            } else {
                if (newInterval[0] < interval[1] && !merged) {
                    list.add(newInterval);
                    merged = true;
                }
                list.add(interval);

            }
        }

        if (!merged || intervals[intervals.length - 1][1] < newInterval[0]) {
            list.add(newInterval);
        }
        return list.toArray(new int[0][]);
    }
}
