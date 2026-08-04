import java.util.Arrays;

public class EraseOverlapIntervals {
    public static void main(String[] args) {
        EraseOverlapIntervals eoi = new EraseOverlapIntervals();
        int[][] intervals = {
            { 1, 2},
            {2, 3}
    };
    int minOverlapIntervals = eoi.eraseOverlapIntervals(intervals);
    System.out.println("minimum arrow to burst ballon " + minOverlapIntervals);
    }

    public int eraseOverlapIntervals(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int ans = 1;
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= prev[1]) {
                ans++;
                prev = intervals[i];
            }
        }

        return intervals.length - ans;
    }
}
