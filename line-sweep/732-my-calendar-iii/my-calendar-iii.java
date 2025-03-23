class MyCalendarThree {

    public MyCalendarThree() {
        
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
        for (Integer value : map.values()) {
            sum += value;
            max = Math.max(max, sum);
        }
        return max;
    }
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(startTime,endTime);
 */