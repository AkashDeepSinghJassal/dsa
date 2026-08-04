import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * MyCalenderMaxBooking
 */
public class MyCalenderMaxBooking {

    public static void main(String[] args) {
        MyCalendarThree myCalThree = new MyCalendarThree();
        int[][] array = {
            {36, 41},
            {28, 34},
            {40, 46},
            {10, 18},
            {4, 11},
            {25, 34},
            {36, 44},
            {32, 40},
            {34, 39},
            {40, 49}
        };
        Arrays.stream(array).forEach(event -> System.err.println(event[0] + " - " + event[1] + " = " + myCalThree.book(event[0], event[1])));
        
    }


}

class MyCalendarThree {
    
    MyCalendarThree() {
        map.put(Integer.MIN_VALUE, 0);
    }
    TreeMap<Integer, Integer> map = new TreeMap<>();

    public int book(int start, int end) {
        // add values tree map
        // find values prefix
        // check if count more than 3
        
        map.put(start,  map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            sum += entry.getValue();
            if(max < sum){
                max = sum;
            }
        }
        return max;
    }
}