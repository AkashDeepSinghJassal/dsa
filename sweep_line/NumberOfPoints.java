
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfPoints {
    public static void main(String[] args) {
        NumberOfPoints nop = new NumberOfPoints();
        Integer[][] array = {
            {1, 2},
            {3, 4}
        };

        // Convert 2D array to a list of lists
        List<List<Integer>> lists = new ArrayList<>();
        
        for (Integer[] innerArray : array) {
            lists.add(new ArrayList<>(Arrays.asList(innerArray)));
        }
        int totalIntersections = nop.numberOfPoints(lists);
        System.out.println("total intersections " + totalIntersections);
    }
    public int numberOfPoints(List<List<Integer>> nums) {

        return numberOfPointsSumPrefix(nums);
    }
    public int numberOfPointsSumPrefix(List<List<Integer>> lists) {
        int[] prefix = new int[102];
        for (List<Integer> l : lists) {
            prefix[l.get(0)]++;
            prefix[l.get(1) + 1]--;
        }
        int sum, count;
        sum = count = 0 ;
        for (int i = 0; i < prefix.length - 1; i++) {
            sum += prefix[i];
            if(sum > 0)
                count++;
        }
        return count;
    }
}
