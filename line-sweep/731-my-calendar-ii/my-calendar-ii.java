
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

    // TreeMap<Integer, Integer> map = new TreeMap<>();
    // public boolean book(int start, int end) {
    //     // add values tree map
    //     // find values prefix
    //     // check if count more than 3
        
    //     map.put(start,  map.getOrDefault(start, 0) + 1);
    //     map.put(end, map.getOrDefault(end, 0) - 1);
    //     int sum = 0;
    //     for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
    //         sum += entry.getValue();
    //         if(sum == 3){
    //             map.put(start,  map.getOrDefault(start, 0) - 1);
    //             map.put(end, map.getOrDefault(end, 0) + 1);
    //             return false;
                
    //         }
    //     }
    //     return true;
    // }

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
