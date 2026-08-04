
import java.util.Objects;
import java.util.TreeMap;


public class CalenderEvent {
    /**
     *      10 - 20
     *  
     *      1- 9
     *      5 - 11
     *      12 - 18
     *      15 - 25
     *      5 - 25
     *      25 - 40
     * 20-10
     * 30-20
     * 30-35
     */
    public static void main(String[] args) {
        MyCalendar myCal = new MyCalendar();
        System.out.println(myCal.book(10, 20));
        System.out.println(myCal.book(20, 30));
        System.out.println(myCal.book(30, 35));
        System.out.println(myCal.book(15, 20));
        System.out.println(myCal.book(200, 300));
        System.out.println(myCal.book(25, 35));
        System.out.println(myCal.book(10, 20));
        System.out.println(myCal.book(5, 100));
        myCal.print();

        MyCalenderTreeMap myCalT = new MyCalenderTreeMap();
        System.out.println(myCalT.book(10, 20));
        System.out.println(myCalT.book(20, 30));
        System.out.println(myCalT.book(30, 35));
        System.out.println(myCalT.book(15, 20));
        System.out.println(myCalT.book(200, 300));
        System.out.println(myCalT.book(25, 35));
        System.out.println(myCalT.book(10, 11));
        System.out.println(myCalT.book(5, 100));
        myCalT.print();
    }
}

/**
 * ALGORITHM :
 * I need a sorted list
 * Linkedlist, Binary tree
 */
class MyCalendar {
    Node<Pair<Integer, Integer>> head;

    static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    static class Pair<U, V> {
        U val1;
        V val2;

        public Pair(U val1, V val2) {
            this.val1 = val1;
            this.val2 = val2;
        }
        
    }

    public MyCalendar() {

    }

    public boolean book(int start, int end) {
        return insert(new Node<>(new Pair<>(start, end - 1)));
    }

    public boolean insert(Node<Pair<Integer, Integer>> node) {
        // first element
        if(Objects.isNull(head)){
            head = node;
            return true;
        }
        if(node.data.val2 < head.data.val1) {
            node.next = head;
            head = node;
            return true;
        } else if ((node.data.val1 >= head.data.val1 && node.data.val1 <= head.data.val2)
            || (node.data.val2 >= head.data.val1 && node.data.val2 <= head.data.val2)
            || (node.data.val1 <= head.data.val1 && node.data.val2 >= head.data.val1)
            || (node.data.val2 <= head.data.val2 && node.data.val2 >= head.data.val2)) {
            return false;
        }
        
        // middle element
        Node<Pair<Integer, Integer>> temp = head;
        while(Objects.nonNull(temp.next)){
            Pair<Integer, Integer> curr = temp.next.data;
            // insert pair in sorted logic
            // node 15, 20 check if it's less than curr
            // both is less - good
                if(node.data.val2 < curr.val1) {
                    // set head and link
                    node.next = temp.next;
                    temp.next = node;
                    return true;
                } else if (node.data.val1 > curr.val2) {
                    temp = temp.next;
                } else {
                    return false;
                }
            // both is more - good
                // continue
            // overlap 
            
        }
        
        temp.next = node;
        // last element
        return true;
    }

    public void print(){
        Node<Pair<Integer, Integer>> curr = head;
        while(curr != null){
            System.err.println(curr.data.val1 + " " + curr.data.val2);
            curr = curr.next;
        }
    }
}

/**
 * InnerCalenderEvent
 */
class MyCalenderTreeMap {

    
    TreeMap<Integer, Integer> map = new TreeMap<>();
    public MyCalenderTreeMap() {
        map.put(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    public boolean book(int start, int end) {
        var e = map.higherEntry(start);
        System.out.println(start + " given higher entry " + e.getKey() + " " + e.getValue());
        if(end <= e.getValue()){
            map.put(end, start);
            return true;
        }
        return false;
    }
    public void print(){
        System.out.println(map.toString());
    }
}