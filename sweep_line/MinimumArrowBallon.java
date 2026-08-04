import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MinimumArrowBallon {
    public static void main(String[] args) {
        MinimumArrowBallon mab = new MinimumArrowBallon();
        int[][] points = {
                { 10, 16 },
                { 2, 8 },
                { 1, 6 },
                { 7, 12 }
        };
        int minArrow = mab.findMinArrowShots(points);
        System.out.println("minimum arrow to burst ballon " + minArrow);
    }

    public int findMinArrowShots(int[][] points) {
        /**
         * We have to burst all small ballons or front ones
         * We will take count of overlapping baloons and
         * 1 0 1 2 3 2 3 2 1 0
         */
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
        for (int[] point : points) {
            list.add(Map.entry(point[1], point[0]));
        }
        list.sort((e1, e2) -> {
            return Integer.compare(e1.getKey(), e2.getKey());
        });
        int arrow = 0;
        int lastArrowCord = Integer.MIN_VALUE;
        if(list.get(0).getValue() == lastArrowCord) {
            lastArrowCord = list.get(0).getKey();
            arrow++;
        }
        // case 1 - ballon is int_min to int_max
        // case 2 - multiple ballons with overlapping point
        // cae 3 multiple adjacent ballons[1, 10],[1, 2] [3, 4], [5, 6]
        for (Map.Entry<Integer, Integer> entry : list) {
            int right = entry.getKey();
            int left = entry.getValue();
            // sent arrow on right
            if (lastArrowCord < left) {
                arrow++;
                lastArrowCord = right;
            }
        }
        return arrow;
    }
}
