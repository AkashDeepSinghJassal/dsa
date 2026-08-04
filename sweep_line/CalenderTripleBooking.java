
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;

public class CalenderTripleBooking {
    public static void main(String[] args) {
        MyCalendarTwo myCalTwo = new MyCalendarTwo();
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
        Arrays.stream(array).forEach(event -> System.err.println(event[0] + " - " + event[1] + " = " + myCalTwo.book(event[0], event[1])));
        
    }


}

class MyCalendarTwo {
    
    TreeMap<Integer, Integer> map = new TreeMap<>();
    MyCalendarTwo() {
        map.put(Integer.MIN_VALUE, 0);
    }

    public boolean book(int start, int end) {
        // add values tree map
        // find values prefix
        // check if count more than 3
        
        map.put(start,  map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int sum = 0;
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            sum += entry.getValue();
            if(sum == 3){
                map.put(start,  map.getOrDefault(start, 0) - 1);
                map.put(end, map.getOrDefault(end, 0) + 1);
                return false;
                
            }
        }
        return true;
    }
}
