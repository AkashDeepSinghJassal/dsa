import java.util.Arrays;

/**
 * Allow at max two events at overlapping time
 * 10 - 20
 * 30 - 40
 * 15 - 25
 * 5 - 12
 * 17-19
 * 
 * 
 * 10 -20
 * 5 - 40
 * 
 * 15 - 18
 * 
 * 10 - 20
 * 30 - 40
 * 15 - 40
 * 5 - 6
 * 5 - 15
 * 11 - 14
 * 5 - 40
 */
public class CalenderEventTwo {
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
        myCalTwo.print();

    }
}

class MyCalendarTwo {
    Node rootOne;
    Node rootTwo;

    class Node {
        int start;
        int end;
        Node left;
        Node right;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

    }

    MyCalendarTwo() {

    }

    public boolean book(int start, int end) {
        return insertToTreeOne(new Node(start, end));
    }

    public boolean  insertToTreeOne(Node node) {
        if(rootOne == null) {
            rootOne = node;
            return true;
        }
        Node temp = rootOne;

        if(checkOverlapping(rootTwo, node)){
            return false;
        }
        while (temp != null) {
            // check overlapping with second tree
            if (node.end <= temp.start) {
                if (temp.left == null) {
                    temp.left = node;
                    break;
                } else
                    temp = temp.left;
            } else if (node.start >= temp.end) {
                if (temp.right == null) {
                    temp.right = node;
                    break;
                } else
                    temp = temp.right;
            } else {
                Node overlappingNode = new Node(Math.max(temp.start, node.start), Math.min(temp.end, node.end));
                insertToTreeTwo(overlappingNode);
                // second booking possible
                if(node.start < temp.start) 
                    insertToTreeOne(new Node(node.start, temp.start));
                if(node.end > temp.end) 
                    insertToTreeOne(new Node(temp.end, node.end));
                return true;
            }

        }
        return true;
    }

    public boolean  insertToTreeTwo(Node node) {
        if(rootTwo == null) {
            rootTwo = node;
            return true;
        }
        Node temp = rootTwo;

        while (temp != null) {
            if (node.end <= temp.start) {
                if (temp.left == null) {
                    temp.left = node;
                    return true;
                }
                temp = temp.left;
            } else if (node.start >= temp.end) {
                if (temp.right == null) {
                    temp.right = node;
                    return true;
                }
                temp = temp.right;
            } else {
                return false;
            }

        }
        return false;
    }
    private boolean checkOverlapping(Node root, Node node) {
        Node temp = root;

        while (temp != null) {
            if (node.end <= temp.start) {
                temp = temp.left;
            } else if (node.start >= temp.end) {
                temp = temp.right;
            } else {
                return true;
            }

        }
        return false;
    }
    public void print() {
        // print pre order
        System.out.println("Tree ONe");
        printPreOrder(rootOne);
        System.out.println("Tree Two");
        printPreOrder(rootTwo);
    }

    public void printPreOrder(Node node) {
        if (node != null) {
            printPreOrder(node.left);
            System.out.println(node.start + " " + node.end);
            printPreOrder(node.right);
        }
    }
}
